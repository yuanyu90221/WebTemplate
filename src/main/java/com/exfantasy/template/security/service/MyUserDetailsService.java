package com.exfantasy.template.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exfantasy.template.mybatis.model.User;
import com.exfantasy.template.mybatis.model.UserRole;
import com.exfantasy.template.services.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.queryUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Cannot find user");
		} 
		else {
			List<UserRole> roles = userService.queryUserRoles(user);
			List<GrantedAuthority> gas = createRoles(roles);
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, gas);
		}
	}
	
	private List<GrantedAuthority> createRoles(List<UserRole> roles) {
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		for (UserRole role : roles) {
			gas.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return gas;
	}
}