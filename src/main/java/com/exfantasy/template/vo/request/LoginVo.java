package com.exfantasy.template.vo.request;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginVo {
	@NotEmpty(message = "Please input email")
    private String email;

	@NotEmpty(message = "Please input password")
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
