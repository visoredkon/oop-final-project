package SIAKAD.controllers;

import SIAKAD.utils.AlertMessage;
import SIAKAD.utils.DB;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardGUIController {
    @FXML private TextArea announcementText;

    @FXML
    private void initialize() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(
                event -> {
                    if (LoggedController.isAdmin()) {
                        announcementText.setEditable(true);
                        announcementText.requestFocus();
                        announcementText
                                .textProperty()
                                .addListener(
                                        (observable, oldValue, newValue) -> {
                                            updateContent(newValue);
                                        });

                        announcementText.setOnKeyPressed(
                                keyEvent -> {
                                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                                        announcementText.setEditable(false);
                                    }
                                });
                    } else {
                        AlertMessage.showError(
                                "Akses Ditolak", "Hanya admin yang dapat merubah pengumuman.");
                    }
                });

        contextMenu.getItems().add(editMenuItem);
        announcementText.setContextMenu(contextMenu);

        announcementText.setText(getContent());
    }

    private static String getContent() {
        String tableName = "pengumuman";
        String condition = "status = 'Active'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        try {
            if (resultSet.next()) {
                return resultSet.getString("content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean updateContent(String value) {
        String publisher = LoggedController.getUsername();

        String tableName = "pengumuman";
        String[] columns = {"status", "publisher", "content"};
        Object[] values = {"Active", publisher, value};
        String condition = "status = 'Active'";

        boolean success = DB.update(tableName, columns, values, condition);

        return success;
    }
}
