package com.exfantasy.template.constant;

public enum Role {
	ADMIN("admin"),
	USER("user");
	
	private String role;
	
	private Role(String role) {
		this.role = role;
	}
	
	public String getRoleStr() {
		return this.role;
	}
	
	public Role convert(String roleStr) {
		for (Role role : Role.values()) {
			if (role.getRoleStr().equals(roleStr)) {
				return role;
			}
		}
		return null;
	}
} 
