package servlets;

import dao.ProductDAO;
import models.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductDAO prods = (ProductDAO) getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
        req.setAttribute(Product.ATTRIBUTE, prods.getFromID((Long) req.getAttribute("prodID")));
        System.out.println("here");
        RequestDispatcher disp = req.getRequestDispatcher("item.jsp");
        disp.forward(req,res);
    }
}
