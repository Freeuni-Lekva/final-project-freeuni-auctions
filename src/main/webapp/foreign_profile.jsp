<%@ page import="dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ReviewDAO" %>
<%@ page import="models.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="dao.SaleDAO" %>
<%@ page import="dao.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.09.2022
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${username}</title>
    <style>
        body {
            background-image: url("images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }
        .no-background {
            background-image: url("images/bg.jpg");
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
    <link href="styles/report.css" rel="stylesheet"/>
</head>
<body>
    <div class="topnav">
        <a href="profile">My Profile</a>
        <div class="topnav-right">
            <a href="homepage.jsp">Home</a>
            <a href="logout">Log out</a>
        </div>
    </div>
    <%  UserDAO userDAO = (UserDAO)application.getAttribute(UserDAO.ATTRIBUTE);
        ForeignUser user = (ForeignUser) userDAO.getUserByID(Long.parseLong(request.getParameter("id")), true); %>
    <h1><%=user.getUsername()%></h1>
    <img src="images/blank-profile-picture.png" alt="profile picture" width="150" height="200">
    <h3><%=user.getEmail()%></h3>
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
        <%
            User signed_in = (User)request.getSession().getAttribute(User.ATTRIBUTE);
            long signed_in_id = signed_in.getId();
            long foreign_id = user.getId();
            SaleDAO sales = (SaleDAO)application.getAttribute(SaleDAO.ATTRIBUTE);
            List<Sale> saleList = sales.getSalesByUserID(signed_in_id);
            boolean hasReviewRights = false;
            for (Sale s : saleList) {
                if (productDAO.getFromID(s.getProduct_id()).getUserId() == foreign_id)
                    hasReviewRights = true;
            }
            if (true) {
        %>
            <form action="add_review" method="post">
                <textarea name="reviewText" id="review" rows="10" tabindex="4"  required="required"></textarea>
                <input type="hidden" name="userId" value="<%=foreign_id%>">
                <input type="hidden" name="reviewerId" value="<%=signed_in_id%>">
            <input name="submit" type="submit" value="Submit Review"/>
            </form>
        <%
            }

        %>
        <h3>Reviews</h3>
        <ul>
            <h3>Reviews</h3>
            <%

                ReviewDAO reviewDAO = (ReviewDAO)application.getAttribute(ReviewDAO.ATTRIBUTE);
                try {
                    List<Review> reviews = reviewDAO.getAllReviewsForAccount(user.getId());
                    for(Review r : reviews) {
            %>
            <li><a> <%=r.getReviewText()%></a></li>
            <%
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            %>
        </ul>
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
    <%
        if(((User)request.getSession().getAttribute(User.ATTRIBUTE)).getRole() == Role.Administator) {
            out.println("<form action=\"suspend\" method=\"post\">\n" +
                    "        <input type=\"submit\" value=\"suspend\"><br><br>\n" +
                    "    </form>");
        }else{
    %>
    <button class="open-button" onclick="openForm()">Report User</button>

    <div class="form-popup" id="myForm">
        <form action="/report" method="post" class="form-container">
            <h1>Report</h1>

            <input type="hidden" id="reportedId" name="reportedId" value="<%=user.getId()%>">

            <label for="message"><b>Message</b></label>
            <input type="text" placeholder="Enter report reason" id="message" name="message" required>

            <button type="submit" class="btn">Submit</button>
            <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
        </form>
    </div>

    <script>
        function openForm() {
            document.getElementById("myForm").style.display = "block";
        }

        function closeForm() {
            document.getElementById("myForm").style.display = "none";
        }
    </script>
    <% }%>
</body>
</html>
