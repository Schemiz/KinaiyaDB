import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_connection {

	static String connectionUrl = "jdbc:sqlserver://localhost:1433; "
                                + "databaseName= Kinaiya_DB;"
                                + "username= Kinaiya_Database;"
                                + "password= 12345;"
                                +";encrypt=true;trustServerCertificate=true;";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
                System.out.println("Connected successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
