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
        ProductDAO prods = (ProductDAO) getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
        if(prods == null)
            System.out.println("null on prods");
        Product product = prods.getFromID(Long.parseLong(req.getParameter("prId")));
        System.out.println(product.getName());
        long bid = Long.parseLong(req.getParameter("bid"));
        long price = product.getCurrPrice();
        String errorMessage = "";
        if (bid <= price) {
            errorMessage = "Your bid must be higher than the price.";
        } else if (balance < bid) {
            errorMessage = "You don't have that amount on your balance.";
        } else {
            //update price and balance in the objects and in the databases.
            product.setPrice(bid);
            loggedInUser.setBalance(balance - bid);
            ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
            productDAO.addProduct(product);
            UserDAO userDAO = (UserDAO)getServletContext().getAttribute(UserDAO.ATTRIBUTE);
            userDAO.addUser(loggedInUser);
        }
        req.setAttribute("ERROR", errorMessage);
        req.setAttribute(Product.ATTRIBUTE, product);
        req.getRequestDispatcher("item.jsp").forward(req, res);
    }
}
