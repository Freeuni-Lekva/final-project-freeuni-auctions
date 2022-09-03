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
    <h1 class ="no-background" style="color: #A67079">Enter Your Email and Password</h1>

    <form action="login" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" style="background-color:#FFE7EB; margin: 10px" > <br>
        <div class="space">
        </div>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password" style="background-color:#FFE7EB; margin: 10px" >
        <input type="submit" value="Login"><br><br>
    </form>

    <a href="create_acc">Create new Account</a>
</body>
<%--<body>--%>
<%--<div class="background-image"></div>--%>
<%--</body>--%>
<%--<style>--%>
<%--    *{--%>
<%--        margin: 0;--%>
<%--        padding: 0;--%>
<%--    }--%>
<%--    .background-image{--%>
<%--        background-image: url("images/bg.jpg");--%>
<%--        /*background-size: cover;*/--%>
<%--        /*background-repeat: no-repeat;*/--%>
<%--        /*height: 100vh;*/--%>
<%--    }--%>

</html>
