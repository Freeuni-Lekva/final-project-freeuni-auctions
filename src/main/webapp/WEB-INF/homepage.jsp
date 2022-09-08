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
<img src="../images/img_1.png" alt="sch.com" width="104" height="142">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<form class="search" action="post">
    <input type="text" placeholder="Search.." name="search">
    <button type="submit"><i class="fa fa-search"></i></button>
</form>

<p>Products:</p>
<div class="row">
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
