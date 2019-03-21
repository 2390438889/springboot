package com.learn.springboot.conf;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Hearts
 * @date 2019/3/21
 * @desc
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

        private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$}");

        /**
         * 需要排除的url列表
         */
        private List<String> execludeUrls;

        @Override
        public boolean matches(HttpServletRequest httpServletRequest) {
            if (execludeUrls != null && execludeUrls.size()>0){
                String servletPath = httpServletRequest.getServletPath();
                for (String execludeUrl : execludeUrls) {
                    if (servletPath.contains(execludeUrl)){
                        return false;
                    }
                }
            }
            return !allowedMethods.matcher(httpServletRequest.getMethod()).matches();
        }

    public Pattern getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(Pattern allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getExecludeUrls() {
        return execludeUrls;
    }

    public void setExecludeUrls(List<String> execludeUrls) {
        this.execludeUrls = execludeUrls;
    }
}
