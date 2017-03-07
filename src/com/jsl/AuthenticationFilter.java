package com.jsl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shaoliang.
 */
@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = {"/*"}
        )
public class AuthenticationFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("", "/login.jsp","/submit")));

    public void init(FilterConfig var1) throws ServletException{

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        System.out.println("I am filtering...");
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        Boolean authenticated = (Boolean)session.getAttribute("authenticated");
        if(authenticated==null && !allowedPath){
            session.setAttribute("path",httpRequest.getRequestURI());
            httpResponse.sendRedirect("login.jsp");
        }
        chain.doFilter(request, response);
    }

    public void destroy(){

    }
}
