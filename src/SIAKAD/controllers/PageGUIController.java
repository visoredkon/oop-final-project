package SIAKAD.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PageGUIController {
    void showSidebarContent(String contentMenu, StackPane contentStackPane, String controllerName) {
        if (contentMenu != "Dashboard") {
            if (controllerName.equals("AdminGUI")) {
                contentMenu = "SidebarAdmin/" + contentMenu;
            } else if (controllerName.equals("DosenGUI")) {
                contentMenu = "SidebarDosen/" + contentMenu;
            } else if (controllerName.equals("MahasiswaGUI")) {
                contentMenu = "SidebarMahasiswa/" + contentMenu;
            }
        }

        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("../views/" + contentMenu + "View.fxml"));
            Parent view = loader.load();
            contentStackPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void logout(Button logoutButton) {
        try {
            Stage currentStage = (Stage) logoutButton.getScene().getWindow();
            Parent mainView = FXMLLoader.load(getClass().getResource("../views/LoginView.fxml"));
            currentStage.setTitle("Login");
            currentStage.setScene(new Scene(mainView));
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
