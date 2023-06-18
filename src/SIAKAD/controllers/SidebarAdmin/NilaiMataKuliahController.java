package SIAKAD.controllers.SidebarAdmin;

import SIAKAD.App;
import SIAKAD.models.Mahasiswa;
import SIAKAD.models.MataKuliah;
import SIAKAD.models.NilaiMataKuliah;
import SIAKAD.utils.AlertMessage;
import SIAKAD.utils.DB;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class NilaiMataKuliahController {
    @FXML private Text ipkText;

    @FXML private ContextMenu contextMenu;
    @FXML private MenuItem editNilaiMenu;

    @FXML private TableView<NilaiMataKuliah> nilaiMataKuliahTableView;
    @FXML private TableColumn<NilaiMataKuliah, String> kodeColumn;
    @FXML private TableColumn<NilaiMataKuliah, String> namaMataKuliahColumn;
    @FXML private TableColumn<NilaiMataKuliah, String> namaMahasiswaColumn;
    @FXML private TableColumn<NilaiMataKuliah, String> nilaiColumn;

    ObservableList<NilaiMataKuliah> dataNilaiMataKuliah = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kodeColumn.setCellValueFactory(new PropertyValueFactory<>("kodeMataKuliah"));
        namaMataKuliahColumn.setCellValueFactory(new PropertyValueFactory<>("namaMataKuliah"));
        namaMataKuliahColumn.setCellValueFactory(
                cellData -> {
                    StringProperty property = new SimpleStringProperty();
                    NilaiMataKuliah nilaiMataKuliah = cellData.getValue();
                    property.setValue(nilaiMataKuliah.getNamaMataKuliah());
                    return property;
                });
        namaMahasiswaColumn.setCellValueFactory(
                cellData -> {
                    StringProperty property = new SimpleStringProperty();
                    NilaiMataKuliah nilaiMataKuliah = cellData.getValue();
                    property.setValue(nilaiMataKuliah.getNamaMahasiswa());
                    return property;
                });
        nilaiColumn.setCellValueFactory(new PropertyValueFactory<>("nilai"));

        nilaiMataKuliahTableView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        editNilaiMenu.setOnAction(this::editNilaiMenuClicked);

        loadData();
    }

    @FXML
    private void editNilaiMenuClicked(ActionEvent event) {
        NilaiMataKuliah selectedNilai =
                nilaiMataKuliahTableView.getSelectionModel().getSelectedItem();
        if (selectedNilai != null) {
            Dialog<Double> dialog = new Dialog<>();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Edit Nilai");

            DialogPane dialogPane = dialog.getDialogPane();

            TextField nilaiTextField = new TextField();
            nilaiTextField.setText(String.valueOf(selectedNilai.getNilai()));

            dialogPane.setContent(nilaiTextField);

            dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.setResultConverter(
                    buttonType -> {
                        if (buttonType == ButtonType.OK) {
                            try {
                                double newValue = Double.parseDouble(nilaiTextField.getText());
                                return newValue;
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    });

            Stage stage = (Stage) dialogPane.getScene().getWindow();
            stage.getIcons()
                    .add(new Image(App.class.getResourceAsStream("resources/logoSIAKAD.png")));
            stage.setOnCloseRequest(event1 -> dialog.setResult(null));

            Optional<Double> result = dialog.showAndWait();

            if (result.isPresent()) {
                double newValue = result.get();

                selectedNilai.updateNilai(newValue);

                loadData();
            }
        } else {
            AlertMessage.showError("Pilih Data", "Pilih data yang ingin diubah!");
        }
    }

    private void loadData() {
        nilaiMataKuliahTableView.setItems(dataNilaiMataKuliah);
        dataNilaiMataKuliah.clear();
        String tableName = "nilai_mata_kuliah";
        String condition = null;
        ResultSet resultSet = DB.selectAll(tableName, condition);

        try {
            while (resultSet.next()) {
                NilaiMataKuliah nilaiMataKuliah = new NilaiMataKuliah();
                nilaiMataKuliah.setMataKuliah(
                        MataKuliah.retrieveFromDatabase(resultSet.getString("kode_mata_kuliah")));
                nilaiMataKuliah.setMahasiswa(
                        Mahasiswa.retrieveFromDatabase(resultSet.getString("mahasiswa_nim")));
                nilaiMataKuliah.setNilai(resultSet.getDouble("nilai"));
                dataNilaiMataKuliah.add(nilaiMataKuliah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
