package com.example.demo.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseFilter extends OncePerRequestFilter {
    public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
    private Set<String> excludesPattern;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void initFilterBean() throws ServletException {
        String param = super.getFilterConfig().getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (param != null && param.trim().length() != 0) {
            this.excludesPattern = new HashSet(Arrays.asList(param.trim().split("\\s*,\\s*")));
        }
    }

    @Override
    protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //不过滤
        if (isExclusion(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        doFilterIn(request, response, filterChain);
    }

    protected abstract void doFilterIn(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException;

    public boolean isExclusion(String requestURI) {
        if (this.excludesPattern == null) {
            return false;
        } else {
            for (String pattern : excludesPattern) {
                if (antPathMatcher.match(pattern, requestURI)) {
                    return true;
                }
            }
            return false;
        }
    }
}
