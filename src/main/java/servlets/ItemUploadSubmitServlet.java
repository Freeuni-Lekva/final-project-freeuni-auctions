package servlets;

import dao.CategoryDAO;
import dao.ProductDAO;
import models.Category;
import models.Product;
import models.Status;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "itemUploadSubmit", value = "/itemUploadSubmit")
public class ItemUploadSubmitServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
        CategoryDAO categoryDAO = (CategoryDAO) getServletContext().getAttribute(CategoryDAO.ATTRIBUTE);
        User user = (User) request.getSession().getAttribute(User.ATTRIBUTE);
        String categoryName = request.getParameter("itemCategory");
        long categoryId ;
        Category category ;
        try {
             category = categoryDAO.getFromName(categoryName);
             if(category == null){
                 categoryDAO.addNewCategory(categoryName);
             }
            categoryId = categoryDAO.getFromName(categoryName).getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        long userId = user.getId();
        String name = request.getParameter("itemTitle");
        String description = request.getParameter("itemDescription");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("itemPrice")));
        Date datePosted = new Date(System.currentTimeMillis());
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String imagePath = String.valueOf(request.getSession().getAttribute("itemUploadImage"));
        Product product = new Product(0, userId,categoryId,name,description,null,
                                price,Status.available,datePosted,endDate,imagePath);
        productDAO.addProduct(product);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("my_profile.jsp");
        dispatcher.forward(request, response);
    }
}

