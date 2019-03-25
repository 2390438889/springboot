package com.learn.springboot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc 自定义配置类
 */
@ConfigurationProperties(prefix = "securityconfig")
@Component
public class SecuritySettings implements Serializable {
    private String logoutsuccessurl = "/logout";
    private String permitall = "/api";
    private String deniedpage = "/deny";
    private String urlroles;

    public String getLogoutsuccessurl() {
        return logoutsuccessurl;
    }

    public String getPermitall() {
        return permitall;
    }

    public String getDeniedpage() {
        return deniedpage;
    }

    public String getUrlroles() {
        return urlroles;
    }
}
