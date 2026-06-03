package com.mycompany.perfume.controllers.controllerAdmin;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.util.Duration;

public class PerfumeFormularioController implements Initializable {

    @FXML private BorderPane root;
    @FXML private VBox sidebar;
    @FXML private Button btn_sidebar;

    @FXML private TextField txtNombrePerfume;
    @FXML private TextField txtMarca;
    @FXML private TextField txtFamiliaOlfativa;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtNotasSalida;
    @FXML private TextField txtNotasCorazon;
    @FXML private TextField txtNotasFondo;

    @FXML private TextArea txtDescripcion;

    @FXML private ImageView imgPerfume;

    @FXML private Button btnSeleccionarImagen;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    private File imagenSeleccionada;

    private boolean sidebarVisible = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (imgPerfume != null) {
            imgPerfume.setPreserveRatio(true);
            imgPerfume.setSmooth(true);
        }
    }

    @FXML
    private void seleccionarImagen() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen del perfume");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        Stage stage = (Stage) btnSeleccionarImagen.getScene().getWindow();

        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {

            imagenSeleccionada = archivo;

            Image imagen = new Image(archivo.toURI().toString());
            imgPerfume.setImage(imagen);
        }
    }

    @FXML
    private void guardarPerfume() {

        String nombre = txtNombrePerfume.getText().trim();
        String marca = txtMarca.getText().trim();
        String familia = txtFamiliaOlfativa.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String notasSalida = txtNotasSalida.getText().trim();
        String notasCorazon = txtNotasCorazon.getText().trim();
        String notasFondo = txtNotasFondo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (nombre.isEmpty()
                || marca.isEmpty()
                || familia.isEmpty()
                || precioTexto.isEmpty()
                || notasSalida.isEmpty()
                || notasCorazon.isEmpty()
                || notasFondo.isEmpty()
                || descripcion.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Todos los campos deben estar llenos."
            );
            return;
        }

        double precio;

        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Precio inválido",
                    "El precio debe ser un número válido."
            );
            return;
        }

        if (precio < 0) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Precio inválido",
                    "El precio no puede ser negativo."
            );
            return;
        }

        String rutaImagen = "";

        if (imagenSeleccionada != null) {
            rutaImagen = imagenSeleccionada.getAbsolutePath();
        }

        /*
         * Aquí después conectas con Oracle:
         *
         * sql.insertarPerfume(
         *      nombre,
         *      marca,
         *      familia,
         *      precio,
         *      notasSalida,
         *      notasCorazon,
         *      notasFondo,
         *      descripcion,
         *      rutaImagen
         * );
         */

        System.out.println("Nombre: " + nombre);
        System.out.println("Marca: " + marca);
        System.out.println("Familia: " + familia);
        System.out.println("Precio: " + precio);
        System.out.println("Notas salida: " + notasSalida);
        System.out.println("Notas corazón: " + notasCorazon);
        System.out.println("Notas fondo: " + notasFondo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Imagen: " + rutaImagen);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Perfume guardado",
                "El perfume se guardó correctamente."
        );

        limpiarFormulario();
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
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

        if (root == null || sidebar == null) {
            return;
        }

        root.setLeft(sidebar);

        double width = sidebar.getPrefWidth();

        if (width <= 0) {
            width = 250;
        }

        sidebar.setTranslateX(-width);

        TranslateTransition slide =
                new TranslateTransition(Duration.millis(180), sidebar);

        slide.setToX(0);
        slide.setInterpolator(Interpolator.EASE_BOTH);
        slide.play();

        sidebarVisible = true;
    }

    private void animarOcultarSidebar() {

        if (root == null || sidebar == null) {
            return;
        }

        double width = sidebar.getWidth();

        if (width <= 0) {
            width = sidebar.getPrefWidth();
        }

        if (width <= 0) {
            width = 250;
        }

        TranslateTransition slide =
                new TranslateTransition(Duration.millis(180), sidebar);

        slide.setToX(-width);
        slide.setInterpolator(Interpolator.EASE_BOTH);

        slide.setOnFinished(e -> root.setLeft(null));

        slide.play();

        sidebarVisible = false;
    }

    private void limpiarFormulario() {

        txtNombrePerfume.clear();
        txtMarca.clear();
        txtFamiliaOlfativa.clear();
        txtPrecio.clear();
        txtNotasSalida.clear();
        txtNotasCorazon.clear();
        txtNotasFondo.clear();
        txtDescripcion.clear();

        imgPerfume.setImage(null);
        imagenSeleccionada = null;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtNombrePerfume.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alert = new Alert(tipo);

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}