<%@ page import="dao.ProductDAO" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Status" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06.09.2022
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDAO products = (ProductDAO) application.getAttribute(ProductDAO.ATTRIBUTE);

%>
<html>
<head>
    <title>Auctions: home</title>
    * {
    box-sizing: border-box;
    }

    .column {
    float: left;
    width: 33.33%;
    padding: 5px;
    }

    /* Clearfix (clear floats) */
    .row::after {
    content: "";
    clear: both;
    display: table;
    }

</head>
<body>
<img src="../images/logo.png" alt="img" width="70" height="30" style="float: left; margin-right: 15px;"/>
<link rel="stylesheet" href="style.css">
<div class="container">
    <form action="post" class="search-bar">
        <input type="text" placeholder="Search.." name="search" style="float: left; ">
        <button type="submit"><img src="images/search-icon.png"></button>
    </form>
</div>
<div class="container-2">
    <form action="post" class="cart-bar">
        <button type="submit" style="float: left;"><img src="images/img_1.png"></button>
    </form>
</div>
<div class="container-3">
    <form action="post" class="authorization-bar">
        <button type="submit" style="float: left;">Authorization</button>
    </form>
</div>
<div class="container-4">
    <form action="post" class="login-bar">
        <button type="submit">Log In</button>
    </form>
</div>

<div class="row">
    <div class="left" style = "background-color: #bbb;">
        <h2>Categories</h2>
        <ul id="myMenu">
            <li><a href="#">category 1</a></li>
            <li><a href="#">category 2</a></li>
        </ul>
    </div>
    <%
        List<Product> prods;
        prods = products.getProductsByStatus(Status.available);
        PrintWriter o = response.getWriter();
        for (Product prod : prods) {
            out.println("<div class=\"column\">\n" +
                    "    <img src=\""+prod.getImage() +"\" + alt=\""+ prod.getName()+"\" style=\"width:100%\">\n" +
                    "    <p>"+ prod.getName()+" Price: "+ prod.getCurrPrice() +"</p>\n" +
                    "  </div>");
        }
    %>
</div>
</body>
</html>
