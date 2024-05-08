package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/catlist")
public class CategoryListServlet extends HttpServlet{
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

        List<Category> list = new ArrayList<>();
        try(CategoryDao categoryDao = new CategoryDao())
        {
                list = categoryDao.findAll();
    
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }


    resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Category List</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Title</th>");
        out.println("<th>Category Desc</th>");
        out.println("</tr>");
    for(Category c: list)
    {
        out.println("<tr>");
        out.printf("<td>%s</td>",c.getTitle());
        out.printf("<td>%s</td>",c.getDescription());
        out.println("</tr>");    
    }
        out.println("</table>");
        out.println("<br/><br/><br/>");
        out.println("<a href='bloglist'>go back</a>");
        out.println("</body>");
        out.println("</html>");




}


}
