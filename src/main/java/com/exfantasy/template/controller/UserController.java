package com.exfantasy.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.template.constant.ResultCode;
import com.exfantasy.template.exception.OperationException;
import com.exfantasy.template.mybatis.model.User;
import com.exfantasy.template.services.user.UserService;
import com.exfantasy.template.util.ErrorMsgUtil;
import com.exfantasy.template.vo.request.QueryVo;
import com.exfantasy.template.vo.request.RegisterVo;
import com.exfantasy.template.vo.response.ResponseVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
//	@Autowired(required = true)
//	private HttpServletRequest request;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String returnUserPage(Model model) {
		model.addAttribute("registerVo", new RegisterVo());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseVo register(@Validated @RequestBody final RegisterVo registerVo, BindingResult result) {
		if (result.hasErrors()) {
			String errorMsg = ErrorMsgUtil.getErrorMsgs(result);
			throw new OperationException(ResultCode.INVALID_FORMAT, errorMsg);
		}
		userService.register(registerVo);
		return new ResponseVo(ResultCode.SUCCESS, "Register succeed");
	}
	
	@RequestMapping(value = "/get_by_email", method = RequestMethod.POST)
	public @ResponseBody User queryUserByEmail(@RequestBody final QueryVo<?> queryVo, BindingResult result) {
		return userService.queryUserByEmail((String) queryVo.getCriteriaValue());
	}
}
