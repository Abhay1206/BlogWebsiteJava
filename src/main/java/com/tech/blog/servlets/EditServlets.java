package com.tech.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;

/**
 * Servlet implementation class EditServlets
 */

@MultipartConfig
public class EditServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlets() {
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
			
			String uEmail = request.getParameter("user_email");
			String uName= request.getParameter("user_name");
			String uPass = request.getParameter("user_password");
			String uAbout = request.getParameter("user_about");
			Part part =  request.getPart("user-image");
			String iName = part.getSubmittedFileName();
			
			/// get user  rom seesion
			HttpSession s = request.getSession();
		User u=(User)s.getAttribute("current");
	    	u.setEmail(uEmail);
	    	u.setName(uName);
			 u.setPassword(uPass);
			 u.setAbout(uAbout);
			 String old = u.getProfile();
			 u.setProfile(iName);
			 // 
			 UserDao dao = new UserDao(ConnectionProvider.getConnection());
		boolean ans =	 dao.updateUser(u);
		if(ans==true) {
			out.print("updated aa");
			
			String path =request.getRealPath("/pics")+File.separator+u.getProfile();
			String path1 =request.getRealPath("/pics")+File.separator+old;
			if(!old.equals("default.png")) {
			Helper.deletefile(path1);
			}
			if(Helper.savefile(part.getInputStream(), path)) {
				out.print("profile upated......");
				
				Message m = new Message("Profile photo updted succeefully","success","alert-success");
				s.setAttribute("msg", m);
				
				
			}else {

				Message m = new Message("Profile photo updted succeefully","error","alert-danger");
				s.setAttribute("msg", m);
			}
			
		
		}else {
			out.print("not updated");
			Message m = new Message("Profile photo updted succeefully","error","alert-danger");
			s.setAttribute("msg", m);
		}
		
		response.sendRedirect("profile.jsp");
			 
		doGet(request, response);
	}

}
