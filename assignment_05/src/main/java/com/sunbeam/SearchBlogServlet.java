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
@WebServlet("/searchblog")
public class SearchBlogServlet extends HttpServlet{
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
       List<Blog> list = new ArrayList<>();
        String word= req.getParameter("word");
        try(BlogDao bd = new BlogDao())
        {
             list= bd.findByLikeContent(word);
            
        }
        catch(Exception e)
        {
            
            e.printStackTrace();
        }
              
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Blogs Searched by word</title>");
        
        out.println("</head>");
        out.println("<body>");
        
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
