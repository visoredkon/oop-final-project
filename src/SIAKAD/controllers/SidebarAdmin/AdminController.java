package SIAKAD.controllers.SidebarAdmin;

import SIAKAD.constants.Sex;
import SIAKAD.models.Admin;
import SIAKAD.utils.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {
    @FXML private TableView<Admin> adminTableView;
    @FXML private TableColumn<Admin, String> kodeColumn;
    @FXML private TableColumn<Admin, String> passwordColumn;
    @FXML private TableColumn<Admin, String> namaColumn;
    @FXML private TableColumn<Admin, Sex> jenisKelaminColumn;
    @FXML private TableColumn<Admin, String> nomorTeleponColumn;
    @FXML private TableColumn<Admin, String> alamatColumn;

    private final ObservableList<Admin> dataAdmin = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kodeColumn.setCellValueFactory(new PropertyValueFactory<>("kode"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        jenisKelaminColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        nomorTeleponColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        adminTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        loadData();
    }

    private void loadData() {
        adminTableView.setItems(dataAdmin);
        dataAdmin.clear();
        String tableName = "admin";
        String condition = null;
        ResultSet resultSet = DB.select(tableName, null, condition);

        try {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setKode(resultSet.getString("kode"));
                admin.setPassword(resultSet.getString("password"));
                admin.setName(resultSet.getString("name"));
                admin.setSex(Sex.fromString(resultSet.getString("sex")));
                admin.setPhone(resultSet.getString("phone"));
                admin.setAddress(resultSet.getString("address"));

                dataAdmin.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
