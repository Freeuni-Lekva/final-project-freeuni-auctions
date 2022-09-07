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
    <style>
        body {
            background-image: url("images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }
        .no-background {
            background-image: url("images/bg.jpg");
        }
    </style>
</head>
<body>
    <h1 class ="no-background" style="color: #A67079">Create New Account</h1>

    <form action="create_acc" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" style="background-color:#FFE7EB; margin: 10px"><br>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" style="background-color:#FFE7EB; margin: 10px"><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password" style="background-color:#FFE7EB; margin: 10px">
        <input type="submit" value="Login"><br><br>
    </form>

    <a href="login_page.jsp">Already have an account?</a><br>
    <a href="profile">Enter as Guest</a> <%-- "profile is set here for testing purposes--%>
</body>
</html>
