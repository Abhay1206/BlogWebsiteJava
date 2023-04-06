

<%@page import="com.tech.blog.entities.User"%>
<%@page import="com.tech.blog.dao.LikeDao"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<div class="row">
<%

User uu = (User)session.getAttribute("current");
Thread.sleep(1000);
PostDao dao = new PostDao(ConnectionProvider.getConnection());
int cid =Integer.parseInt(request.getParameter("cid"));
List<Post>  list= null;
if(cid==0){
list = dao.getAllposts();
}else{
	list =dao.getpostbyCATID(cid);
}
if(list.size()==0){
	out.println("<h3 class='display-3 text-center'>No Post in this category</h3>");
}
for(Post p:list){
	
	%>
	<div class="col-md-6 mt-2">
	
	<div class="card">
	
	<img class="card-img-top" src="blog_pics/<%=p.getpPic() %>" alt="Card image cap">
	<div class="card-body">
	<b><%=p.getpTitle() %></b>
	<br>
	 <%=p.getpContent() %>
	 <br>
	 
	
	
	</div>
	
	<div class="card-footer primary-background text-center">
	
	<%
LikeDao ldao = new LikeDao(ConnectionProvider.getConnection());
%>

	<a class="btn btn-outline-light btn-sm" onClick="doLike(<%=p.getPid() %>,<%=uu.getId() %>)" href="#"><i class="fa fa-thumbs-o-up"><span class="like-counter"><%=ldao.countLikeonPost(p.getPid()) %></span></i></a>
	
	<a class="btn btn-outline-light btn-sm" href="show_blog.jsp?post_id=<%=p.getPid() %>">Read More</a>
	
	<a class="btn btn-outline-light btn-sm" href="#"><i class="fa fa-commenting-o"><span>20</span></i></a>
	</div>
	</div>
	
	</div>
	<%
	
	
}
%>

</div>