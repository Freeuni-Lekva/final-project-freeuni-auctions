package servlets;

import models.ForeignUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
        req.getRequestDispatcher("/WEB-INF/foreign_profile.jsp").forward(req, res);
    }
}
