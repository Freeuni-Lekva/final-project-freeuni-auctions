<%--
  Created by IntelliJ IDEA.
  User: nnayeno
  Date: 9/3/22
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tryAgain</title>
</head>
<body>
<h1>Email or Password is incorrect.</h1>
<p>please try again</p>

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
