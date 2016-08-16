package com.exfantasy.template.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 如果是 config 一定要有 getter, setter
 * 
 * http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
 * 
 * @author tommy.feng
 */
@Component
@ConfigurationProperties(prefix = "custom")
public class AdminConfig {

    private List<String> admins = new ArrayList<String>();
    
    public void setAdmins(List<String> admins) {
    	this.admins = admins;
    }

    public List<String> getAdmins() {
        return this.admins;
    }
}