public class DBController {


    public static void main(String[] args) {
        DBQuery dbQuery= new DBQuery();
        System.out.println(dbQuery.getAllTasks().toString());
    }
}
