public class DBConnectorTest {
    public static void main(String[] args) {
        DBConnector dbconn = new DBConnector();
        String databaseContent = dbconn.getDatabaseContent();
        System.out.println(databaseContent);
        System.out.println(dbconn.getTableInfo());
    }
}
