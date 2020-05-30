package io.generator.barcode.project.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.generator.barcode.project.Appinitializer;
import io.generator.barcode.project.business.DetailBO;
import io.generator.barcode.project.entity.Details;
import io.generator.barcode.project.tm.DetailTM;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import static javafx.scene.input.KeyCode.ENTER;

public class SettingController {

    public JFXTextField txtBarcodeNo;
    public JFXTextField txtItemName;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnClear;
    public TableView<DetailTM> tblBarcode;
    public TextField txtSearch;


    private DetailBO detailbo = Appinitializer.ctx.getBean(DetailBO.class);

    public void initialize(){

        tblBarcode.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblBarcode.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));

        ObservableList<DetailTM> items = tblBarcode.getItems();
        List<Details> all = detailbo.findAll();
        for (Details details : all) {
            items.add(new DetailTM(details.getItemCode(),details.getName()));
        }

        tblBarcode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DetailTM>() {
            @Override
            public void changed(ObservableValue<? extends DetailTM> observable, DetailTM oldValue, DetailTM newValue) {
                DetailTM selectedItem = tblBarcode.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnSave.setText("SAVE");
                    btnDelete.setDisable(true);
                    txtBarcodeNo.clear();
                    txtItemName.clear();
                    return;
                }
                btnSave.setText("UPDATE");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtBarcodeNo.setText(selectedItem.getItemCode());
                txtItemName.setText(selectedItem.getItemName());
            }
        });
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(" ")) {
                tblBarcode.getItems().clear();
                searchDetail(newValue);
            }
        });

    }

    private void searchDetail(String newValue) {
        try {
            ObservableList<DetailTM> items = tblBarcode.getItems();
            List<Details> searchCustomer = detailbo.searchData(newValue + "%");
            for (Details data : searchCustomer) {
                items.add(new DetailTM(data.getItemCode(),data.getName()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong,Contact 0712385700", ButtonType.OK).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtBarcodeNo.clear();
        txtItemName.clear();
        tblBarcode.getSelectionModel().clearSelection();
        txtBarcodeNo.requestFocus();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Detail?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            DetailTM selectedItem = tblBarcode.getSelectionModel().getSelectedItem();
            try {
                detailbo.delete(selectedItem.getItemCode());
                tblBarcode.getItems().remove(selectedItem);
                tblBarcode.getSelectionModel().clearSelection();
                btnClear.setDisable(false);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "This Detail Can not Delete").show();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals("SAVE")) {
            ObservableList<DetailTM> barcode = tblBarcode.getItems();
            try {

                detailbo.save(new Details(txtItemName.getText().trim(),txtBarcodeNo.getText().toUpperCase().trim()));
                barcode.add(new DetailTM(txtBarcodeNo.getText(),txtItemName.getText()));

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong(Save Detail)").show();
            }
        } else {

            DetailTM selectedItem = tblBarcode.getSelectionModel().getSelectedItem();
            try {
                detailbo.update(new Details(txtItemName.getText().trim(),txtBarcodeNo.getText().toUpperCase().trim()));
                selectedItem.setItemCode(txtBarcodeNo.getText());
                selectedItem.setItemName(txtItemName.getText());
                tblBarcode.refresh();
                btnSave.setText("SAVE");
                tblBarcode.getSelectionModel().clearSelection();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong(Update Customer)").show();
            }
        }
    }

    public void OnKeyPressed(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (code == ENTER) {
            JFXTextField source = (JFXTextField) keyEvent.getSource();
            switch (source.getId()) {
                case "txtBarcodeNo":
                    txtItemName.requestFocus();
                    break;
                case "txtItemName":
                 btnSave.requestFocus();
                    break;
            }
        }
    }

    public void saveEnterClick(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        if (code == ENTER) {
            btnSave.fire();
        }
    }

    public void tblOnmouseClick(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2) {
            URL resource = this.getClass().getResource("/viwe/mainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent root = fxmlLoader.load();
            Scene placeOrderScene = new Scene(root);
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(placeOrderScene);
            secondaryStage.centerOnScreen();
            secondaryStage.setResizable(false);

            MainWindowController ctrl = fxmlLoader.getController();
            DetailTM selectedCustomer = tblBarcode.getSelectionModel().getSelectedItem();
            ctrl.initialize(selectedCustomer.getItemCode(),selectedCustomer.getItemName());

            secondaryStage.show();
        }
    }
}
