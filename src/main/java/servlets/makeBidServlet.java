package servlets;

import dao.ProductDAO;
import dao.UserDAO;
import models.Product;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "makeBid", value = "/makeBid")
public class makeBidServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User loggedInUser = (User)req.getSession().getAttribute(User.ATTRIBUTE);
        long balance = loggedInUser.getBalance();
        Product product = (Product)req.getAttribute(Product.ATTRIBUTE);
        long bid = Long.parseLong(req.getParameter("bid"));
        long price = product.getCurrPrice();
        if (bid <= price) {

        } else if (balance < bid) {

        } else {
            product.setPrice(bid);
            loggedInUser.setBalance(balance - bid);
            ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
            productDAO.addProduct(product);
            UserDAO userDAO = (UserDAO)getServletContext().getAttribute(UserDAO.ATTRIBUTE);
            userDAO.addUser(loggedInUser);
        }
    }
}
