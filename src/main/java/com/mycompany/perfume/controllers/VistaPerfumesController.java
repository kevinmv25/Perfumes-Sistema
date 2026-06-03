package com.mycompany.perfume.controllers;


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

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class VistaPerfumesController implements Initializable {

    @FXML
    private ScrollPane scrollPerfumes;

    @FXML
    private FlowPane contenedorPerfumes;

    @FXML
    private Label cantidad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarPanel();
        cargarPerfumes();
        actualizarCantidadCarrito();
    }

    private void configurarPanel() {
        scrollPerfumes.setFitToWidth(true);
        scrollPerfumes.setStyle("-fx-background-color: white; -fx-background: white;");

        contenedorPerfumes.setHgap(25);
        contenedorPerfumes.setVgap(25);
        contenedorPerfumes.setPadding(new Insets(25));
        contenedorPerfumes.setStyle("-fx-background-color: white;");
    }

    private void cargarPerfumes() {
        contenedorPerfumes.getChildren().clear();

        agregarTarjetaTemporal(
                1,
                "Versace Eros EDP",
                "Fragancia dulce, fresca y seductora.",
                1150,
                "/images/perfumesPNG/VersaceEros-EDP.png"
        );

        agregarTarjetaTemporal(
                2,
                "Versace Pour Homme EDT",
                "Aromático cítrico y elegante.",
                1200,
                "/images/perfumesPNG/VersacePourHomme-EDT.png"
        );

        agregarTarjetaTemporal(
                3,
                "212 VIP Black",
                "Dulce, especiado y nocturno.",
                950,
                "/images/perfumesPNG/VipBlack.png"
        );

        agregarTarjetaTemporal(
                4,
                "Afnan 9PM",
                "Vainilla, manzana y canela.",
                850,
                "/images/perfumesPNG/9pm.png"
        );

        agregarTarjetaTemporal(
                5,
                "Parfums de Marly Layton",
                "Manzana, vainilla y maderas.",
                4500,
                "/images/perfumesPNG/layton.png"
        );
    }

    private void agregarTarjetaTemporal(int idPerfume, String nombre, String descripcion, double precio, String rutaImagen){
        VBox tarjeta = crearTarjetaPerfume(
                idPerfume,
                nombre,
                descripcion,
                precio,
                rutaImagen
        );

        contenedorPerfumes.getChildren().add(tarjeta);
    }

    private VBox crearTarjetaPerfume(int idPerfume, String nombre, String descripcion, double precio, String rutaImagen){
        VBox card = new VBox(10);
        card.setPrefWidth(220);
        card.setMinHeight(365);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.TOP_CENTER);

        card.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #DADADA;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 8, 0, 0, 2);"
        );

        ImageView imagen = new ImageView();

        try {
            Image img = new Image(getClass().getResourceAsStream(rutaImagen));
            imagen.setImage(img);
        } catch (Exception e) {
            System.out.println("No se encontró la imagen: " + rutaImagen);
        }

        imagen.setFitWidth(170);
        imagen.setFitHeight(170);
        imagen.setPreserveRatio(true);

        Label lblNombre = new Label(nombre);
        lblNombre.setWrapText(true);
        lblNombre.setAlignment(Pos.CENTER);
        lblNombre.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #222222;"
        );

        Label lblDescripcion = new Label(descripcion);
        lblDescripcion.setWrapText(true);
        lblDescripcion.setAlignment(Pos.CENTER);
        lblDescripcion.setStyle(
                "-fx-font-size: 12px;"
                + "-fx-text-fill: #666666;"
        );

        Label lblPrecio = new Label("$" + String.format("%.2f", precio));
        lblPrecio.setStyle(
                "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #000000;"
        );

        Button btnCarrito = new Button("Agregar al carrito");
        btnCarrito.setPrefWidth(180);
        btnCarrito.setStyle(estiloBotonCarrito());

        btnCarrito.setOnMouseEntered(e -> btnCarrito.setStyle(estiloBotonCarritoHover()));
        btnCarrito.setOnMouseExited(e -> btnCarrito.setStyle(estiloBotonCarrito()));

        btnCarrito.setOnAction(event -> agregarAlCarrito(
                idPerfume,
                nombre,
                precio,
                rutaImagen
        ));

        card.getChildren().addAll(
                imagen,
                lblNombre,
                lblDescripcion,
                lblPrecio,
                btnCarrito
        );

        return card;
    }

    private void agregarAlCarrito(int idPerfume,String nombre,double precio,String rutaImagen){
        CarritoTemporal.agregarProducto(
                idPerfume,
                nombre,
                precio,
                rutaImagen
        );

        actualizarCantidadCarrito();

        System.out.println("Perfume agregado al carrito: "
                + nombre + CarritoTemporal.cantidadProductos()
        );

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Carrito");
        alerta.setHeaderText(null);
        alerta.setContentText(nombre + " fue agregado al carrito.");
        alerta.showAndWait();

        /*
            PREPARADO PARA ORACLE:

            Este carrito temporal guarda idPerfume porque tu BD usa:

            detalle_venta_t(
                id_detalle,
                perfume REF perfume_t,
                cantidad,
                precio_unitario,
                subtotal
            )

            Cuando el usuario presione IR A PAGAR en CarritoController,
            se usará el idPerfume para buscar el REF del perfume:

            SELECT REF(p)
            FROM PERFUMES_OBJ p
            WHERE p.id_perfume = ?

            Y con eso se armarán los detalle_venta_t para VENTAS_OBJ.

            De momento NO se registra en Oracle aquí porque todavía
            solo se está agregando al carrito, no confirmando la venta.
        */
    }

    private void actualizarCantidadCarrito() {
        if (cantidad != null) {
            cantidad.setText(String.valueOf(CarritoTemporal.cantidadProductos()));
        }
    }

    private String estiloBotonCarrito() {
        return "-fx-background-color: #000000;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 10;"
                + "-fx-font-weight: bold;"
                + "-fx-cursor: hand;";
    }

    private String estiloBotonCarritoHover() {
        return "-fx-background-color: #333333;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 10;"
                + "-fx-font-weight: bold;"
                + "-fx-cursor: hand;";
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
    private void sceneCarrito(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/carrito.fxml", (Node) event.getSource());
    }
    
}