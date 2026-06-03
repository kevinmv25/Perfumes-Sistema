/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.perfume.sistema;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author juego
 */
public class PerfumeSistema extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/scenes/principal.fxml")
                //getClass().getResource("/scenes/vistaPerfumes.fxml")
                //getClass().getResource("/scenes/AdministradoreesFXML/Perfume-formulario.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Colibrí Fragrances");
        stage.setScene(scene);

        // Opcional
        stage.setMaximized(true);

        stage.show();
    }
}
