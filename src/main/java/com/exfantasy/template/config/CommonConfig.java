package com.exfantasy.template.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CommonConfig {
	private List<String> admins = new ArrayList<String>();
}
