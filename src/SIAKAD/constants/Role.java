package SIAKAD.constants;

public enum Role {
    MAHASISWA("Mahasiswa"),
    DOSEN("Dosen"),
    ADMIN("Admin");

    private final String roleString;

    Role(String roleString) {
        this.roleString = roleString;
    }

    @Override
    public String toString() {
        return roleString;
    }

    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.roleString.equals(role)) {
                return r;
            }
        }
        return null;
    }
}
