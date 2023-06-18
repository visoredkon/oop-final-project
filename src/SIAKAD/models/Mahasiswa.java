package SIAKAD.models;

import SIAKAD.constants.Sex;
import SIAKAD.constants.Role;
import SIAKAD.interfaces.Contactable;

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
}