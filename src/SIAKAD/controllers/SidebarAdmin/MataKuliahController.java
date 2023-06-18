package SIAKAD.controllers.SidebarAdmin;

import SIAKAD.models.Dosen;
import SIAKAD.models.MataKuliah;
import SIAKAD.utils.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MataKuliahController {
    @FXML private TableView<MataKuliah> mataKuliahTableView;
    @FXML private TableColumn<MataKuliah, String> kodeColumn;
    @FXML private TableColumn<MataKuliah, String> namaColumn;
    @FXML private TableColumn<MataKuliah, String> prodiColumn;
    @FXML private TableColumn<MataKuliah, String> sksColumn;
    @FXML private TableColumn<MataKuliah, String> dosenPengajarColumn;

    private final ObservableList<MataKuliah> dataMataKuliah = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kodeColumn.setCellValueFactory(new PropertyValueFactory<>("kode"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodiColumn.setCellValueFactory(new PropertyValueFactory<>("prodi"));
        sksColumn.setCellValueFactory(new PropertyValueFactory<>("sks"));
        dosenPengajarColumn.setCellValueFactory(new PropertyValueFactory<>("nameDosenPengajar"));

        mataKuliahTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        loadData();
    }

    private void loadData() {
        mataKuliahTableView.setItems(dataMataKuliah);
        dataMataKuliah.clear();
        String tableName = "mata_kuliah";
        String condition = null;
        ResultSet resultSet = DB.selectAll(tableName, condition);

        try {
            while (resultSet.next()) {
                MataKuliah mataKuliah = new MataKuliah();
                mataKuliah.setKode(resultSet.getString("kode"));
                mataKuliah.setName(resultSet.getString("nama_mata_kuliah"));
                mataKuliah.setProdi(resultSet.getString("prodi"));
                mataKuliah.setSks(resultSet.getInt("sks"));
                mataKuliah.setProdi(resultSet.getString("prodi"));
                mataKuliah.setDosenPengajar(
                        Dosen.retrieveFromDatabase(resultSet.getString("dosen_pengajar")));

                dataMataKuliah.add(mataKuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
