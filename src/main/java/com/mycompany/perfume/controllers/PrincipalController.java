/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.perfume.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author juego
 */
public class PrincipalController implements Initializable {

    
    
    
    @FXML
    private StackPane bannerPane;

    @FXML
    private ImageView bannerImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bannerImage.fitWidthProperty().bind(bannerPane.widthProperty());
        bannerImage.fitHeightProperty().bind(bannerPane.heightProperty());

        bannerImage.setPreserveRatio(false);
        bannerImage.setSmooth(true);
    }

    public void cambiarEscena(String fxml, Node node) {
        try {
                Parent nuevaVista = FXMLLoader.load(getClass().getResource(fxml));

                Scene scene = node.getScene();

                FadeTransition fadeOut = new FadeTransition(Duration.millis(200), scene.getRoot());
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);

                fadeOut.setOnFinished(e -> {
                    scene.setRoot(nuevaVista);

                    FadeTransition fadeIn = new FadeTransition(Duration.millis(200), nuevaVista);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);
                    fadeIn.play();
                });

                fadeOut.play();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }  
    
    @FXML
    private void sceneVista(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/vistaPerfumes.fxml", (Node) event.getSource());
    }
    
}
