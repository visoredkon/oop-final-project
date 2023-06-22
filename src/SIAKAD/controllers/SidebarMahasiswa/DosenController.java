package SIAKAD.controllers.SidebarMahasiswa;

import SIAKAD.constants.Sex;
import SIAKAD.controllers.LoggedController;
import SIAKAD.models.Dosen;
import SIAKAD.utils.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DosenController {
    @FXML private TableView<Dosen> dosenTableView;
    @FXML private TableColumn<Dosen, String> nipColumn;
    @FXML private TableColumn<Dosen, String> namaColumn;
    @FXML private TableColumn<Dosen, String> prodiColumn;
    @FXML private TableColumn<Dosen, Sex> jenisKelaminColumn;
    @FXML private TableColumn<Dosen, String> nomorTeleponColumn;
    @FXML private TableColumn<Dosen, String> alamatColumn;

    private final ObservableList<Dosen> dataDosen = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        nipColumn.setCellValueFactory(new PropertyValueFactory<>("nip"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodiColumn.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        jenisKelaminColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        nomorTeleponColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        dosenTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        loadData();
    }

    private void loadData() {
        dosenTableView.setItems(dataDosen);
        dataDosen.clear();
        String tableName = "dosen";
        String condition = "prodi = '" + LoggedController.getMahasiswa().getProdi() + "'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        try {
            while (resultSet.next()) {
                Dosen dosen = new Dosen();
                dosen.setNip(resultSet.getString("nip"));
                dosen.setPassword(resultSet.getString("password"));
                dosen.setName(resultSet.getString("name"));
                dosen.setSex(Sex.fromString(resultSet.getString("sex")));
                dosen.setProdi(resultSet.getString("prodi"));
                dosen.setPhone(resultSet.getString("phone"));
                dosen.setAddress(resultSet.getString("address"));

                dataDosen.add(dosen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
