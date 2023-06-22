package SIAKAD.controllers.SidebarMahasiswa;

import SIAKAD.controllers.LoggedController;
import SIAKAD.models.MataKuliah;
import SIAKAD.models.NilaiMataKuliah;
import SIAKAD.utils.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NilaiMataKuliahController {
    @FXML private Text ipkText;
    @FXML private TableView<NilaiMataKuliah> nilaiMataKuliahTableView;
    @FXML private TableColumn<NilaiMataKuliah, String> kodeColumn;
    @FXML private TableColumn<NilaiMataKuliah, String> namaMataKuliahColumn;
    @FXML private TableColumn<NilaiMataKuliah, String> nilaiColumn;

    private final ObservableList<NilaiMataKuliah> dataNilaiMataKuliah =
            FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        ipkText.setText("IPK: " + LoggedController.getMahasiswa().getIpk());

        kodeColumn.setCellValueFactory(new PropertyValueFactory<>("kodeMataKuliah"));
        namaMataKuliahColumn.setCellValueFactory(new PropertyValueFactory<>("namaMataKuliah"));
        namaMataKuliahColumn.setCellValueFactory(
                cellData -> {
                    StringProperty property = new SimpleStringProperty();
                    NilaiMataKuliah nilaiMataKuliah = cellData.getValue();
                    property.setValue(nilaiMataKuliah.getNamaMataKuliah());
                    return property;
                });
        nilaiColumn.setCellValueFactory(new PropertyValueFactory<>("nilai"));

        nilaiMataKuliahTableView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        loadData();
    }

    private void loadData() {
        nilaiMataKuliahTableView.setItems(dataNilaiMataKuliah);
        dataNilaiMataKuliah.clear();
        String tableName = "nilai_mata_kuliah";
        String condition = "mahasiswa_nim = '" + LoggedController.getMahasiswa().getNim() + "'";
        ResultSet resultSet = DB.select(tableName, null, condition);

        try {
            while (resultSet.next()) {
                NilaiMataKuliah nilaiMataKuliah = new NilaiMataKuliah();
                nilaiMataKuliah.setMataKuliah(
                        MataKuliah.retrieveFromDatabase(resultSet.getString("kode_mata_kuliah")));
                nilaiMataKuliah.setNilai(resultSet.getDouble("nilai"));

                dataNilaiMataKuliah.add(nilaiMataKuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
