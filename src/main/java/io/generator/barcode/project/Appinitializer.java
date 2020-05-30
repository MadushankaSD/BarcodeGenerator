package io.generator.barcode.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URL;

public class Appinitializer extends Application {

    public static AnnotationConfigApplicationContext ctx;
    public AnchorPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ctx= new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();

        URL resource = this.getClass().getResource("/viwe/settingDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene mainscene = new Scene(load);
        primaryStage.setScene(mainscene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("BarCode Generator");

       /* Image image = new Image(getClass().getResourceAsStream("/image/mellowIcon.png"));
        primaryStage.getIcons().add(image);*/

        primaryStage.centerOnScreen();
        primaryStage.alwaysOnTopProperty();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
