package SIAKAD.constants;

public enum Sex {
    LAKI_LAKI("Laki-laki"),
    PEREMPUAN("Perempuan");

    private final String sexString;

    Sex(String sexString) {
        this.sexString = sexString;
    }

    @Override
    public String toString() {
        return sexString;
    }

    public static Sex fromString(String sex) {
        for (Sex s : Sex.values()) {
            if (s.sexString.equals(sex)) {
                return s;
            }
        }
        return null;
    }
}
