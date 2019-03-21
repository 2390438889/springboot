package com.learn.springboot.pojo;

import org.springframework.cache.annotation.EnableCaching;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc 表的结构定义是security提供的
 * 使用一个实体来实现，目的是为了在系统启动时
 * 能够创建这个表结构，它用来保存用户名，令牌和
 * 最后登陆的时间
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins implements Serializable {
    @Id
    @Column(name = "series",length = 64,nullable = false)
    private String series;
    @Column(name = "username",length = 64,nullable = false)
    private String username;
    @Column(name = "token",length = 64,nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used",nullable = false)
    private Date last_used;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }

    @Override
    public String toString() {
        return "PersistentLogins{" +
                "series='" + series + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", last_used=" + last_used +
                '}';
    }
}
