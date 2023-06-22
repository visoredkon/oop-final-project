package SIAKAD.models;

import SIAKAD.constants.Role;
import SIAKAD.constants.Sex;
import SIAKAD.interfaces.Contactable;
import SIAKAD.utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mahasiswa extends UserAbstract implements Contactable {
    private String nim;
    private String prodi;
    private double ipk;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public String getUsername() {
        return nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public double getIpk() {
        return ipk;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    @Override
    public String getEmail() {
        return nim + "@student.cupid.ac.id";
    }

    @Override
    public Role getRole() {
        return Role.MAHASISWA;
    }

    // Mengambil data mahasiswa dari database berdasarkan NIM
    public static Mahasiswa retrieveFromDatabase(String nim) {
        String tableName = "mahasiswa";
        String condition = "nim = '" + nim + "'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        Mahasiswa mahasiswa = null;

        try {
            if (resultSet.next()) {
                mahasiswa = new Mahasiswa();
                mahasiswa.setNim(resultSet.getString("nim"));
                mahasiswa.setPassword(resultSet.getString("password"));
                mahasiswa.setProdi(resultSet.getString("prodi"));
                mahasiswa.setName(resultSet.getString("name"));
                mahasiswa.setSex(Sex.fromString(resultSet.getString("sex")));
                mahasiswa.setPhone(resultSet.getString("phone"));
                mahasiswa.setAddress(resultSet.getString("address"));
                mahasiswa.setIpk(resultSet.getDouble("ipk"));
            } else {
                return mahasiswa; // Mengembalikan false jika nim tidak ditemukan
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mahasiswa;
    }

    // Menambahkan data mahasiswa ke database
    public boolean addMahasiswa() {
        try {
            String[] columns = {"nim", "password", "prodi", "name", "sex", "phone", "address"};
            Object[] values = {
                nim, getPassword(), getName(), prodi, getSex().toString(), getPhone(), getAddress()
            };

            boolean success = DB.insert("mahasiswa", columns, values);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Mengubah data mahasiswa di database
    public boolean updateMahasiswa(String nim) {
        try {
            nim = nim == null || nim.isEmpty() ? this.nim : nim;

            String[] columns = {"nim", "password", "prodi", "name", "sex", "phone", "address"};
            Object[] values = {
                nim, getPassword(), getName(), prodi, getSex().toString(), getPhone(), getAddress()
            };

            boolean success = DB.update("mahasiswa", columns, values, "nim=" + this.nim);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Menghapus data mahasiswa dari database
    public boolean deleteMahasiswa() {
        try {
            String tableName = "mahasiswa";
            String condition = "nim = '" + nim + "'";
            boolean success = DB.delete(tableName, condition);

            if (success) return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
