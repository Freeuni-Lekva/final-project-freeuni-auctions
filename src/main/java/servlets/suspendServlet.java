package servlets;

import dao.UserDAO;
import models.ForeignUser;

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
        ForeignUser user = (ForeignUser) req.getAttribute(ForeignUser.ATTRIBUTE);
        UserDAO users = (UserDAO) req.getAttribute(UserDAO.ATTRIBUTE);
        try {
            users.setSuspended(user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/suspended_profile.jsp");
        disp.forward(req,res);
    }
}
