package SIAKAD.models;

import SIAKAD.constants.Role;
import SIAKAD.constants.Sex;
import SIAKAD.interfaces.Contactable;
import SIAKAD.interfaces.KodeEntity;
import SIAKAD.utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends UserAbstract implements Contactable, KodeEntity {
    private String kode;

    @Override
    public String getKode() {
        return kode;
    }

    @Override
    public void setKode(String kode) {
        this.kode = kode;
    }

    @Override
    public String getUsername() {
        return kode;
    }

    @Override
    public String getEmail() {
        return kode + "@admin.cupid.ac.id";
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    // Mengambil data admin dari database berdasarkan kode
    public static Admin retrieveFromDatabase(String kode) {
        String tableName = "admin";
        String condition = "kode = '" + kode + "'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        Admin admin = null;

        try {
            if (resultSet.next()) {
                admin = new Admin();
                admin.setKode(resultSet.getString("kode"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admin.setSex(Sex.fromString(resultSet.getString("sex")));
                admin.setPhone(resultSet.getString("phone"));
                admin.setAddress(resultSet.getString("address"));
            } else {
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    // Menambahkan admin baru ke dalam database
    public boolean addAdmin() {
        try {
            String[] columns = {"username", "password", "name", "sex", "phone", "email", "address"};
            Object[] values = {
                getUsername(),
                getPassword(),
                getName(),
                getSex().toString(),
                getPhone(),
                getEmail(),
                getAddress()
            };

            boolean success = DB.insert("admin", columns, values);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mengupdate data admin di dalam database berdasarkan kode
    public boolean updateAdmin(String kode) {
        try {
            kode = kode == null || kode.isEmpty() ? this.kode : kode;

            String[] columns = {"username", "password", "name", "sex", "phone", "email", "address"};
            Object[] values = {
                getUsername(),
                getPassword(),
                getName(),
                getSex().toString(),
                getPhone(),
                getEmail(),
                getAddress()
            };

            boolean success = DB.update("admin", columns, values, "kode=" + this.kode);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Menghapus data admin dari database berdasarkan kode
    public boolean deleteAdmin() {
        try {
            boolean success = DB.delete("admin", "kode=" + kode);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
