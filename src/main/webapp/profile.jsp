
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.entities.Message"%>
<%@page import="com.tech.blog.entities.User" %>
<%@page import="com.tech.blog.entities.Category" %>
<%@page import="com.tech.blog.dao.PostDao" %>
<%@page errorPage="error_page.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
 User user =(User)session.getAttribute("current");

if(user==null){
	response.sendRedirect("login_page.jsp");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
        <!--css-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="css/mystyles.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .banner-background{
             clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 91%, 63% 100%, 22% 91%, 0 99%, 0 0);
            }
        </style>

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark primary-background">
  <a class="navbar-brand" href="index.jsp"> <span class="fa fa-asterisk"></span>TechBlog</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"> <span class ="fa fa-bell-o"></span> LearnCode <span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="fa fa-check-square-o"></span>  Categories
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Project implement</a>
          <a class="dropdown-item" href="#">Programming action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#!" > <span class="fa fa-address-card-o"></span> Contact</a>
      </li>
      
      
        <li class="nav-item">
        <a class="nav-link" href="#" data-toggle="modal" data-target="#add-post-modal"> <span class="fa fa-asterisk"></span> DoPost</a>
      </li>
      
      
      
    </ul>
    <ul class="navbar-nav mr-right">
      <li class="nav-item">
        <a class="nav-link" href="#!" data-toggle="modal" data-target="#profile-modal"> <span class="fa fa-user-circle"></span> <%=user.getName() %> </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="LogoutServlets"> <span class="fa fa-user-plus"></span> Logout</a>
      </li>
      
    
    </ul>
  </div>
</nav>
<div class="container">
    <%
                                
                                 Message m = (Message)session.getAttribute("msg");
                                if(m!=null){
                                	
                                	 %>
                                	 <div class="alert  <%=m.getCssClass() %>" role="alert">
                                	<%= m.getContent() %>
                                	 </div>
                                	 
                                	 <% 
                                	session.removeAttribute("msg");
                                }
                                
                                
                                %>
                                </div>
                                
                                <!-- main body of page.. -->
                                
                                
                                <main>
                                
                                <div class="container">
                                <div class="row mt-4">
                                
                                <!-- first col -->
                                
                                <div class="col-md-4">
                                <!-- categoriess -->
                                <div class="list-group">
  <a href="#"  onClick="getPost(0,this)" class="c-link list-group-item list-group-item-action active">
    All posts
  </a>
  
  <%
  PostDao dao = new PostDao(ConnectionProvider.getConnection());
       ArrayList<Category> al = dao.getAllCategory();
       for(Category cc:al){
    	   
    	   %>
    	   <a href="#"  onClick="getPost(<%=cc.getCid() %>,this)" class="c-link list-group-item list-group-item-action"><%=cc.getName() %></a>
    	   <% 
       }
  
  %>
  
 
</div>
                                
                                </div>
                                <!-- seconmd col -->
                                 <div class="col-md-8">
                                <!-- postss -->
                                <div class="container text-center" id="loader">
                                <i class="fa fa-refresh fa-4x fa-spin"></i>
                                <h3 class="mt-2">Loading...</h3>
                                
                                </div>
                                
                                <div class="container-fluid"id="post-id" ></div>
                                
                                </div>
                                
                                </div>
                                
                                </div>
                                </main>
<!-- Button trigger modal -->

<!-- Modal -->
<div class="modal fade" id="profile-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header primary-background text-white text-center">
        <h5 class="modal-title" id="exampleModalLabel">Blog Site</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="container  text-center">
       <img src="pics/<%=user.getProfile()%>" class="img-fluid"  style="border-radius:50%;" width="100">
       <br>
         <h5 class="modal-title mt-3" id="exampleModalLabel"> <%=user.getName() %></h5>
      <div id="profile-detail">
      
        <table class="table">
 
  <tbody>
    <tr>
      <th scope="row">Id</th>
      <td><%=user.getId() %></td>
     
    </tr>
    <tr>
      <th scope="row">Email</th>
      <td><%=user.getEmail() %></td>
     
    </tr>
    <tr>
      <th scope="row">Gender</th>
      <td><%=user.getGender() %></td>
    
    </tr>
     <tr>
      <th scope="row">Status</th>
      <td><%=user.getAbout() %></td>
    
    </tr>
    
     <tr>
      <th scope="row">Date Registered</th>
      <td><%=user.getDateTime().toLocaleString() %></td>
    
    </tr>
  </tbody>
