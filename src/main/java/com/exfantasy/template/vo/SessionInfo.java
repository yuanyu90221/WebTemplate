package com.exfantasy.template.vo;

public class SessionInfo {
	public static final String SESSION_INFO = "SESSION_INFO";

	private String email;
	
	private String clienIp;

	public SessionInfo() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClienIp() {
		return clienIp;
	}

	public void setClienIp(String clienIp) {
		this.clienIp = clienIp;
	}

}
