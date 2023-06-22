package SIAKAD.controllers;

import SIAKAD.constants.Role;
import SIAKAD.models.LoginData;
import SIAKAD.utils.AlertMessage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginGUIController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    @FXML
    private void initialize() {
        // Inisialisasi fungsi tombol dan key listener
        loginButton.setOnAction(event -> login());
        usernameField.setOnKeyPressed(this::handleEnterKey);
        passwordField.setOnKeyPressed(this::handleEnterKey);
    }

    // Fungsi untuk menangani event key enter
    private void handleEnterKey(KeyEvent event) {
        // Jika key yang ditekan adalah enter, maka lakukan login
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Autentikasi username dan password
        LoginData loginData = authenticateAndGetData(username, password);

        // Jika username dan password benar, maka tampilkan halaman sesuai role
        if (loginData != null) {
            navigateToMainView(loginData.getRole(), username);
        } else {
            AlertMessage.showError("Login Gagal", "Username atau password salah!");
            usernameField.getStyleClass().add("invalid-login");
            passwordField.getStyleClass().add("invalid-login");
        }
    }

    // Fungsi untuk mengambil data login dari database berdasarkan username
    private LoginData authenticateAndGetData(String username, String password) {
        try {
            LoginData loginData = LoginData.getLoginData(username);

            if (loginData != null && password.equals(loginData.getPassword())) {
                return loginData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Fungsi untuk menampilkan halaman sesuai role
    private void navigateToMainView(Role role, String username) {
        try {
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            // Ambil nama file fxml berdasarkan role
            String fxmlName = "../views/" + role.toString() + "View.fxml";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            Parent mainView = loader.load();

            Object controller = loader.getController();

            if (controller != null) {
                if (role == Role.ADMIN && controller instanceof AdminGUIController) {
                    AdminGUIController adminController = (AdminGUIController) controller;
                    adminController.setUsernameText(username);
                } else if (role == Role.DOSEN && controller instanceof DosenGUIController) {
                    DosenGUIController dosenController = (DosenGUIController) controller;
                    dosenController.setUsernameText(username);
                } else if (role == Role.MAHASISWA && controller instanceof MahasiswaGUIController) {
                    MahasiswaGUIController mahasiswaController =
                            (MahasiswaGUIController) controller;
                    mahasiswaController.setUsernameText(username);
                }
            }

            // Ganti judul window dan tampilkan halaman
            currentStage.setTitle(role.toString() + " - SIAKAD");
            currentStage.setScene(new Scene(mainView));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
