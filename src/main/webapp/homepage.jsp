<%@ page import="dao.ProductDAO" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Status" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="models.User" %>
<%@ page import="dao.CategoryDAO" %>
<%@ page import="models.Category" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06.09.2022
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDAO products = (ProductDAO) application.getAttribute(ProductDAO.ATTRIBUTE);
    products.refresh();
    Product prod;
    List<Product> prods = (ArrayList<Product>) request.getAttribute("items_to_display");
    CategoryDAO categories = (CategoryDAO) application.getAttribute(CategoryDAO.ATTRIBUTE);
    List<Category> cat;
    try {
         cat = categories.getAll();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>
<html>
<head>
    <title>Auctions: home</title>
    <style>
        * {
            box-sizing: border-box;
        }

        .column {
            position: relative;
            float: left;
            width: 33.33%;
            height: 0;
            padding: 5px;
        }

        /* Clearfix (clear floats) */
        .row::after {
            content: "";
            clear: both;
            display: table;
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

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #ddd;}

        .dropdown:hover .dropdown-content {display: block;}

        .dropdown:hover .dropbtn {background-color: #3e8e41;}
        body {
            background-image: url("images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }
    </style>
</head>
<body>
<a href="my_profile.jsp" style= "float: right"> My Profile </a>
<form action="search" method="post">
    <label for="selected_category">Choose a Category:</label>
    <select id="selected_category" name="selected_category">
        <%
            for (Category category : cat) {
                out.println("<option value=\""+category.getName()+"\">"+category.getName()+"</option>");
            }
        %>


    </select>
    <input type="submit">
</form>



<form action="search" method="post">
    <label for="search_phrase">Search:</label>
    <input type="text" id="search_phrase" name="search_phrase" style="background-color:#FFE7EB; margin: 10px" > <br>
    <div class="space">
    </div>
</form>
<div class="row">
    <%
        if(prods == null)
            prods = products.getProductsByStatus(Status.available);
        PrintWriter o = response.getWriter();
        for (Product product : prods) {
            prod = product;
           %>
    <div class="column">
        <img src="<%=prod.getImage()%>" alt=<%=prod.getName()%> width=250px height=250px">
        <form action="item" method="post">
            <input type="hidden" id="prodId" name="prodId" value=<%=prod.getId()%>>
            <input type="submit" id="item" name="item" value=<%=prod.getName()%>>
        </form>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
