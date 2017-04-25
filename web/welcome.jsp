<%@ page import="com.julius.BusinessLogic.Calculations" %><%--
  Created by IntelliJ IDEA.
  User: Julius
  Date: 29-03-2017
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<%
    if(session.getAttribute("username")==null){
        response.sendRedirect("index.jsp");
    }
    Calculations BL = new Calculations();
    String messages = BL.getMessages();
    session.setAttribute("messages", messages);
%>

Welcome ${username}<br>
Nye beskeder:<br>
<textarea readonly rows="4" cols="50">${messages}</textarea><br>
<form class="form-signin" method="GET" action="LogoutServlet">
    <button type="submit">Logout</button>
</form>

</body>
</html>