package com.example.demo.configuration;

import cn.hutool.core.util.StrUtil;
import com.example.demo.filter.BaseFilter;
import com.example.demo.filter.LoginFilter;
import com.example.demo.filter.TwoFilter;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    FilterRegistrationBean loginFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new LoginFilter());
        filter.setOrder(2);
        filter.addUrlPatterns("/*");
        //不过滤
        List<String> excludes = new ArrayList<>();
        excludes.add("/test/test");
        filter.addInitParameter(BaseFilter.PARAM_NAME_EXCLUSIONS, StrUtil.join(",", excludes.toArray()));
        return filter;
    }

    @Bean
    FilterRegistrationBean twoFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new TwoFilter());
        filter.setOrder(1);
        filter.addUrlPatterns("/*");
        //不过滤
        List<String> excludes = new ArrayList<>();
        excludes.add("/test/*");
        excludes.add("/test");
        excludes.add("c");
        filter.addInitParameter(BaseFilter.PARAM_NAME_EXCLUSIONS, StrUtil.join(",", excludes.toArray()));
        return filter;
    }
}
