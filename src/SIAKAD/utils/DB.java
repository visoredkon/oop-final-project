package SIAKAD.utils;

import java.sql.*;

public class DB {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/siakad";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DB() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet select(String tableName, String[] columns, String condition) {
        String query = "SELECT ";
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Connection connection = getConnection();

            if (columns != null && columns.length > 0) {
                query += String.join(",", columns);
            } else {
                query += "*";
            }
            query += " FROM " + tableName;

            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean insert(String tableName, String[] columns, Object[] values) {
        try {
            Connection connection = getConnection();
            String query =
                    "INSERT INTO " + tableName + "(" + String.join(",", columns) + ") VALUES (";

            for (int i = 0; i < values.length; i++) {
                query += "?";
                if (i < values.length - 1) {
                    query += ",";
                }
            }

            query += ")";

            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean update(
            String tableName, String[] columns, Object[] values, String condition) {
        try {
            Connection connection = getConnection();
            String query = "UPDATE " + tableName + " SET ";

            for (int i = 0; i < columns.length; i++) {
                query += columns[i] + "=?";
                if (i < columns.length - 1) {
                    query += ",";
                }
            }

            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }

            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean delete(String tableName, String condition) {
        try {
            Connection connection = getConnection();
            String query = "DELETE FROM " + tableName;

            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }

            PreparedStatement statement = connection.prepareStatement(query);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
