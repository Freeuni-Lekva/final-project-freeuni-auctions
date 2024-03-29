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
         category = categoryDAO.getFromName(categoryName);
         if(category == null){
             categoryDAO.addNewCategory(categoryName);
         }
        categoryId = categoryDAO.getFromName(categoryName).getId();

        long userId = user.getId();
        String name = request.getParameter("itemTitle");
        String description = request.getParameter("itemDescription");
        long price = Long.parseLong((request.getParameter("itemPrice")));
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String imagePath = String.valueOf(request.getSession().getAttribute("itemUploadImage"));
        Product product = new Product(userId,categoryId,name, price,endDate);
        product.setDescription(description);
        product.setImage(imagePath);
        productDAO.addProduct(product);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/my_profile.jsp");
        dispatcher.forward(request, response);
    }
}

