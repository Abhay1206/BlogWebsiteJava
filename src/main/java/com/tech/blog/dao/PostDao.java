package com.tech.blog.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
// import com.mysql.cj.xdevapi.Statement;
import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;

public class PostDao {
	private Connection connection;
	
	public PostDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public ArrayList<Category> getAllCategory(){
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			
			String query ="select * from categories";
			java.sql.Statement st = this.connection.createStatement();
			java.sql.ResultSet set = st.executeQuery(query);
			while(set.next()) {
				
				int cid = set.getInt("cid");
				String name = set.getString("name");
				String description = set.getString("description");
				Category c = new Category(cid,name,description);
				list.add(c);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;
		
	}
	
	public boolean savePost(Post p) {
		boolean f =false;
		
		
		try {
			String q ="insert into posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
			PreparedStatement pst = this.connection.prepareStatement(q);
			pst.setString(1, p.getpTitle());
			pst.setString(2, p.getpContent());
			pst.setString(3, p.getpCode());
			pst.setString(4, p.getpPic());
			pst.setInt(5, p.getCatId());
			pst.setInt(6, p.getUserId());
			
			pst.executeUpdate();
			f=true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
		
		
	}
	
public List<Post> getAllposts(){
	List<Post> list = new ArrayList<Post>();
	// frtch all post
	try {
		PreparedStatement pst = connection.prepareStatement("select * from posts order by pid asc");
		java.sql.ResultSet set = pst.executeQuery();
		while(set.next()) {
			int pId =set.getInt("pid");
			String pTitle =set.getString("pTitle");
			String pConetent =set.getString("pContent");
			String pCode =set.getString("pCode");
			String pPic = set.getString("pPic");
			Timestamp date = set.getTimestamp("pDate");
			int catId = set.getInt("catId");
			int userid =set.getInt("userId");
			Post post = new Post(pId,pTitle,pConetent,pCode,pPic,date,catId,userid);
			list.add(post);
			
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return list;
}


public List<Post> getpostbyCATID(int catId){
	List<Post> list = new ArrayList<Post>();
	try {
		PreparedStatement pst = connection.prepareStatement("select * from posts where catId =?");
		pst.setInt(1,catId);
		java.sql.ResultSet set = pst.executeQuery();
		while(set.next()) {
			int pId =set.getInt("pid");
			String pTitle =set.getString("pTitle");
			String pConetent =set.getString("pContent");
			String pCode =set.getString("pCode");
			String pPic = set.getString("pPic");
			Timestamp date = set.getTimestamp("pDate");
			
			int userid =set.getInt("userId");
			Post post = new Post(pId,pTitle,pConetent,pCode,pPic,date,catId,userid);
			list.add(post);
			
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return list;
	
	
}


public Post getpostbyPostId(int postid) {
	Post post =null;
	String q ="select * from posts where pId=?";
	try {
		PreparedStatement pst = connection.prepareStatement(q);
		pst.setInt(1, postid);
		java.sql.ResultSet set =  pst.executeQuery();
		while(set.next()) {
	//	post = new Post();
			int pId =set.getInt("pid");
			String pTitle =set.getString("pTitle");
			String pConetent =set.getString("pContent");
			String pCode =set.getString("pCode");
			String pPic = set.getString("pPic");
			Timestamp date = set.getTimestamp("pDate");
			int catId = set.getInt("catId");
			int userid =set.getInt("userId");
			 post = new Post(pId,pTitle,pConetent,pCode,pPic,date,catId,userid);
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return post;
}
}

