package servlets;

import dao.UserDAO;
import models.RegularUser;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDAO users = (UserDAO) getServletContext().getAttribute(UserDAO.ATTRIBUTE);
        //req.getParameter("Username"), req.getParameter("Password")
        String hashed = "";
        try {
            hashed = User.hashPassword(req.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            if(users.correct(req.getParameter("email"), hashed)){
                RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/homepage.jsp");
                req.getSession().setAttribute(User.ATTRIBUTE, users.getUserByEmail(req.getParameter("email"), false));
                disp.forward(req,res);
            } else {
                RequestDispatcher disp = req.getRequestDispatcher("tryAgain.jsp");
                disp.forward(req,res);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
