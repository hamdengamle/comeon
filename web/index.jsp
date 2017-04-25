<%--
  Created by IntelliJ IDEA.
  User: Julius
  Date: 29-03-2017
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>WebApp</title>
</head>
<body>
<form class="form-signin" method="POST" action="LoginServlet">
  Enter username : <input type="text" name="uName">
  Enter password : <input type="password" name="pWord">
  <button type="submit">Login</button>
</form>

<form action="newUser.jsp">
  <button type="submit">Opret ny bruger</button>
</form>

</body>
</html>
