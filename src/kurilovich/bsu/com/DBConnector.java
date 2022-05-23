package kurilovich.bsu.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DBConnector {
//    private static Connection connection;
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            FileInputStream fis;
//            Properties property = new Properties();
//            try {
//                fis = new FileInputStream("src/database.properties");
//                property.load(fis);
//
//            } catch (IOException e) {
//                System.err.println("ОШИБКА: Файл свойств отсуствует!");
//            }
//            try {
//                connection = DriverManager.getConnection(property.getProperty("url"), property.getProperty("username"), property.getProperty("password"));
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//    public static void returnConnection() throws SQLException {
//        connection.close();
//        connection = null;
//    }
    private static final Queue<Connection> queue = new ConcurrentLinkedDeque<>();

    static {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/database.properties");
            property.load(fis);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        try {
            for (int i = 0; i < 5; i++) {
                Connection connection = DriverManager.getConnection(property.getProperty("url"), property.getProperty("username"), property.getProperty("password"));
                queue.add(connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return queue.poll();
    }

    public static void returnConnection(Connection connection) {
        queue.add(connection);
    }

}
