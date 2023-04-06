package com.tech.blog.dao;
import java.sql.*;


import com.tech.blog.entities.User;
public class UserDao {

	private Connection connection;

	public UserDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public boolean saveUser(User user) {
		boolean f=false;
		try {
			
			String query ="insert into user(name,email,password,gender,about) values(?,?,?,?,?)";
		PreparedStatement pstm=	this.connection.prepareStatement(query);
		pstm.setString(1, user.getName());
		pstm.setString(2, user.getEmail());
		pstm.setString(3, user.getPassword());
		pstm.setString(4, user.getGender());
		pstm.setString(5, user.getAbout());
		pstm.executeUpdate();
		f=true;
		
		}catch(Exception e) {
		e.printStackTrace();
	}
		return f;
}
	
	
	
	public User getUserByEmailandPassword(String email,String password) {
		User user = null;
		try {
			String query=" select * from user where email=? and password=?";
			PreparedStatement pstm = this.connection.prepareStatement(query);
			pstm.setString(1, email);
			pstm.setString(2, password);
		
		ResultSet  set =	pstm.executeQuery();
		
		if(set.next()) {
			user = new User();
			String name=set.getString("name");
			user.setName(name);
			user.setId(set.getInt("id"));
			user.setEmail(set.getString("email"));
			user.setPassword(set.getString("password"));
			user.setGender(set.getString("gender"));
			user.setAbout(set.getString("about"));
			user.setDateTime(set.getTimestamp("rdate"));
			user.setProfile(set.getString("profile"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	public boolean updateUser(User user) {
		boolean flag=false;
		try {
			String query="update user set name=? , email=?, password=? ,gender=?,about=?,profile=? where id=?";
			PreparedStatement p = connection.prepareStatement(query);
			p.setString(1, user.getName());
			p.setString(2, user.getEmail());
			p.setString(3, user.getPassword());
			p.setString(4, user.getGender());
			p.setString(5, user.getAbout());
			p.setString(6,user.getProfile());
			p.setInt(7, user.getId());
			p.executeUpdate();
			flag=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public User getuserbyPostid(int userId) {
		
		
		User user =null;
		
		String q = "select * from user where id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(q);
			pst.setInt(1, userId);
			java.sql.ResultSet set = pst.executeQuery();
			if(set.next()) {
				user = new User();
				String name=set.getString("name");
				user.setName(name);
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				user.setAbout(set.getString("about"));
				user.setDateTime(set.getTimestamp("rdate"));
				user.setProfile(set.getString("profile"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}
