package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/freeuni_auctions";
    private static final String user = "root";
    private static final String password = "root";
    private static Connection instance;


    public static Connection getInstance() {
        if (instance == null) {
            try {
                instance = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
