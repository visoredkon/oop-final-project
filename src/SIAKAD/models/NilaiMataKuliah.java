package SIAKAD.models;

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
}