package servlets;

import dao.UserDAO;
import models.ForeignUser;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "foreign_profile", value = "/foreign_profile")
public class GetForeignProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //following try/catch block serves testing purposes.

        UserDAO users = (UserDAO) getServletContext().getAttribute(UserDAO.ATTRIBUTE);
        User us = users.getUserByID(Long.parseLong(req.getParameter("id")), true);
        try {
            if (users.isSuspended(us.getEmail())) {
                req.setAttribute(ForeignUser.ATTRIBUTE, us);
                req.getRequestDispatcher("suspended_profile.jsp").forward(req, res);
            } else {
                req.setAttribute(ForeignUser.ATTRIBUTE, us);
                req.getRequestDispatcher("foreign_profile.jsp").forward(req, res);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
