package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BEmerson
 */
public class SetupDb {

    static final Logger logger = Logger.getLogger(DBManager.class.getName());

    void createTables() {

        DBManager dmbgr = new DBManager();

        Connection con = dmbgr.getConnection();

        InputStream inpStr = this.getClass().getResourceAsStream("createdb.sql");

        executeSqlScript(con, inpStr);
    }

    void insertSetupData() {

        DBManager dmbgr = new DBManager();

        Connection con = dmbgr.getConnection();
        
        InputStream inpStr = this.getClass().getResourceAsStream("insertdata.sql");

        executeSqlScript(con, inpStr);
    }

    public void showData() {

        Statement stmt;

        DBManager dmbgr = new DBManager();

        Connection con = dmbgr.getConnection();

        try {
            stmt = con.createStatement();
            try (ResultSet results = stmt.executeQuery("select * from USERDATA")) {
                System.out.println("\n-------------------------------------------------");
                
                while (results.next()) {
                    int id = results.getInt(1);
                    String email = results.getString(2);
                    String password = results.getString(3);
                    String fName = results.getString(4);
                    String lName = results.getString(5);
                    String userType = results.getString(6);
                    logger.log(Level.INFO, "{0}\t\t{1}\t\t{2}\t\t{3}\t\t{4}\t\t{5}", new Object[]{id, email, password, fName, lName, userType});
                }
            }
            stmt.close();
        } catch (SQLException sqlExcept) {
            logger.log(Level.SEVERE, null, sqlExcept);
        }

    }

public void executeSqlScript(Connection conn, InputStream inputStream) {
    String delimiter = ";";
    try {
        if (conn.isClosed()) {
            logger.log(Level.SEVERE, "Error: Connection is closed");
            return;
        }
        try (Scanner scanner = new Scanner(inputStream).useDelimiter(delimiter);
             Statement statement = conn.createStatement()) {
            while (scanner.hasNext()) {
                String rawStatement = scanner.next();
                try {
                    statement.execute(rawStatement);
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error executing statement: " + rawStatement, e);
                }
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error creating statement", e);
    }
}


}

