import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List getDatabaseContent(String SQLQuery) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(SQLQuery);
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            List listOfResults = new ArrayList();

            for (int i=1; i<=columnCount; i++) {
                Array column = results.getArray(columnNames[i-1]);
                listOfResults.add(results.getArray(columnNames[i-1]));
            }
            return listOfResults;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Something went wrong, check your tablestructure...");
            return new ArrayList();
        }
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
