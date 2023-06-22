package SIAKAD.controllers;

import SIAKAD.constants.Role;
import SIAKAD.models.Dosen;
import SIAKAD.models.LoginData;
import SIAKAD.models.Mahasiswa;

public class LoggedController {
    private static String username;

    public LoggedController(String username) {
        LoggedController.username = username;
    }

    public static String getUsername() {
        return username;
    }

    // Mengecek apakah user yang sedang login adalah admin
    public static boolean isAdmin() {
        LoginData loginData = LoginData.getLoginData(username);
        if (loginData.getRole() == Role.ADMIN) {
            return true;
        }
        return false;
    }

    public static Dosen getDosen() {
        return Dosen.retrieveFromDatabase(username);
    }

    public static Mahasiswa getMahasiswa() {
        return Mahasiswa.retrieveFromDatabase(username);
    }
}
