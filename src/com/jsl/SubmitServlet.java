package com.jsl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shaoliang.
 */
@WebServlet("/submit")
public class SubmitServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
                response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if("admin".equals(username)&&"admin".equals(password)){
            session.setAttribute("authenticated",true);
            String path = (String)session.getAttribute("path");
            if(path!=null){
                response.sendRedirect(path);
                return;
            }
        }
        response.sendRedirect("login.jsp");
    }
}