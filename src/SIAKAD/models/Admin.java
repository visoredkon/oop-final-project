package SIAKAD.models;

import SIAKAD.constants.Sex;
import SIAKAD.constants.Role;
import SIAKAD.interfaces.KodeEntity;
import SIAKAD.interfaces.Contactable;

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
}