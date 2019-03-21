package com.learn.springboot.conf;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc 定义了一个监控服务器和一个过滤器，
 * 监控服务器设定了访问监控后台的连接地址为“/druid/*”，设定了
 * 访问数据库的白名单和黑名单，即通过访问者的IP地址来控制访问来源
 * ，增加了数据库的安全设置，还配置了一个用来登陆监控后台的用户druid
 * 并将密码设置为12345678
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean statViewServle() {
        ServletRegistrationBean servletRegistrationBean;
        servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //IP白名单
        servletRegistrationBean.addInitParameter("allow", "192.168.3.123,126.0.0.1");
        //IP黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.0.124");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "12345678");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤的规则
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
