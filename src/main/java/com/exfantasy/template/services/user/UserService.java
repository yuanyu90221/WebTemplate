package com.exfantasy.template.services.user;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exfantasy.template.config.AdminConfig;
import com.exfantasy.template.constant.ResultCode;
import com.exfantasy.template.constant.Role;
import com.exfantasy.template.exception.OperationException;
import com.exfantasy.template.mybatis.mapper.UserMapper;
import com.exfantasy.template.mybatis.mapper.UserRoleMapper;
import com.exfantasy.template.mybatis.model.User;
import com.exfantasy.template.mybatis.model.UserExample;
import com.exfantasy.template.mybatis.model.UserRole;
import com.exfantasy.template.util.EncryptUtil;
import com.exfantasy.template.vo.request.LoginVo;
import com.exfantasy.template.vo.request.RegisterVo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private AdminConfig adminConfig;

//    private final AdminConfig adminConfig;
//
//    @Autowired
//    public UserService(AdminConfig adminConfig) {
//        this.adminConfig = adminConfig;
//    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void register(RegisterVo registerVo) {
    	User user = new User();
    	user.setEmail(registerVo.getEmail());
    	user.setPassword(EncryptUtil.encrypt(registerVo.getPassword()));
    	user.setEnabled(true);
    	user.setCreateTime(new Date(System.currentTimeMillis()));
        userMapper.insert(user);
        
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRole(isAdminEmail(registerVo.getEmail()) ? Role.ADMIN.getRoleStr() : Role.USER.getRoleStr());
		userRoleMapper.insert(userRole);
    }
    
	public void login(LoginVo loginVo) {
		User user = queryUserByEmail(loginVo.getEmail());
		if (user == null) {
			throw new OperationException(ResultCode.CANNOT_FIND_USER);
		}
		String inputPwd = EncryptUtil.encrypt(loginVo.getPassword());
		String pwd = user.getPassword();
		if (!inputPwd.equals(pwd)) {
			throw new OperationException(ResultCode.INCORRECT_PASSWORD);
		}
		
		logger.info("{} login succeed", loginVo.getEmail());
	}

	public User queryUserByUserId(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
	
	public User queryUserByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> user = userMapper.selectByExample(example);
		return user.isEmpty() ? null : user.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateSelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int updateByReplace(User user) {
        return userMapper.updateByPrimaryKey(user);
    }
    
    private boolean isAdminEmail(String email) {
		List<String> admins = adminConfig.getAdmins();
		return admins.contains(email);
	}
}
