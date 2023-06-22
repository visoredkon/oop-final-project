package SIAKAD.models;

import SIAKAD.constants.Role;
import SIAKAD.utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginData {
    private String username;
    private String password;
    private Role role;

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassowrd(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    private void setRole(Role role) {
        this.role = role;
    }

    // Mengambil data login dari database berdasarkan username
    public static LoginData getLoginData(String username) {
        String tableName =
                "(SELECT kode AS username, password, 'Admin' AS role FROM admin UNION ALL SELECT"
                    + " nip AS username, password, 'Dosen' AS role FROM dosen UNION ALL SELECT nim"
                    + " AS username, password, 'Mahasiswa' AS role FROM mahasiswa) AS data";
        String[] columns = {"username", "password", "role"};
        String condition = "username = '" + username + "'";

        ResultSet resultSet = DB.select(tableName, columns, condition);
        LoginData loginData = null;

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String retrievedUsername = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    Role role = Role.fromString(resultSet.getString("role"));

                    if (role != null) {
                        loginData = new LoginData();
                        loginData.setUsername(retrievedUsername);
                        loginData.setPassowrd(password);
                        loginData.setRole(role);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        DB.closeConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return loginData;
    }
}
