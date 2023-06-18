package SIAKAD.controllers;

import SIAKAD.models.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AdminGUIController {
    @FXML private Text usernameText;
    @FXML private Text namaText;
    @FXML private Button dashboardButton;
    @FXML private Button adminButton;
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
                event -> controller.showSidebarContent("Dashboard", contentStackPane, "AdminGUI"));
        adminButton.setOnAction(
                event -> controller.showSidebarContent("Admin", contentStackPane, "AdminGUI"));
        dosenButton.setOnAction(
                event -> controller.showSidebarContent("Dosen", contentStackPane, "AdminGUI"));
        mahasiswaButton.setOnAction(
                event -> controller.showSidebarContent("Mahasiswa", contentStackPane, "AdminGUI"));
        mataKuliahButton.setOnAction(
                event -> controller.showSidebarContent("MataKuliah", contentStackPane, "AdminGUI"));
        mataKuliahNilaiButton.setOnAction(
                event ->
                        controller.showSidebarContent(
                                "NilaiMataKuliah", contentStackPane, "AdminGUI"));
        logoutButton.setOnAction(event -> controller.logout(logoutButton));
    }

    public void setUsernameText(String username) {
        new LoggedController(username);

        usernameText.setText(username);
        retrieveAdmin();
    }

    private void retrieveAdmin() {
        String username = usernameText.getText();
        Admin admin = Admin.retrieveFromDatabase(username);
        namaText.setText(admin.getName());
    }
}
