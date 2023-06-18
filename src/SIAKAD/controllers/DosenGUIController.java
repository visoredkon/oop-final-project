package SIAKAD.controllers;

import SIAKAD.models.Dosen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DosenGUIController {
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
        PageGUIController controller = new PageGUIController();

        dashboardButton.setOnAction(
                event -> controller.showSidebarContent("Dashboard", contentStackPane, "DosenGUI"));
        dosenButton.setOnAction(
                event -> controller.showSidebarContent("Dosen", contentStackPane, "DosenGUI"));
        mahasiswaButton.setOnAction(
                event -> controller.showSidebarContent("Mahasiswa", contentStackPane, "DosenGUI"));
        mataKuliahButton.setOnAction(
                event -> controller.showSidebarContent("MataKuliah", contentStackPane, "DosenGUI"));
        mataKuliahNilaiButton.setOnAction(
                event ->
                        controller.showSidebarContent(
                                "NilaiMataKuliah", contentStackPane, "DosenGUI"));
        logoutButton.setOnAction(event -> controller.logout(logoutButton));
    }

    public void setUsernameText(String username) {
        new LoggedController(username);

        usernameText.setText(username);
        retrieveDosen();
    }

    private void retrieveDosen() {
        String username = usernameText.getText();
        Dosen dosen = Dosen.retrieveFromDatabase(username);
        namaText.setText(dosen.getName());
    }
}
