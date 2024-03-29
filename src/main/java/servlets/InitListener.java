package servlets;

import dao.*;
import models.Administrator;
import models.RegularUser;
import models.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class InitListener implements ServletContextListener, HttpSessionListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing context");
        try {
            Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DBConnection.getInstance();
        // create all connections here?
        sce.getServletContext().setAttribute(UserDAO.ATTRIBUTE, new UserDAO(con));
        sce.getServletContext().setAttribute(ProductDAO.ATTRIBUTE, new ProductDAO(con));
        sce.getServletContext().setAttribute(CategoryDAO.ATTRIBUTE, new CategoryDAO(con));
        sce.getServletContext().setAttribute(ReportDAO.ATTRIBUTE, new ReportDAO(con));
        sce.getServletContext().setAttribute(ReviewDAO.ATTRIBUTE, new ReviewDAO(con));
        sce.getServletContext().setAttribute(SaleDAO.ATTRIBUTE, new SaleDAO(con));
        sce.getServletContext().setAttribute(BidDAO.ATTRIBUTE, new BidDAO(con));
        UserDAO users = (UserDAO)  sce.getServletContext().getAttribute(UserDAO.ATTRIBUTE);
        try {
            users.addAdmin(new Administrator(0, "admin", "admin", "admin", "blank-profile-picture,png", 0));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // FOLLOWING BLOCK FOR TESTING(creates a user object as main profile user)
        try {
            httpSessionEvent.getSession().setAttribute(User.ATTRIBUTE, new RegularUser("sandro", "sandro", "sandro@gm.com", "sandro", 12));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
