package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/findblog")
public class FindBlogByWordServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     processRequest(req, resp);   
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        PrintWriter out = resp.getWriter();
        out.println("<html >"+"<head>"+"<title>"+"FindBlog</title></head>"+
        "<body><form action='searchblog' method='post'>"+
        "Enter any term to search for blogs here : "+
        "<input type='text' name='word'/><input type='submit' "+
        "value='Search'/></form></body></html>")  ;  
    }

}
