package servlets;

import dao.ProductDAO;
import models.Product;
import models.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Product", value = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("productId"));
        ProductDAO dao = (ProductDAO)getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
        Product product = dao.getFromID(productId);
        Status status = product.getStatus();
        switch (status) {
            case available:
                req.setAttribute(Product.ATTRIBUTE, product);
                req.getRequestDispatcher("item.jsp").forward(req, res);
                break;
            case sold:
                req.getRequestDispatcher("sold.jsp").forward(req, res);
                break;
            case timed_out:
                req.getRequestDispatcher("timed_out.jsp").forward(req, res);
                break;
        }
    }
}
