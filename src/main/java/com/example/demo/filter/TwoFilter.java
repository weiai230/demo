package com.example.demo.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoFilter extends BaseFilter {

    @Override
    protected void doFilterIn(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("2过滤:" + request.getRequestURI());
        filterChain.doFilter(request, response);
    }

}
