package SIAKAD.models;

import SIAKAD.utils.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MataKuliah extends KodeEntityAbstract {
    private String kode;
    private String namaMataKuliah;
    private String prodi;
    private int sks;
    private Dosen dosenPengajar;

    @Override
    public String getKode() {
        return kode;
    }

    @Override
    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getName() {
        return namaMataKuliah;
    }

    public void setName(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public Dosen getDosenPengajar() {
        return dosenPengajar;
    }

    public void setDosenPengajar(Dosen dosenPengajar) {
        this.dosenPengajar = dosenPengajar;
    }

    public String getNameDosenPengajar() {
        return dosenPengajar.getName();
    }

    // Mengambil data mata kuliah dari database berdasarkan kode
    public static MataKuliah retrieveFromDatabase(String kode) {
        String tableName = "mata_kuliah";
        String condition = "kode = '" + kode + "'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        MataKuliah mataKuliah = null;

        try {
            if (resultSet.next()) {
                mataKuliah = new MataKuliah();
                mataKuliah.setKode(resultSet.getString("kode"));
                mataKuliah.setName(resultSet.getString("nama_mata_kuliah"));
                mataKuliah.setProdi(resultSet.getString("prodi"));
                mataKuliah.setSks(resultSet.getInt("sks"));
                mataKuliah.setDosenPengajar(
                        Dosen.retrieveFromDatabase(resultSet.getString("dosen_pengajar")));
            } else {
                return mataKuliah;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mataKuliah;
    }
}
