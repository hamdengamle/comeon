<%--
  Created by IntelliJ IDEA.
  User: Julius
  Date: 29-03-2017
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<%
    if(session.getAttribute("admin")==null){
        response.sendRedirect("index.jsp");
    }
    out.print(session.getId());
%>

<br>
Welcome admin ${admin}
<form class="form-signin" method="POST" action="AdminPostTxtServlet">
    Enter text to users : <textarea name="text">Din text her</textarea>
    <button type="submit">Send message</button>
</form>

<form class="form-signin" method="GET" action="LogoutServlet">
    <button type="submit">Logout</button>
</form><br>

</body>
</html>
