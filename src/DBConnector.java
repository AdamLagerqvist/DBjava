import javax.swing.*;
import java.sql.*;

public class DBConnector {
    Connection conn;

    // Should take all data as input instead
    public DBConnector() {
        //JPasswordField pf = new JPasswordField();
        //JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //String password = new String(pf.getPassword());

        try {
            // Set up connection to database
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + DB.Host() + ":3306/" + DB.DB() + "? " +
                            "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DB.UserName(), DB.Password());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to log in, check your database and credentials and try again. Shutting down...");
            System.exit(0);
        }
    }

    public String getDatabaseContent() {
        String result = "";
        try {
            // Setup statement
            Statement stmt = conn.createStatement();

            // Create query and execute
            String SQLQuery = "select * from admlat_tasks";
            ResultSet rset = stmt.executeQuery(SQLQuery);

            // Loop through the result set and convert to String
            // Need to know the table-structure
            while (rset.next()) {
                result += rset.getInt("id") + ", " +
                        rset.getString("task") + ", " +
                        rset.getBoolean("completed") + ", " +
                        rset.getTimestamp("created_at") + ", " +
                        rset.getTimestamp("updated_at") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Something went wrong, check your tablestructure...");
            return "Error reading result from SQL-query";
        }
        return result;
    }

    public String getTableInfo() {
        String result = "";
        try {
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM admlat_tasks");

            // Get resultset metadata
            ResultSetMetaData metadata = results.getMetaData();
            int columnCount = metadata.getColumnCount();

            // Get the column names; column indices start from 1
            for (int i=1; i<=columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                result += columnName + " ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
