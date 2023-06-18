package SIAKAD.models;

import SIAKAD.constants.Role;
import SIAKAD.constants.Sex;
import SIAKAD.interfaces.Contactable;
import SIAKAD.utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dosen extends UserAbstract implements Contactable {
    private String nip;
    private String prodi;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public String getUsername() {
        return nip;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    @Override
    public String getEmail() {
        return nip + "@lecturer.cupid.ac.id";
    }

    @Override
    public Role getRole() {
        return Role.DOSEN;
    }

    public static Dosen retrieveFromDatabase(String nip) {
        String tableName = "dosen";
        String condition = "nip = '" + nip + "'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        Dosen dosen = null;

        try {
            if (resultSet.next()) {
                dosen = new Dosen();
                dosen.setNip(resultSet.getString("nip"));
                dosen.setPassword(resultSet.getString("password"));
                dosen.setName(resultSet.getString("name"));
                dosen.setProdi(resultSet.getString("prodi"));
                dosen.setSex(Sex.fromString(resultSet.getString("sex")));
                dosen.setPhone(resultSet.getString("phone"));
                dosen.setAddress(resultSet.getString("address"));
            } else {
                return dosen;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dosen;
    }

    public boolean addDosen() {
        try {
            String[] columns = {
                "nip", "password", "name", "prodi", "sex", "phone", "email", "address"
            };
            Object[] values = {
                nip,
                getPassword(),
                getName(),
                prodi,
                getSex().toString(),
                getPhone(),
                getEmail(),
                getAddress()
            };

            boolean success = DB.insert("dosen", columns, values);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDosen(String nip) {
        try {
            nip = nip == null || nip.isEmpty() ? this.nip : nip;

            String[] columns = {
                "nip", "password", "name", "prodi", "sex", "phone", "email", "address"
            };
            Object[] values = {
                nip,
                getPassword(),
                getName(),
                prodi,
                getSex().toString(),
                getPhone(),
                getEmail(),
                getAddress()
            };

            boolean success = DB.update("dosen", columns, values, "nip=" + this.nip);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDosen() {
        try {
            boolean success = DB.delete("dosen", "nip=" + nip);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
