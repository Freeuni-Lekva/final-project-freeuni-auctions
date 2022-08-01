package dao;

import database.DataBaseConnection;
import junit.framework.TestCase;

import java.sql.*;

public class DAOTest extends TestCase {
    protected Connection con;

    protected void setUp() throws SQLException, ClassNotFoundException {
        con = DataBaseConnection.getConnection("test_db");
    }
    /* change this logic when we have database connection or some similar class */
    /* this is specific to my machine */
    protected Connection establishDBConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/test_db/?user=root&password=";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
