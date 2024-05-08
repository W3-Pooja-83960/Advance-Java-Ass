package com.sunbeam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delblog")
public class DeleteBlogServlet extends HttpServlet{
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
        int blogId=Integer.parseInt(req.getParameter("id"));
        try(BlogDao blogdao =new BlogDao())
        {
            int count = blogdao.deleteById( blogId);
            String message = "Blog deleted succesfully";
            req.setAttribute("message", message);
    
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String message = "Blog delete failed";
            req.setAttribute("message", message);
    
            
        }
        //resp.sendRedirect("bloglist");
        RequestDispatcher rd = req.getRequestDispatcher("bloglist");
        rd.forward(req, resp);
    }
}
