package servlets;

import dao.UserDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balance", value = "/balance")
public class BalanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("balance.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long newBalance = Long.parseLong(req.getParameter("amount"));
        User user = (User)(req.getSession().getAttribute(User.ATTRIBUTE));
        user.setBalance(newBalance);
        UserDAO userDAO = (UserDAO)getServletContext().getAttribute(UserDAO.ATTRIBUTE);
        userDAO.addUser(user);
        req.getRequestDispatcher("balance.jsp").forward(req, res);
    }
}
