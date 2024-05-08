package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bloglist")
public class BloglistServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
      ProcessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        ProcessRequest(req, resp);
    }


    protected void ProcessRequest(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException 
    {
        List<Blog> list = new ArrayList<>();
        try(BlogDao blogDao = new BlogDao()){
            list=blogDao.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        PrintWriter out= resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Blogs</title>");
        //out.println("<script> a{background-color: '#AAAAAA'; padding: 14px 20px; text-align: 'center'; display: 'inline-block';}");
        //out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("curusr");
        out.println("Hello, "+user.getName()+"<hr/>");
        out.println("<a href='newblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;' > Create Blog</a>");
        out.println("<a href='findblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'> Find Blogs</a>");
        out.println("<a href='bloglist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'> All Blogs</a>");
        out.println("<a href='bloglist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>My Blogs </a>");
        out.println("<a href='logout' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Logout</a>");
        out.println("<br/><br/>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Title</th>");
        out.println("<th>Category</th>");
        out.println("<th>User</th>");
        out.println("<th>Action</th>");
        out.println("</tr>");
        try(CategoryDao categoryDao = new CategoryDao())
        {
            for(Blog b:list){
                out.println("<tr>");
                out.printf("<td>%d</td>",b.getId());
                out.printf("<td>%s</td>",b.getTitle());
                Category c = categoryDao.findById(b.getCategoryId());
                out.printf("<td>%s</td>",c.getTitle());
        try(UserDao userDao=new UserDao())
                {
                User u = userDao.findById(b.getUserId());
                out.println("<td>"+u.getName()+"</td>");
                }
                out.printf("<td></td>");
                out.println("</tr>");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

    }
}
