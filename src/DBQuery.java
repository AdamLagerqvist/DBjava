import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBQuery {
    DBConnector dbconn = new DBConnector();

    public List getAllTasks() {
        String SQLQuery = "select * from admlat_tasks";
        List rset = dbconn.getDatabaseContent(SQLQuery);
        return rset;
    }
}
