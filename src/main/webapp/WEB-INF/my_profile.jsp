<%@ page import="dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="dao.ReviewDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="models.*" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.09.2022
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
    <style>
        body {
            background-image: url("../images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }
        .no-background {
            background-image: url("../images/bg.jpg");
        }
        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #aa0473;
            color: white;
        }

        .topnav-right {
            float: right;
        }
        .tab {
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
        }

        /* Style the buttons inside the tab */
        .tab button {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 17px;
        }

        /* Change background color of buttons on hover */
        .tab button:hover {
            background-color: #ddd;
        }

        /* Create an active/current tablink class */
        .tab button.active {
            background-color: #ccc;
        }

        /* Style the tab content */
        .tabcontent {
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-top: none;
        }
    </style>
</head>
<body>
    <div class="topnav">
        <div class="topnav-right">
            <a href="homepage.jsp">Home</a>
            <a href="logout">Log out</a>
        </div>
    </div>
    <% User user = (User) request.getSession().getAttribute(User.ATTRIBUTE); %>

    <h1><%=user.getUsername()%></h1>
    <img src="../images/blank-profile-picture.png" alt="profile picture" width="150" height="200">
    <h3><%=user.getEmail()%></h3>
    <h2>Balance: $<%=user.getBalance()%></h2>
    <form action="balance" method="get">
        <input type="submit" value="Update Balance">
    </form>
    <p></p>
    <div class="tab">
        <button class="tablinks" onclick="openTab('Products')">Products</button>
        <button class="tablinks" onclick=openTab('Reviews')>Reviews</button>
    </div>
    <div id="Products" class="tabcontent">
        <ul>
            <h3>Products</h3>
            <%
                ProductDAO productDAO = (ProductDAO)application.getAttribute(ProductDAO.ATTRIBUTE);
                List<Product> products = productDAO.getProductsByUser(user.getId());
                for(Product p : products) {

            %>
            <li><a href="product?productId=<%=p.getId()%>"><%=p.getName()%></a></li>
            <%
                }
            %>
        </ul>
    </div>
    <div id="Reviews" class="tabcontent">
        <h3>Reviews</h3>
    </div>

    <script>
        function openTab(tabName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tabName).style.display = "block";
            evt.currentTarget.className += " active";
        }
    </script>

<a href="../item_upload.jsp">UPLOAD YOUR ITEM</a><br>
<%
    ((User) request.getSession().getAttribute(User.ATTRIBUTE)).makeChanges();
%>
</body>
</html>
