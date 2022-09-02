<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.07.2022
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Enter Your Username And Password</h1>

    <form action="login" method="post">
        <label for="user">User name:</label>
        <input type="text" id="user" name="user"><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password">
        <input type="submit" value="Login"><br><br>
    </form>

    <a href="create_acc">Create new Account</a>
</body>
</html>
