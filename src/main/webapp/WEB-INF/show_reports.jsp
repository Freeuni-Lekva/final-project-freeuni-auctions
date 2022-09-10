<%@ page import="dao.ReportDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Report" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.security.NoSuchAlgorithmException" %><%--
  Created by IntelliJ IDEA.
  User: nnayeno
  Date: 9/9/22
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ReportDAO rep = (ReportDAO) application.getAttribute(ReportDAO.ATTRIBUTE);
    List<Report> actives = rep.getUnresolved();
    UserDAO users = (UserDAO) application.getAttribute(UserDAO.ATTRIBUTE);
%>
<html>
<head>
    <title>reports</title>
</head>
<body>
    <h1>Active Reports</h1>
    <%
        for(Report report : actives){
            out.println("<hr>");
            out.println("<hr>\n" +
                    "<a href=\"foreign_profile.jsp\" style=\"float: right\">Reported: </a>\n" + users.getUserByID(report.getReportedId(), true).getUsername()+
                    "\n" +
                    "<p>" + report.getComment() +
                    "</p>\n" +
                    "\n" +
                    "<a href=\"foreign_profile.jsp\" style=\"float: left\">Reporter: </a><br>\n" + users.getUserByID(report.getReporterId(), true).getUsername()+
                    "\n" +
                    "<hr>");
            out.println("<hr>");
        }
    %>
</body>
</html>
