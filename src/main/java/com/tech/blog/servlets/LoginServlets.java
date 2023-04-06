package com.tech.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlets
 */
public class LoginServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

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
			
			String UserEmail = request.getParameter("email");
			String UserPass = request.getParameter("password");
			UserDao dao = new UserDao(ConnectionProvider.getConnection());
			User u = dao.getUserByEmailandPassword(UserEmail, UserPass);
			if(u!=null) {
				out.println("success.....");
				HttpSession s = request.getSession();
				s.setAttribute("current", u);
				response.sendRedirect("profile.jsp");
				
			}else {
				
			//	out.println("no user is found ");
				Message msg = new Message("oops wrong details , try again","error","alert-danger");
				HttpSession s = request.getSession();
				s.setAttribute("msg", msg);
				 response.sendRedirect("login_page.jsp");
			}
	 	doGet(request, response);
	}

}
