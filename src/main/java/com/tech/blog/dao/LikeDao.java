package com.tech.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDao {
private Connection connection;
	
	public LikeDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public boolean insertLike(int pid,int uid) {
		boolean f= false;
		try {
			String q ="insert into liked(pid,uid) values(?,?)";
			PreparedStatement pst = connection.prepareStatement(q);
		  pst.setInt(1, pid);
		  pst.setInt(2, uid);
		  
		  pst.executeUpdate();
				f=true;	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	public int countLikeonPost(int pid) {
		int count=0;
		String q ="select count(*) from liked where pid=?";
		try {
			PreparedStatement ps = connection.prepareStatement(q);
			ps.setInt(1, pid);
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				count =set.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}

  public boolean isLiked(int pid,int uid) {
	  boolean f=false;
	  
	  try {
		  PreparedStatement p = connection.prepareStatement("select * from liked where pid=? and uid=?");
		  p.setInt(1, pid);
		  p.setInt(2, uid);
		  ResultSet set =p.executeQuery();
		  if(set.next()) {
			  f=true;
		  }
		  
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  
	  return f;
	  
  }

  public boolean deleteLike(int pid,int uid) {
	  boolean f=false;
	  
	  try {
		  PreparedStatement pst = connection.prepareStatement("delete from liked where pid=? and uid=?");
		  pst.setInt(1, pid);
		  pst.setInt(2, uid);
		  pst.executeUpdate();
		  f=true;
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  
	  return f;
  }
}