</table>
      
      </div>
      
      <div id="profile-edit" style="display:none;">
      <h3 class="mt-2">Please Edit Details</h3>
      <form action="EditServlets" method="post" enctype="multipart/form-data">
      
      <table class="table">
      
      <tr>
      <td>ID:</td>
        <td> <%=user.getId() %> </td>
      </tr>
      
        <tr>
      <td>Name</td>
        <td> <input type="text" name="user_name" class="form-control" value=" <%=user.getName() %> " > </td>
      </tr>
      
       <tr>
      <td>Email</td>
        <td> <input type="email" name="user_email" class="form-control" value=" <%=user.getEmail() %> " > </td>
      </tr>
        <tr>
      <td>Password</td>
        <td> <input type="password" name="user_password" class="form-control" value=" <%=user.getPassword() %> " > </td>
      </tr>
        <tr>
      <td>Status</td>
        <td> <input type="text" name="user_about" class="form-control" value=" <%=user.getAbout() %> " > </td>
      </tr>
      
        <tr>
      <td>New Profile pic</td>
        <td> <input type="file" name="user-image" class="form-control"  > </td>
      </tr>
      
      </table>
      
      <div class="container">
      <button type="submit" class="btn btn-outline-primary">Save</button>
      </div>
      
      </form>
      </div>
      
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button id="edit-profile-btn" type="button" class="btn btn-primary">Edit</button>
      </div>
    </div>
  </div>
</div>


<!-- doPoatModal --> 
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Provide Post detail</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="add-post-form" action="AddPostServlets" method="post">
        
      <div class="form-group">
      
        <select class="form-control" name="cid">
        <option selected disabled>--select category--</option>
        
        <%  
        PostDao pdao = new PostDao(ConnectionProvider.getConnection()); 
        ArrayList<Category> list = pdao.getAllCategory();
        for(Category c:list){
        	
        
        %>
     
      <option value="<%=c.getCid() %>"><%=c.getName() %></option>
       
        <%
        }
        %>
        </select>
        
      
      </div>
        <div class="form-group">
        <input name="pTitle" type="text" placeholder="Eneter the title of blog" class="form-control">
        
        </div>
        
        <div  class="form-group">
        <textarea name="pContent" class="form-control" placeholder="Enter your content " style="height:200px"></textarea>
        </div>
        
          <div  class="form-group">
        <textarea name="pCode" class="form-control" placeholder="Enter your program(if any) " style="height:200px"></textarea>
        </div>
        
        <div class="form-group">
        <label>Select your pic</label>
        <br>
        <input name="pic" type="file">
        </div>
        
        <div class="container text-center">
        <button type="submit" class="btn btn-outline-primary">Post</button>
        
        </div>
        
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
  <!--javascripts-->
        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script type="text/javascript" charset="utf-8"  src="./jquery-1.9.1.js" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <script src="js/myjs.js" type="text/javascript"></script>
        
        <script>
          $(document).ready(function(){
        	  let editStatus =false;
        	  
        	 $('#edit-profile-btn').click(function(){
        //		 alert("button clicked")
        
        if(editStatus==false){
        	   $('#profile-detail').hide();
               $('#profile-edit').show();
               editStatus=true;
               $(this).text("back")
        }else{
        	   $('#profile-detail').show();
        	 $('#profile-edit').hide();
        	 
              editStatus =false;
              $(this).text("Edit")
        }
       
        	 })
        	  
          })
        
        </script>
        
        <!-- addpost jss -->
        
        <script>
        
        
        $(document).ready(function(){
        	$('#add-post-form').on("submit",function(event){
        		// this is called when form is submitted
        		
        		event.preventDefault();
       // 		console.log("submitted this..............");
        		let form = new FormData(this);
        		// now calling server
        		$.ajax({
        			url:"AddPostServlets",
        			type:"POST",
        			data:form,
        			success:function(data,textStatus,jqXHR){
        				
        				console.log(data)
        				if(data.trim()=="done"){
        					swal("Good Job" ,"save successfully","success")
        				}else{
        					swal("Bad Job" ,"something went wrong","error")
        				}
        			},
        			error:function(jqXHR,textStatus,errorThrown){
        				
        			},
        			processData:false,
        			contentType:false,
        		})
        		
        	})
        })
        
        </script>
        
        <script>
        
        function getPost(catId,temp){
        	$('#loader').show()
        	$('#post-id').hide()
        	$('.c-link').removeClass('active')
        	$.ajax({
            	url:'load_posts.jsp',
            	data:{cid:catId},
            	success:function(data,textStatus,jqXHR){
            		console.log(data)
            		$('#loader').hide()
            		$('#post-id').show()
            		$('#post-id').html(data)
            		$(temp).addClass('active')
            	},
            	})
        }
        
        $(document).ready(function(){
        	let apref =$('.c-link')[0]
        	getPost(0,apref)
        	
        })
        
        
        
        </script>
</body>
</html>