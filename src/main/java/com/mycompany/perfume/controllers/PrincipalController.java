/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.perfume.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

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
    
}
