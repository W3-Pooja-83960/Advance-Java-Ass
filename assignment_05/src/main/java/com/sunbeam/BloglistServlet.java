package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            //list=blogDao.findAll();
            if(req.getParameter("userid")!=null)
            {
                int userId = Integer.parseInt(req.getParameter("userid"));
                list = blogDao.findBlogByUserId(userId);
            }
            else{
                list=blogDao.findAll();
            }
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
       // out.println("Hello, "+user.getName()+"<hr/>");
       String userName="";
       Cookie[] arr= req.getCookies();
       if(arr!=null){
                for(Cookie c :arr)
                {
                    if(c.getName().equals("uname"))
                    {
                        userName=c.getValue();
                        break;
                    }
                }
       }
       out.println("Hello, "+userName+"<hr/>");
        out.println("<a href='categories.html' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;' > Create category</a>");
        out.println("<a href='catlist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Show categories </a>");

        out.println("<a href='newblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;' > Create Blog</a>");
        out.println("<a href='findblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'> Find Blogs</a>");
        out.println("<a href='bloglist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'> All Blogs</a>");
        out.printf("<a href='bloglist?userid=%d' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>My Blogs </a>",user.getId());
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
                //out.printf("<td></td>");
                out.println("<td>");
                if(b.getUserId()==user.getId())
                    out.printf("<a href='editblog?id=%d'><img src='edit.png' alt='Edit' width='18' height='18' /> </a>  <a href='delblog?id=%d'><img src='delete.png' alt='Delete' width='16' height='16' /> </a>  ",b.getId(),b.getId());
                out.println("</td>");
                out.println("</tr>");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        out.println("</table>");
        String message =(String) req.getAttribute("message");
        if(message!=null)
            out.println("<br/>"+message);
        out.println("</body>");
        out.println("</html>");

    }
}
