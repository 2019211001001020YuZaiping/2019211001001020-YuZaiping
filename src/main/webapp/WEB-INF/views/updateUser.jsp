<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/28 0028
  Time: 上午 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>User Update</h1>
<!--need form-->
<%
    //get user from session
    User u=(User) session.getAttribute("user");
%>
<form method="post" action="updateUser">
    <input type="hidden" name="id" value="<%=u.getId()%>">
    username<input type="text" name="username" value="<%=u.getUsername()%>"/><br/>
    password<input type="password" name="password" value="<%=u.getPassword()%>"/><br/>
    Email<input type="text" name="email" value="<%=u.getEmail()%>"/><br/>
    Gender <input type="radio" name="gender" value="male" <%="male".equals(u.getGender())?"checked":""%>>Male
    <input type="radio" name="gender" value="female" <%= "female".equals(u.getGender())?"checked" :""%>>Female<br/>
    <!--if name is same it makes array-->
    Birthday <input type="text" name="birthday" value="<%=u.getBirthday()%>" ><br/>
    <input type="submit" value="Save Changes" />
</form>
<%@include file="footer.jsp"%>