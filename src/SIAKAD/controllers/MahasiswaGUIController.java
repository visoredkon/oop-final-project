package SIAKAD.controllers;

import SIAKAD.models.Mahasiswa;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MahasiswaGUIController {
    @FXML private Text usernameText;
    @FXML private Text namaText;
    @FXML private Button dashboardButton;
    @FXML private Button dosenButton;
    @FXML private Button mahasiswaButton;
    @FXML private Button mataKuliahButton;
    @FXML private Button mataKuliahNilaiButton;
    @FXML private Button logoutButton;
    @FXML private StackPane contentStackPane;

    @FXML
    private void initialize() {
        // Inisialisasi fungsi tombol
        PageGUIController controller = new PageGUIController();

        dashboardButton.setOnAction(
                event ->
                        controller.showSidebarContent(
                                "Dashboard", contentStackPane, "MahasiswaGUI"));
        dosenButton.setOnAction(
                event -> controller.showSidebarContent("Dosen", contentStackPane, "MahasiswaGUI"));
        mahasiswaButton.setOnAction(
                event ->
                        controller.showSidebarContent(
                                "Mahasiswa", contentStackPane, "MahasiswaGUI"));
        mataKuliahButton.setOnAction(
                event ->
                        controller.showSidebarContent(
                                "MataKuliah", contentStackPane, "MahasiswaGUI"));
        mataKuliahNilaiButton.setOnAction(
                event ->
                        controller.showSidebarContent(
                                "NilaiMataKuliah", contentStackPane, "MahasiswaGUI"));
        logoutButton.setOnAction(event -> controller.logout(logoutButton));
    }

    public void setUsernameText(String username) {
        new LoggedController(username);

        // Menampilkan username pada header halaman
        usernameText.setText(username);
        retrieveMahasiswa();
    }

    private void retrieveMahasiswa() {
        String username = usernameText.getText();
        Mahasiswa mahasiswa = Mahasiswa.retrieveFromDatabase(username);
        // Menampilkan nama pada header halaman
        namaText.setText(mahasiswa.getName());
    }
}
