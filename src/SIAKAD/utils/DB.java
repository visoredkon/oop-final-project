package SIAKAD.utils;

import java.sql.*;

public class DB {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/siakad";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static Connection connection;

    // Mendapatkan koneksi ke database
    public static Connection getConnection() {
        try {
            // Mengecek apakah koneksi sudah ada atau sudah ditutup
            // Jika tidak ada atau sudah ditutup, maka membuat koneksi baru
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Mengambil data pada tabel dan kolom tertentu (bisa dengan kondisi)
    public static ResultSet select(String tableName, String[] columns, String condition) {
        String query = "SELECT ";
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Connection connection = getConnection();

            // Mengecek kolom yang akan diambil
            // Jika tidak ada maka ambil semua data pada tabel
            if (columns != null && columns.length > 0) {
                query += String.join(",", columns);
            } else {
                query += "*";
            }
            query += " FROM " + tableName;

            // Mengecek kondisi dan menambahkan WHERE clause jika ada kondisi
            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }

            // Melakukan eksekusi query dan mengembalikan hasil ResultSet
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Menutup statement dan koneksi setelah selesai
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

    // Memasukkan data ke dalam tabel dan kolom tertentu
    public static boolean insert(String tableName, String[] columns, Object[] values) {
        try {
            Connection connection = getConnection();
            String query =
                    "INSERT INTO " + tableName + "(" + String.join(",", columns) + ") VALUES (";

            // Menambahkan parameter '?' sesuai dengan jumlah nilai yang akan dimasukkan
            for (int i = 0; i < values.length; i++) {
                query += "?";
                if (i < values.length - 1) {
                    query += ",";
                }
            }

            query += ")";

            // Mengatur nilai-nilai pada parameter '?'
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            // Melakukan eksekusi query dan mengembalikan hasil boolean (berhasil atau tidak)
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi setelah selesai
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Mengupdate data pada tabel dan kolom tertentu (bisa dengan kondisi)
    public static boolean update(
            String tableName, String[] columns, Object[] values, String condition) {
        try {
            Connection connection = getConnection();
            String query = "UPDATE " + tableName + " SET ";

            // Membangun bagian SET dalam query dengan kolom dan nilai yang diinginkan
            for (int i = 0; i < columns.length; i++) {
                query += columns[i] + "=?";
                if (i < columns.length - 1) {
                    query += ",";
                }
            }

            // Mengatur nilai-nilai pada parameter '?'
            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }

            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            // Melakukan eksekusi query dan mengembalikan hasil boolean (berhasil atau tidak)
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi setelah selesai
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Menghapus data pada tabel tertentu (bisa dengan kondisi)
    public static boolean delete(String tableName, String condition) {
        try {
            Connection connection = getConnection();
            String query = "DELETE FROM " + tableName;

            if (condition != null && !condition.isEmpty()) {
                query += " WHERE " + condition;
            }

            // Melakukan eksekusi query dan mengembalikan hasil boolean (berhasil atau tidak)
            PreparedStatement statement = connection.prepareStatement(query);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi setelah selesai
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Menghapus semua data pada tabel tertentu
    public static boolean truncate(String tableName) {
        try {
            Connection connection = getConnection();
            String query = "TRUNCATE TABLE " + tableName;

            // Melakukan eksekusi query dan mengembalikan hasil boolean (berhasil atau tidak)
            PreparedStatement statement = connection.prepareStatement(query);
            int rowsTruncated = statement.executeUpdate();
            return rowsTruncated == 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi setelah selesai
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Menutup koneksi ke database
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
