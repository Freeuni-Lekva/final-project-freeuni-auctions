package servlets;

import dao.UserDAO;
import models.RegularUser;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "Create", value = "/create_acc")
public class CreateAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO users = (UserDAO) getServletContext().getAttribute(UserDAO.ATTRIBUTE);
        try {
            if(users.contains(req.getParameter("email"))){
                RequestDispatcher disp = req.getRequestDispatcher("email_in_use.jsp");
                disp.forward(req,resp);
            } else if(users.containsUsername(req.getParameter("username"))){
                RequestDispatcher disp = req.getRequestDispatcher("username_in_use.jsp");
                disp.forward(req,resp);
            }
            else {
                User user = new RegularUser(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"), 0);
                users.addUser(user);
                RequestDispatcher disp = req.getRequestDispatcher("profile.jsp");
                disp.forward(req,resp);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

