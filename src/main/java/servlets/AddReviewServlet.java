package servlets;

import dao.SaleDAO;
import dao.ReviewDAO;
import models.Review;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Review", value = "/add_review")
public class AddReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReviewDAO reviewDAO = (ReviewDAO)getServletContext().getAttribute(ReviewDAO.ATTRIBUTE);
        SaleDAO saleDAO = (SaleDAO) getServletContext().getAttribute(SaleDAO.ATTRIBUTE);
        long user_id = Long.parseLong(req.getParameter("userId"));
        String review_text = req.getParameter("reviewText");
        long customerId = Long.parseLong(req.getParameter("reviewerId"));
        try {
            reviewDAO.addReview(new Review(-1, user_id, -1, customerId, review_text));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/foreign_profile.jsp?id=" + user_id);
        dispatcher.forward(req, resp);
    }
}
