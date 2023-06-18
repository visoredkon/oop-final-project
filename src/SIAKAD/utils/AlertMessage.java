package SIAKAD.utils;

import SIAKAD.App;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertMessage {
    public static void showInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        title = title != null ? title : "Information";
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        setAlertIcon(alert);
        alert.showAndWait();
    }

    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        title = title != null ? title : "Error";
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        setAlertIcon(alert);
        alert.showAndWait();
    }

    private static void setAlertIcon(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("resources/logoSIAKAD.png")));
    }
}
