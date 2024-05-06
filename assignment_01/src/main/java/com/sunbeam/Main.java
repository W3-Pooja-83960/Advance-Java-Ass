/* 1. Implement a menu driven program using PreparedStatement. Provide following functionalities.
Create New User
Create New Category
Display All Categories
Create New Blog (input user id, category id, title, contents from user).
Display All Blogs
Edit the Blog (by id)
Delete the Blog (by id)
Display Blogs of the user (input user id from user)
Display Blogs of the category (input category id from user)
Search Blogs by content or title (input word to search from user)
 */


package com.sunbeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
private static final String DB_URL = "jdbc:mysql://localhost:3306/dmcdb";
private static final String DB_USER = "dmc";
private static final String DB_PASSWORD ="dmc";


static{
    
try{
    Class.forName(DB_DRIVER);
}
catch(Exception e){
e.printStackTrace();
System.exit(1);
}
}

public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int choice =12;
        do{
         System.out.println
         ("\n\n1. Create new user\n"+
         "2.Create New Category\n"+
         "3.Display All Categories\n"+
         "4.Create New Blog \n"+
         "5.Display All Blogs\n"+
         "6.Edit the Blog \n"+
         "7.Delete the Blog \n"+
         "8.Display Blogs of the user\n"+
         "9.Display Blogs of the category\n"+
         "10.Search Blogs by content or title\n"+
         "0.Exit\n");
         System.out.print(" Enter your choice : ");
         choice=sc.nextInt();
            switch(choice)
            {
                case 1 :
                create_user();
                break;
                case 2:
                 create_category();
                break;
                case 3:
                display_categories();
                break;
                case 4:
                create_blog();
                break;
                case 5:
                display_blogs();
                break;
                case 6:
                edit_blog();
                case 7:
               delete_blog();
                break;
                case 8:
                dis_blog_userid();
                break;
                case 9:
                dis_blog_catid();
                break;
                case 10:
                search_blog();
                break;

            }
        }while(choice!=0);
        }

         static void  create_user()
         {
        Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql ="Insert into users(email,name,password,phone,created_on) values(?,?,?,?,null)";

            try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                    System.out.println("Enter new email id : ");
                    String emailId=sc.nextLine();

                    System.out.println("Enter new name : ");
                    String name=sc.nextLine();

                    System.out.println("Enter new password : ");
                    String password=sc.nextLine();

                    System.out.println("Enter new phone number : ");
                    long phoneno =sc.nextLong();

                    stmt.setString(1, emailId);
                    stmt.setString(2, name);
                    stmt.setString(3, password);
                    stmt.setLong(4, phoneno);

                    int count = stmt.executeUpdate();
                    System.out.println("Number of rows afftected :"+count);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

         }


         static void  create_category()
         {
        Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql ="Insert into categories(title,description) values(?,?)";

            try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                    System.out.println("Enter new category title : ");
                    String title=sc.nextLine();

                    System.out.println("Enter category description : ");
                    String desc=sc.nextLine();

                    stmt.setString(1, title);
                    stmt.setString(2, desc);

                    int count = stmt.executeUpdate();
                    System.out.println("Number of rows affected :"+count);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

         }




         static void  display_categories()
         {
        Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql ="Select * from categories";

            try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                   try(ResultSet rs = stmt.executeQuery())
                   {
                    while(rs.next())
                    {
                        int id =rs.getInt("id");
                        String title = rs.getString("title");
                        String description = rs.getString("description");
                        System.out.println("Category id : "+id+ " title : "+title+" description: "+description);
                        
                    }
                   }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

         }


         static void  create_blog()
         {
        Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql ="Insert into blogs(created_on,contents,title,category_id,user_id) values(null,?,?,?,?)";

            try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                    System.out.println("Enter new blog title : ");
                    String title=sc.nextLine();

                    System.out.println("Enter blog content  : ");
                    String content=sc.nextLine();

                    System.out.println("Enter category_id of blog : ");
                    int cat_id=sc.nextInt();

                    System.out.println("Enter user_id of blog creator: ");
                    int user_id=sc.nextInt();

                    stmt.setString(1, content);
                    stmt.setString(2, title);
                    stmt.setInt(3,cat_id );
                    stmt.setInt(4, user_id);

                    int count = stmt.executeUpdate();
                    System.out.println("Number of rows affected :"+count);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

         }


         static void  display_blogs()
         {
        Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql ="Select id,created_on,title,user_id,category_id from blogs";

            try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                   try(ResultSet rs = stmt.executeQuery())
                   {
                    while(rs.next())
                    {
                        int id =rs.getInt("id");
                        String created_on= rs.getString("created_on");
                        String title = rs.getString("title");
                        int cat_id = rs.getInt("category_id");
                        int user_id = rs.getInt("user_id");
                        System.out.println(" id : "+id+ " title : "+title+" created on :"+created_on+" category_id "+cat_id+" user_id: "+user_id);

                        
                    }
                   }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

         }


         static void edit_blog()
         {
         Scanner sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql ="Update blogs Set title=?,contents=? where id=?";

            try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                    System.out.println("Enter blog id you want to edit: ");
                    int id=sc.nextInt();

                    sc.nextLine();
                    System.out.println("Enter updated title  : ");
                    String title=sc.nextLine();

                    System.out.println("Enter blog content : ");
                    String content=sc.nextLine();

                    

                    stmt.setString(1, title);
                    stmt.setString(2, content);
                    stmt.setInt(3, id);
                   

                    int count = stmt.executeUpdate();
                    System.out.println("Number of rows affected :"+count);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    static void delete_blog()
    {
    Scanner sc = new Scanner(System.in);
   try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
   {
       String sql ="Delete from  blogs where id=?";

       try(PreparedStatement stmt = con.prepareStatement(sql))
       {
               System.out.println("Enter blog id you want to delete: ");
               int id=sc.nextInt();
               
               stmt.setInt(1, id);
              int count = stmt.executeUpdate();
               System.out.println("Number of rows affected :"+count);
       }
   }
   catch(Exception e)
   {
       e.printStackTrace();
   }
}

static void  dis_blog_userid()
{
Scanner sc = new Scanner(System.in);
try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
{
   String sql ="Select id,created_on,title,user_id,category_id from blogs where user_id=?";

   try(PreparedStatement stmt = con.prepareStatement(sql))
   {
    System.out.println("Enter user_id of user of whom you want to see blogs :");
    int usr_id=sc.nextInt();
    stmt.setInt(1, usr_id);
          try(ResultSet rs = stmt.executeQuery())
          {
           while(rs.next())
           {
               int id =rs.getInt("id");
               String created_on= rs.getString("created_on");
               String title = rs.getString("title");
               int cat_id = rs.getInt("category_id");
               int user_id = rs.getInt("user_id");
               System.out.println(" id : "+id+ " title : "+title+" created on :"+created_on+" category_id :"+cat_id+" user_id: "+user_id);

               
           }
          }
   }
}
catch(Exception e)
{
   e.printStackTrace();
}

}




static void  dis_blog_catid()
{
Scanner sc = new Scanner(System.in);
try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
{
   String sql ="Select id,created_on,title,user_id,category_id from blogs where category_id=?";

   try(PreparedStatement stmt = con.prepareStatement(sql))
   {
    System.out.println("Enter category_id of category for which you want to see blogs :");
    int categ_id=sc.nextInt();
    stmt.setInt(1, categ_id);
          try(ResultSet rs = stmt.executeQuery())
          {
           while(rs.next())
           {
               int id =rs.getInt("id");
               String created_on= rs.getString("created_on");
               String title = rs.getString("title");
               int cat_id = rs.getInt("category_id");
               int user_id = rs.getInt("user_id");
               System.out.println(" id : "+id+ " title : "+title+" created on :"+created_on+" category_id :"+cat_id+" user_id: "+user_id);

               
           }
          }
   }
}
catch(Exception e)
{
   e.printStackTrace();
}

}


static void  search_blog()
{
Scanner sc = new Scanner(System.in);
try(Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
{
   String sql ="Select id,created_on,title,user_id,category_id from blogs where contents like CONCAT( '%',?,'%') or title like CONCAT('%',?,'%')";

   try(PreparedStatement stmt = con.prepareStatement(sql))
   {
    System.out.println("Enter any word for the blog :");
    String word=sc.nextLine();
    stmt.setString(1, word);
    stmt.setString(2, word);
          try(ResultSet rs = stmt.executeQuery())
          {
           while(rs.next())
           {
               int id =rs.getInt("id");
               String created_on= rs.getString("created_on");
               String title = rs.getString("title");
               int cat_id = rs.getInt("category_id");
               int user_id = rs.getInt("user_id");
               System.out.println(" id : "+id+ " title : "+title+" created on :"+created_on+" category_id :"+cat_id+" user_id: "+user_id);

               
           }
          }
   }
}
catch(Exception e)
{
   e.printStackTrace();
}

}



}