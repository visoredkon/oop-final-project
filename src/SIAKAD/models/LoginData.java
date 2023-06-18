package SIAKAD.models;

import SIAKAD.constants.Role;
import SIAKAD.utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginData {
    private String username;
    private String password;
    private Role role;

    public LoginData(String data, String password, Role role) {
        this.username = data;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

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
                        loginData = new LoginData(retrievedUsername, password, role);
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
