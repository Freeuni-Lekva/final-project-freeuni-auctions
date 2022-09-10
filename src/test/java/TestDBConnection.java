import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test_db";
    private static final String user = "root";
    private static final String password = "";
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

    public static void resetDatabase() {
        if (instance != null) {
            String content = null;
            try {
                content = new String(Files.readAllBytes(Paths.get("test.sql")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] arr = content.split(";");
            for (int i = 0; i < arr.length; i++) {
                try {
                    instance.createStatement().execute(arr[i]);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
