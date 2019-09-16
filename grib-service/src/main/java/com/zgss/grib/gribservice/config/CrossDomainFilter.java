package com.zgss.grib.gribservice.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  跨域过滤器
 */
public class CrossDomainFilter implements Filter {

    public CrossDomainFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpServletRequest requ = (HttpServletRequest)servletRequest;
        String distcode = requ.getHeader("distcode");
        System.out.println("distcode in cross:"+ distcode);

        resp.addHeader("Access-Control-Allow-Origin", "*");
        // Authorization 参数用于支持token
        resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since," +
                " Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Authorization");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
