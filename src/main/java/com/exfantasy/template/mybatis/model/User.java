package com.exfantasy.template.mybatis.model;

import java.util.Date;

public class User {
    private Integer userId;

    private String email;

    private String password;

    private boolean enabled;

    private Date createTime;

    private Date lastSigninTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastSigninTime() {
        return lastSigninTime;
    }

    public void setLastSigninTime(Date lastSigninTime) {
        this.lastSigninTime = lastSigninTime;
    }
}