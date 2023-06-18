package SIAKAD.models;

import SIAKAD.constants.Sex;
import SIAKAD.constants.Role;
import SIAKAD.interfaces.Contactable;

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
}