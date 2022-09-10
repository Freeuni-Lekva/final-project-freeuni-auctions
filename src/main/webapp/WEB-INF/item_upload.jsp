<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Item upload</title>
    <style>
        body {
            background-image: url("../images/img.png");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }
    </style>
</head>
<body>
<div>
    <link href="../styles/item_upload.css" rel="stylesheet"/>
    <div class="home-container">
        <h1 class="home-heading">Upload item form</h1>
        <div class="home-container1">
            <div class="home-container2">
                <%
                    String file_name = (String) request.getParameter("filename");
                    if (file_name == null || file_name.equals("images/no-image-icon.png")) {
                        session.setAttribute("itemUploadImage", "images/no-image-icon.png");
                %>
                <br>
                <img src="../images/no-image-icon.png" width="250" height="250" alt="image" class="home-image">
                <%
                } else {
                %><br>
                <img width="250" height="250" alt="image" class="home-image" src="images/itemImages/<%=file_name%>">
                <%
                        session.setAttribute("itemUploadImage", "images/itemImages/"+file_name);
                        out.println(file_name + " File uploaded successfully");
                    }
                %>
                <form action="itemImageUpload" enctype="multipart/form-data" method="post" class="home-form">
                    Select <input style="text-align: left" type="file" name="file2"/><br>
                    <input type="submit" value="upload"/>
                </form>
            </div>
            <form action="itemUploadSubmit" method="post" class="home-form-submit">

                <div class="home-container3">
                    <div class="home-title-container">
                        <span class="home-title-lable">Title</span>
                        <input
                                required
                                id="itemTitle"
                                name="itemTitle"
                                type="text"
                                placeholder="Title"
                                class="home-title-input input"
                        />
                    </div>
                    <div class="home-description-container">
                        <span class="home-descripton-lable">Description</span>
                        <input
                                required
                                id="itemDescription"
                                name="itemDescription"
                                type="text"
                                placeholder="Description"
                                class="home-description-input input"
                        />
                    </div>
                    <div class="home-price-container">
                        <span class="home-price-lable">Starting Price</span>
                        <input
                                required
                                id="itemPrice"
                                name="itemPrice"
                                type="number"
                                placeholder="Price"
                                class="home-price-input input"
                        />
                    </div>
                    <div class="home-category-container">
                        <span class="home-category-lable">Category</span>
                        <input
                                required
                                id="itemCategory"
                                name="itemCategory"
                                type="text"
                                placeholder="Category"
                                class="home-category-input input"
                        />
                    </div>
                    <div class="home-date-container">
                        <span class="home-date-lable">End Date</span>
                        <input required
                               class="home-date-input"
                               type="date" id="endDate" name="endDate"
                               value="2022-09-11"
                               min="2022-09-11" max="2025-09-11">
                    </div>
                </div>
                <input type="submit" value="Submit" class="home-submit"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>


