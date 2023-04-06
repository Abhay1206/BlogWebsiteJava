package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class RegisterServlets
 */
public class RegisterServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlets() {
        super();
        // TODO Auto-generated constructor stub
    }
 
   
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//          
//
////            fetch all form data
//            String check = request.getParameter("check");
//            if (check == null) {
//                out.println("box not checked");
//            } else {
//              
//                String name = request.getParameter("user_name");
//                String email = request.getParameter("user_email");
//                String password = request.getParameter("user_password");
//                String gender = request.getParameter("gender");
//                String about = request.getParameter("about");
//                //create user object and set all data to that object..
//                User user = new User(name, email, password, gender, about);
//
//                //create a user daao object..
//                UserDao dao = new UserDao(ConnectionProvider.getConnection());
//                if (dao.saveUser(user)) {
////                save..
//                    out.println("done");
//                } else {
//                    out.println("error");
//                }
//            }
//         
//        }
//    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   response.setContentType("text/html;charset=UTF-8");
	
		PrintWriter out= response.getWriter();
		  String check = request.getParameter("check");
          if (check == null) {
              System.out.println("box not checked");
          } else {
             
              String name = request.getParameter("user_name");
              String email = request.getParameter("user_email");
              String password = request.getParameter("user_password");
              String gender = request.getParameter("gender");
              String about = request.getParameter("about");
              //create user object and set all data to that object..
              User user = new User(name, email, password, gender, about);
    
              //create a user daao object..
              UserDao dao = new UserDao(ConnectionProvider.getConnection());
              boolean result = dao.saveUser(user);
              out.println("<html>");
              out.println("<head>");
              out.println("<title>Registration Result</title>");
              out.println("</head>");
              out.println("<body>");
              if (result) {
                  out.println("<h3>Registration Successful!</h3>");
                  out.println("<p>Welcome, " + name + "!</p>");
                  out.println("<a href=\"login_page.jsp\">Click here to log in</a>");
              } else {
                  out.println("<h3>Registration Failed!</h3>");
                  out.println("<p>Please try again later.</p>");
              }
              out.println("</body>");
              out.println("</html>");
//              if (dao.saveUser(user)) {
////              save..
//                  System.out.println("done");
//                  out.println("sucess");
//              } else {
//                  out.println("error");
//              }
          }
	//	doGet(request, response);
         //  response.sendRedirect("login_page.jsp");
         
	}

}
