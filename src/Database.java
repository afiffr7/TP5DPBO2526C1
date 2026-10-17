import java.sql.*;

// create class Database
public class Database {
    private Connection connection;
    private Statement statement;

    // Constructor
    public Database(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost: 3306/db_trader", "root", "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Select query method
    public ResultSet Fetch(String query){
        try {
            statement.executeQuery(query);
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // insert, update, delete query method
    public int ManipulateData(String query){
        try {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // getter
    public Statement getStatement(){
        return statement;
    }
}
