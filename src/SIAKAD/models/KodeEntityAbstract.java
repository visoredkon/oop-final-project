package SIAKAD.models;

import SIAKAD.interfaces.KodeEntity;

public abstract class KodeEntityAbstract implements KodeEntity {
    protected String kode;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}