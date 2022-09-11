package servlets;

import dao.SaleDAO;
import dao.ReviewDAO;
import dao.UserDAO;
import models.Product;
import models.Review;
import models.Sale;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Review", value = "add_review")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReviewDAO reviewDAO = (ReviewDAO)getServletContext().getAttribute(ReviewDAO.ATTRIBUTE);
        SaleDAO saleDAO = (SaleDAO) getServletContext().getAttribute(SaleDAO.ATTRIBUTE);
        long user_id = Long.parseLong(req.getParameter("userId"));
        String review_text = req.getParameter("reviewText");
        User customer = (User) req.getSession().getAttribute(User.ATTRIBUTE);
        try {
            reviewDAO.addReview(new Review(-1, user_id, -1, customer.getId(), review_text));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/foreign_profile.jsp");
        //dispatcher.forward(req, resp);
    }
}
