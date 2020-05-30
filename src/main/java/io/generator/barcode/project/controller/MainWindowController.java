package io.generator.barcode.project.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;

public class MainWindowController {

    public Button btnPrint;
    public Text itemCode;
    public Text itemName;
    public AnchorPane root;
    public JFXTextField labelQty;

    public void initialize(String itemCode,String itemName){
        this.itemCode.setText(itemCode);
        this.itemName.setText(itemName);
    }


    public void btnPrintOnAction(ActionEvent actionEvent) {
        for (int i=0;i<Integer.parseInt(labelQty.getText());i++){
            new Thread(() -> {
                try {
                    JasperReport load = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/report/barcodeGenarator.jasper"));
                    Map<String, Object> params = new HashMap<>();
                    params.put("itemCode",itemCode.getText());
                    params.put("itemName",itemName.getText());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(load, params);
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
