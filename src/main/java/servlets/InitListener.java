package servlets;

import dao.UserDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(UserDAO.ATTRIBUTE, new UserDAO());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
