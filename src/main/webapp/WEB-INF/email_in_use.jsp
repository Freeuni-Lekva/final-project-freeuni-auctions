<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 30.07.2022
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invalid SignUp</title>
</head>
<body>
    <h1>Email already in use/</h1>
    <p>please try again</p>

    <form action="create_acc" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email"><br>
        <label for="user">User name:</label>
        <input type="text" id="user" name="user"><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password">
        <input type="submit" value="Login"><br><br>
    </form>

    <a href="login">Already have an account?</a>
</body>
</html>
