package servlets;

import dao.UserDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing context");
        try {
            Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sce.getServletContext().setAttribute(UserDAO.ATTRIBUTE, new UserDAO());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
