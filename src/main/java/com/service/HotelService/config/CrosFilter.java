package com.service.HotelService.config;
// 處理 CORS 請求，允許跨域請求的發起和回應。
// 設置 HTTP 標頭，收到預檢請求時，返回適當的響應
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CrosFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String originHeader = req.getHeader("origin");
        res.setHeader("Acess-Control-Allow-Origin", originHeader);
        res.setHeader("Acess-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        res.setHeader("Acess-Control-Max-Age", "3600");
        res.setHeader("Acess-Control-Headers", "*");
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
        }
        if (!"OPTIONS".equalsIgnoreCase(req.getMethod())) {
           filterChain.doFilter(servletRequest, servletResponse);
        }
        
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
