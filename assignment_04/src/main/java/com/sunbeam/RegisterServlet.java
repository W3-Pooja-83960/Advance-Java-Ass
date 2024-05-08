package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    { ProcessRequest(req, resp);}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProcessRequest(req, resp);
    }
    protected void ProcessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    { 

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<title>");
        out.println("Register User</title>");
        out.println("<body>");
        out.println(" <form method='post' action= 'saveuser'>");
        out.println("Name: <input type ='text' name='username'/></br>");
        out.println("Email: <input type ='email' name='email'/></br>");
        out.println("Password: <input type ='password' name='passwd'/></br>");
        out.println("phone: <input type ='text' name='phone'/></br>");

        out.println("<input type='submit' value='save'>");
        out.println("</form>");

        out.println("</body>");

        

        out.println("</html>");

        
    }
}
