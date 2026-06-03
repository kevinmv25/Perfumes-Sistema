package com.mycompany.perfume.controllers;



import com.mycompany.perfume.pojo.CarritoItem;
import com.mycompany.perfume.pojo.CarritoTemporal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CarritoController implements Initializable {

    @FXML
    private ScrollPane scrollCarrito;

    @FXML
    private VBox contenedorCarrito;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnPagar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarVista();
        cargarCarrito();

        btnPagar.setOnAction(event -> pagarPedido());
    }

    private void configurarVista() {
        scrollCarrito.setFitToWidth(true);
        scrollCarrito.setStyle("-fx-background-color: white; -fx-background: white;");

        contenedorCarrito.setSpacing(0);
        contenedorCarrito.setStyle("-fx-background-color: white;");
    }

    private void cargarCarrito() {
        contenedorCarrito.getChildren().clear();

        for (CarritoItem item : CarritoTemporal.obtenerCarrito()) {
            HBox fila = crearFilaProducto(item);
            contenedorCarrito.getChildren().add(fila);
        }

        actualizarTotales();
    }

    private HBox crearFilaProducto(CarritoItem item) {
        HBox fila = new HBox(20);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setPadding(new Insets(18));
        fila.setStyle(
                "-fx-background-color: white;"
                + "-fx-border-color: #E6E6E6;"
                + "-fx-border-width: 0 0 1 0;"
        );

        ImageView imagen = new ImageView();

        try {
            Image img = new Image(getClass().getResourceAsStream(item.getImagen()));
            imagen.setImage(img);
        } catch (Exception e) {
            System.out.println("No se encontró imagen: " + item.getImagen());
        }

        imagen.setFitWidth(85);
        imagen.setFitHeight(85);
        imagen.setPreserveRatio(true);

        Label lblNombre = new Label(item.getNombre());
        lblNombre.setPrefWidth(335);
        lblNombre.setWrapText(true);
        lblNombre.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #202428;"
        );

        Label lblPrecio = new Label("$" + String.format("%.2f", item.getPrecio()));
        lblPrecio.setPrefWidth(120);
        lblPrecio.setStyle("-fx-font-size: 15px;");

        Button btnMenos = new Button("-");
        Label lblCantidad = new Label(String.valueOf(item.getCantidad()));
        Button btnMas = new Button("+");

        btnMenos.setPrefWidth(35);
        btnMas.setPrefWidth(35);

        lblCantidad.setPrefWidth(40);
        lblCantidad.setAlignment(Pos.CENTER);
        lblCantidad.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        btnMenos.setStyle(estiloBotonCantidad());
        btnMas.setStyle(estiloBotonCantidad());

        btnMenos.setOnAction(event -> {
            item.disminuirCantidad();
            cargarCarrito();
        });

        btnMas.setOnAction(event -> {
            item.aumentarCantidad();
            cargarCarrito();
        });

        HBox cajaCantidad = new HBox(5);
        cajaCantidad.setAlignment(Pos.CENTER);
        cajaCantidad.setPrefWidth(140);
        cajaCantidad.getChildren().addAll(btnMenos, lblCantidad, btnMas);

        Label lblSubtotalItem = new Label("$" + String.format("%.2f", item.getSubtotal()));
        lblSubtotalItem.setPrefWidth(120);
        lblSubtotalItem.setStyle(
                "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
        );

        Button btnEliminar = new Button("X");
        btnEliminar.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-text-fill: #202428;"
                + "-fx-font-weight: bold;"
                + "-fx-cursor: hand;"
        );

        btnEliminar.setOnAction(event -> {
            CarritoTemporal.eliminarProducto(item);
            cargarCarrito();
        });

        fila.getChildren().addAll(
                imagen,
                lblNombre,
                lblPrecio,
                cajaCantidad,
                lblSubtotalItem,
                btnEliminar
        );

        return fila;
    }

    private void actualizarTotales() {
        double total = CarritoTemporal.calcularTotal();

        lblSubtotal.setText("$" + String.format("%.2f", total));
        lblTotal.setText("$" + String.format("%.2f", total));
    }

    private void pagarPedido() {
        if (CarritoTemporal.obtenerCarrito().isEmpty()) {
            mostrarAlerta("Carrito vacío", "No hay perfumes en el carrito.");
            return;
        }

        /*
            AQUÍ DESPUÉS VA ORACLE.

            Cuando ya tengan conexión con la BD, este método debe:

            1. Obtener el cliente actual.
            2. Crear una venta en VENTAS_OBJ.
            3. Crear los detalles usando LISTA_DETALLES_VENTA_T.
            4. Cada detalle debe guardar:
               - perfume REF perfume_t
               - cantidad
               - precio_unitario
               - subtotal
            5. Insertar la venta completa en Oracle.

            De momento no se conecta a Oracle porque todavía no tenemos
            la conexión lista.
        */

        mostrarAlerta("Pedido", "Aquí después se registrará la venta en Oracle.");
    }

    private String estiloBotonCantidad() {
        return "-fx-background-color: white;"
                + "-fx-border-color: #CCCCCC;"
                + "-fx-border-radius: 5;"
                + "-fx-background-radius: 5;"
                + "-fx-font-weight: bold;"
                + "-fx-cursor: hand;";
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
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