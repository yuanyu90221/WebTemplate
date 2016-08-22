package com.exfantasy.template.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {
	private List<String> admins = new ArrayList<String>();

	private int loginMaxAttempt;
	
	private int blockTimeMins;
	
	public void setAdmins(List<String> admins) {
    	this.admins = admins;
    }

    public List<String> getAdmins() {
        return this.admins;
    }
    
    public void setLoginMaxAttempt(int loginMaxAttempt) {
    	this.loginMaxAttempt = loginMaxAttempt;
    }
    
    public int getLoginMaxAttempt() {
    	return this.loginMaxAttempt;
    }
    
    public void setBlockTimeMins(int blockTimeMins) {
    	this.blockTimeMins = blockTimeMins;
    }
    
    public int getBlockTimeMins() {
    	return this.blockTimeMins;
    }
}
