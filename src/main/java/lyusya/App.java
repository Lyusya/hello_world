package lyusya;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        //утсанавливаем соединение
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            doCreate(conn);
            doInsert(conn);
            doSelect(conn);
            doUpdate(conn);
            doSelect(conn);
            doDelete(conn);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * создание таблицы
     */
    private static void doCreate(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS TEST(ID INT PRIMARY KEY,  NAME VARCHAR(255))");
        ps.executeUpdate();
        ps.close();
    }

    /**
     * добавление данных в таблицу
     */
    private static void doInsert(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO TEST VALUES(1, 'Hello')");
        stmt.close();
    }

    /**
     * извлечение данны из таблицы
     */
    private static void doSelect(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from TEST");
        while (rs.next()) {
            logger.severe(String.format("id = %s, name = %s", rs.getString("id"), rs.getString("name")));
        }
        stmt.close();
    }

    /**
     * обнавление данных в таблице
     */
    private static void doUpdate(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE TEST SET NAME='Hi' WHERE ID=1");
        stmt.close();
    }

    /**
     * удаление данных из таблицы
     */
    private static void doDelete(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM TEST WHERE ID=1");
        ps.executeUpdate();
        stmt.close();
        logger.severe("data deleted");
    }
}
