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

import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;

/**
 * Servlet implementation class AddPostServlets
 */

@MultipartConfig
public class AddPostServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostServlets() {
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
			int cid =Integer.parseInt(request.getParameter("cid"));
			String pTitle = request.getParameter("pTitle");
			String pContent =request.getParameter("pContent");
			String pCode = request.getParameter("pCode");
			Part part =request.getPart("pic");
			HttpSession session = request.getSession();
			User user =(User) session.getAttribute("current");
			
			Post post = new Post(pTitle,pContent,pCode,part.getSubmittedFileName(),null,cid,user.getId());
			PostDao dao = new PostDao(ConnectionProvider.getConnection());
			
			if(dao.savePost(post)) {
				
				
				String path1 =request.getRealPath("/blog_pics")+File.separator+part.getSubmittedFileName();
				
				Helper.savefile(part.getInputStream(), path1);
				out.println("done");
			}else{
				out.println("error");
			}
			
//			out.println(pTitle);
//			out.println(part.getSubmittedFileName());
		// doGet(request, response);
	}

}
