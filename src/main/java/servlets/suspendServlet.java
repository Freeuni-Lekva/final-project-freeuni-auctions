package servlets;

import dao.UserDAO;
import models.ForeignUser;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/suspend")
public class suspendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDAO users = (UserDAO) getServletContext().getAttribute(UserDAO.ATTRIBUTE);
        User user = users.getUserByID(Long.parseLong(req.getParameter("id")), true);
        try {
            users.setSuspended(user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher disp = req.getRequestDispatcher("suspended_profile.jsp");
        disp.forward(req,res);
    }
}
