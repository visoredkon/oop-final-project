package SIAKAD.controllers.SidebarAdmin;

import SIAKAD.constants.Sex;
import SIAKAD.models.Admin;
import SIAKAD.models.Mahasiswa;
import SIAKAD.utils.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MahasiswaController {
    @FXML private TableView<Mahasiswa> mahasiswaTableView;
    @FXML private TableColumn<Mahasiswa, String> nimColumn;
    @FXML private TableColumn<Admin, String> passwordColumn;
    @FXML private TableColumn<Mahasiswa, String> namaColumn;
    @FXML private TableColumn<Mahasiswa, String> prodiColumn;
    @FXML private TableColumn<Mahasiswa, Sex> jenisKelaminColumn;
    @FXML private TableColumn<Mahasiswa, String> nomorTeleponColumn;
    @FXML private TableColumn<Mahasiswa, String> alamatColumn;

    private final ObservableList<Mahasiswa> dataMahasiswa = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodiColumn.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        jenisKelaminColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        nomorTeleponColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        mahasiswaTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        loadData();
    }

    private void loadData() {
        mahasiswaTableView.setItems(dataMahasiswa);
        dataMahasiswa.clear();
        String tableName = "mahasiswa";
        String condition = null;
        ResultSet resultSet = DB.select(tableName, null, condition);

        try {
            while (resultSet.next()) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(resultSet.getString("nim"));
                mahasiswa.setPassword(resultSet.getString("password"));
                mahasiswa.setName(resultSet.getString("name"));
                mahasiswa.setSex(Sex.fromString(resultSet.getString("sex")));
                mahasiswa.setProdi(resultSet.getString("prodi"));
                mahasiswa.setPhone(resultSet.getString("phone"));
                mahasiswa.setAddress(resultSet.getString("address"));

                dataMahasiswa.add(mahasiswa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
