package servlets;

import dao.ReportDAO;
import models.ForeignUser;
import models.Report;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "report", value = "/report")
public class ReportServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportDAO reportDAO = (ReportDAO) getServletContext().getAttribute(ReportDAO.ATTRIBUTE);

        User reporter = (User) request.getSession().getAttribute(User.ATTRIBUTE);
        long reportedID = Long.parseLong(request.getParameter("reportedId"));
        String message = request.getParameter("message");
        Report report = new Report(0, reporter.getId(), reportedID, message, false);

        try {
            reportDAO.addReport(report);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/foreign_profile?id=" + reportedID);
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/foreign_profile?id=" + reportedID + "");
//        dispatcher.forward(request, response);
    }
}
