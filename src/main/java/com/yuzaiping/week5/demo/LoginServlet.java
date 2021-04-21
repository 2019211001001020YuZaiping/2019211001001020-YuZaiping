package com.yuzaiping.week5.demo;
import com.yuzaiping.dao.UserDao;
import com.yuzaiping.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init()throws ServletException{
        /*String driver=getServletConfig().getServletContext().getInitParameter("driver");
        String url=getServletConfig().getServletContext().getInitParameter("url");
        String username=getServletConfig().getServletContext().getInitParameter("username");
        String password=getServletConfig().getServletContext().getInitParameter("password");
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
        //only one one
        con = (Connection) getServletContext().getAttribute("con");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when user click login menu -request is get
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sql="Select * from usertable where username=? and password=?";
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        //now move jdbc code in dao - MVC design
        //write mvc code
        //user model and dao
        UserDao userDao=new UserDao();
        try {
            User user= userDao.findByUsernamePassword(con,username,password);
            if (user!=null){
                //valid
                //set user into request
                request.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else{
                //invalid
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
            //forward - JSP
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        /*try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                //out.println("Login Success!!!");
                //out.println("Welcome "+username);
                //get from rs and set into request attribute
                //forward to userinfo.jsp
                request.setAttribute("id",rs.getInt("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthday",rs.getString("birthday"));
                //forward to userinfo.jsp
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else{
                //out.println("Username or Password Error!!!");
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }//end of else
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}

