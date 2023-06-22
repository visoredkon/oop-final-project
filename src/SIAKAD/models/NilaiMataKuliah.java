package SIAKAD.models;

import SIAKAD.utils.DB;

public class NilaiMataKuliah {
    private MataKuliah mataKuliah;
    private Mahasiswa mahasiswa;
    private double nilai;

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getKodeMataKuliah() {
        return mataKuliah.getKode();
    }

    public String getNamaMataKuliah() {
        return mataKuliah.getName();
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public String getNimMahasiswa() {
        return mahasiswa.getNim();
    }

    public String getNamaMahasiswa() {
        return mahasiswa.getName();
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    // Mengupdate nilai mahasiswa pada mata kuliah tertentu
    public Boolean updateNilai(double nilai) {
        String tableName = "nilai_mata_kuliah";
        String[] columns = {"nilai"};
        Object[] values = {nilai};
        String condition =
                "kode_mata_kuliah = '"
                        + mataKuliah.getKode()
                        + "' AND mahasiswa_nim = '"
                        + mahasiswa.getNim()
                        + "'";

        boolean success = DB.update(tableName, columns, values, condition);

        return success;
    }
}
