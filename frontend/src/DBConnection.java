import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {

        Connection conn = null;

        try {

            String url =
                    "jdbc:sqlite:hospital.db";

            conn =
                    DriverManager.getConnection(url);

            System.out.println(
                    "Database Connected"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return conn;
    }
}