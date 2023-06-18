package SIAKAD.models;

public class MataKuliah extends KodeEntityAbstract {
    private String kode;
    private String namaMataKuliah;
    private String prodi;
    private int sks;
    private Dosen dosenPengajar;

    public String getKode() {
        return kode;
    }

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
}