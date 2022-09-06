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
    <title>Sign Up</title>
</head>
<body>
    <h1>Create New Account</h1>

    <form action="create_acc" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email"><br>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password">
        <input type="submit" value="Login"><br><br>
    </form>

    <a href="login">Already have an account?</a>
</body>
</html>
