package servlets;

import dao.CategoryDAO;
import dao.ProductDAO;
import models.Category;
import models.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(name = "Search", value="/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute(ProductDAO.ATTRIBUTE);
        CategoryDAO categoryDAO = (CategoryDAO) getServletContext().getAttribute(CategoryDAO.ATTRIBUTE);
        String phrase = (String) req.getAttribute("search_phrase");
        String category_name = (String) req.getAttribute("selected_category");
        Category category = null;
        ArrayList<Product> results = new ArrayList<>();
        if (category_name != null) {
            try {
                category = categoryDAO.getFromName(category_name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (phrase != null) {
            results = productDAO.getProductsByName(phrase);
            if (category != null) {
                final Category finalCategory = category;
                results = results.stream().filter(p -> p.getCategoryId() == finalCategory.getId()).collect(Collectors.toCollection(ArrayList::new));
            }
        } else if (category != null) {
            results = new ArrayList<>(productDAO.getProductsByCategory(category.getId()));
        }
        req.setAttribute("items_to_display", results);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/homepage.jsp");
        dispatcher.forward(req, resp);
    }
}
