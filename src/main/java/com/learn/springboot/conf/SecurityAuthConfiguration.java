package com.learn.springboot.conf;

import com.learn.springboot.pojo.SecuritySettings;
import com.learn.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc 安全配置策略
 */

public class SecurityAuthConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private SecuritySettings settings;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login").permitAll()
                .successHandler(loginSuccessHandler())
                .and().authorizeRequests()
                .antMatchers("/images/**","*/checkcode","/scripts/**","/styles/**").permitAll()
                .antMatchers(settings.getPermitall().split(",")).permitAll()
                .anyRequest().authenticated()
                .and().csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().logout().logoutSuccessUrl(settings.getLogoutsuccessurl())
                .and().exceptionHandling().accessDeniedPage(settings.getDeniedpage())
                .and().rememberMe().tokenValiditySeconds(1209600).tokenRepository(tokenRepository());
    }

    private PersistentTokenRepository tokenRepository() {
        return new PersistentTokenRepository() {
            @Override
            public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {

            }

            @Override
            public void updateToken(String s, String s1, Date date) {

            }

            @Override
            public PersistentRememberMeToken getTokenForSeries(String s) {
                return null;
            }

            @Override
            public void removeUserTokens(String s) {

            }
        };
    }

    private RequestMatcher csrfSecurityRequestMatcher() {

        CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
        List<String> list = new ArrayList<String>();
        list.add("/rest/");
        csrfSecurityRequestMatcher.setExecludeUrls(list);
        return csrfSecurityRequestMatcher;
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                User userDetails = (User) authentication.getPrincipal();
                System.out.println("IP:"+httpServletRequest.getHeaderNames());
            }
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
