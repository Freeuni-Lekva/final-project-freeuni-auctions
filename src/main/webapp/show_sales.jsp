<%@ page import="dao.SaleDAO" %>
<%@ page import="models.Sale" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: nnayeno
  Date: 9/10/22
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SaleDAO sal = (SaleDAO) application.getAttribute(SaleDAO.ATTRIBUTE);
    List<Sale> sales = sal.getAll();
%>
<html>
<head>
    <title>Sales</title>
    <style>
        body {
            background-image: url("images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }
    </style>
</head>
<body>
<h1 style="color: #A67079">All Sales</h1>
<%
    for(Sale sale : sales){
        out.println("<hr>");
        out.println("<p>" + sale.toString() + "</p>");
        out.println("<hr>");
    }
%>
</body>
</html>

