package com.mycompany.perfume.controllers.controllerAdmin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class InterfazAdminController implements Initializable {

    @FXML private Button btn_sidebar;

    @FXML private BorderPane root;
    @FXML private VBox sidebar;
    @FXML private AnchorPane centro;

    @FXML private Button btn_menuAdmin;
    @FXML private Button btn_inventario;
    @FXML private Button btn_empleados;
    @FXML private Button btn_reportes;

    private boolean sidebarVisible = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (sidebar != null) {
            sidebar.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.getRoot().setUserData("/scenes/interfazAdmin.fxml");
                }
            });
        }
    }

    @FXML
    private void mostrarSidebar() {
        if (!sidebarVisible) {
            animarMostrarSidebar();
        }
    }

    @FXML
    private void ocultarSidebar() {
        if (sidebarVisible) {
            animarOcultarSidebar();
        }
    }

    @FXML
    private void toggleSidebar() {
        if (sidebarVisible) {
            animarOcultarSidebar();
        } else {
            animarMostrarSidebar();
        }
    }

    private void animarMostrarSidebar() {
        root.setLeft(sidebar);

        double width = sidebar.getPrefWidth();

        if (width <= 0) {
            width = 250;
        }

        sidebar.setTranslateX(-width);

        TranslateTransition slide = new TranslateTransition(Duration.millis(180), sidebar);
        slide.setToX(0);
        slide.setInterpolator(Interpolator.EASE_BOTH);
        slide.play();

        sidebarVisible = true;
    }

    private void animarOcultarSidebar() {
        double width = sidebar.getWidth();

        if (width <= 0) {
            width = sidebar.getPrefWidth();
        }

        if (width <= 0) {
            width = 250;
        }

        TranslateTransition slide = new TranslateTransition(Duration.millis(180), sidebar);
        slide.setToX(-width);
        slide.setInterpolator(Interpolator.EASE_BOTH);

        slide.setOnFinished(e -> root.setLeft(null));

        slide.play();

        sidebarVisible = false;
    }

    private void applyFadeEffect() {
        if (centro != null) {
            FadeTransition ft = new FadeTransition(Duration.millis(200), centro);
            ft.setFromValue(0.8);
            ft.setToValue(1.0);
            ft.play();
        }
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
    private void sceneMenuAdmin(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/admin/", (Node) event.getSource());
    }

    @FXML
    private void sceneInventario(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/admin/", (Node) event.getSource());
    }

    @FXML
    private void scenePrincipal(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/admin/", (Node) event.getSource());
    }

    @FXML
    private void sceneControl(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/admin/", (Node) event.getSource());
    }
}