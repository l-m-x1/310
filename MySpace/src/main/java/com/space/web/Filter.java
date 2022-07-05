package com.space.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest)servletRequest;

        String []urls={"/css","/element-ui","/icon","/js","/index.html","register.html","/Login","/Register"};

        String url = req.getRequestURL().toString();
        for(String s:urls){
            if(url.contains(s)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }

        }

        Object id = req.getSession().getAttribute("id");
        if(id!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {

            req.getRequestDispatcher("/index.html").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
