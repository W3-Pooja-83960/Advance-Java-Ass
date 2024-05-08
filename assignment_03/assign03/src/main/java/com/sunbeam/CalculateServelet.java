package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/operations")
public class CalculateServelet extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        float num1 = Float.parseFloat(req.getParameter("num1"));
        float num2 = Float.parseFloat(req.getParameter("num2"));
        String type=req.getParameter("operations");
        String result="";
        

        if(type.equals("add")){
            //float res= num1+num2;
        result = "The addition of given numbers is "+(num1+num2);
        }
        else if(type.equals("sub"))
        result = "The subtraction of given numbers is "+(num1-num2);
        
        else if(type.equals("mul"))
        result = "The multiplication of given numbers is "+(num1*num2);

        else if(type.equals("div"))
        result = "The division of given numbers is "+(num1/num2);

        out.println("<html>");
        out.println("<body>");
        out.println(" <h1> "+result+"</h1>");
        out.println("</body>");
        out.println("</html>");


    }
}
