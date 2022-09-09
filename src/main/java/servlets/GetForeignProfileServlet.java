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
        try {
            req.setAttribute(ForeignUser.ATTRIBUTE, new ForeignUser("vigac", "ss@gg.ge", "img"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        User us = (User) req.getAttribute(ForeignUser.ATTRIBUTE);
        UserDAO users = (UserDAO) req.getAttribute(UserDAO.ATTRIBUTE);
        try {
            if(users.isSuspended(us.getEmail())){
                req.getRequestDispatcher("/WEB-INF/suspended_profile.jsp").forward(req, res);
            } else
            req.getRequestDispatcher("/WEB-INF/foreign_profile.jsp").forward(req, res);
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
