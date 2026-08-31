import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/officedb";
        String user = "root";
        String password = "root";

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
