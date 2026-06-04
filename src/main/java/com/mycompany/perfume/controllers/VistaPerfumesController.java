package com.mycompany.perfume.controllers;

import com.mycompany.perfume.pojo.CarritoTemporal;
import com.mycompany.perfume.pojo.Perfume;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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

        /*
        ==========================================================
        FUTURO ORACLE
        ==========================================================

        Aquí después se reemplazarán estos datos temporales por:

        OraclePerfumeDAO dao = new OraclePerfumeDAO();
        List<Perfume> perfumes = dao.obtenerPerfumesDisponibles();

        for (Perfume perfume : perfumes) {
            contenedorPerfumes.getChildren().add(crearTarjetaPerfume(perfume));
        }

        IMPORTANTE:
        Cada perfume debe traer mínimo:
        - idPerfume
        - nombre
        - descripcion
        - precio
        - imagen
        - presentacion
        - disponible
        - marca
        - familiaOlfativa

        Las notas y reseñas se podrán completar después en detalles desde Neo4j
        usando perfume.getIdPerfume().
        */

        agregarTarjetaTemporal(
                1,
                "Versace Eros EDP",
                "Versace",
                "Aromática Fougère",
                "Fragancia dulce, fresca y seductora.",
                1150,
                "100 ml",
                true,
                "/images/perfumesPNG/VersaceEros-EDP.png",
                Arrays.asList("Menta", "Manzana verde", "Limón"),
                Arrays.asList("Haba tonka", "Geranio", "Ambroxan"),
                Arrays.asList("Vainilla", "Cedro", "Vetiver")
        );

        agregarTarjetaTemporal(
                2,
                "Versace Pour Homme EDT",
                "Versace",
                "Aromática Cítrica",
                "Aromático cítrico y elegante.",
                1200,
                "100 ml",
                true,
                "/images/perfumesPNG/VersacePourHomme-EDT.png",
                Arrays.asList("Limón", "Bergamota", "Neroli"),
                Arrays.asList("Jacinto", "Salvia", "Cedro"),
                Arrays.asList("Ámbar", "Almizcle", "Haba tonka")
        );

        agregarTarjetaTemporal(
                3,
                "212 VIP Black",
                "Carolina Herrera",
                "Oriental Especiada",
                "Dulce, especiado y nocturno.",
                950,
                "100 ml",
                true,
                "/images/perfumesPNG/VipBlack.png",
                Arrays.asList("Absenta", "Anís", "Hinojo"),
                Arrays.asList("Lavanda"),
                Arrays.asList("Vainilla negra", "Almizcle")
        );

        agregarTarjetaTemporal(
                4,
                "Afnan 9PM",
                "Afnan",
                "Oriental Vainilla",
                "Vainilla, manzana y canela.",
                850,
                "100 ml",
                true,
                "/images/perfumesPNG/9pm.png",
                Arrays.asList("Manzana", "Canela", "Bergamota"),
                Arrays.asList("Flor de azahar", "Lirio de los valles"),
                Arrays.asList("Vainilla", "Haba tonka", "Ámbar")
        );

        agregarTarjetaTemporal(
                5,
                "Parfums de Marly Layton",
                "Parfums de Marly",
                "Oriental Floral",
                "Manzana, vainilla y maderas.",
                4500,
                "125 ml",
                true,
                "/images/perfumesPNG/layton.png",
                Arrays.asList("Manzana", "Lavanda", "Bergamota"),
                Arrays.asList("Jazmín", "Violeta", "Geranio"),
                Arrays.asList("Vainilla", "Pimienta", "Guayaco")
        );
    }

    private void agregarTarjetaTemporal(int idPerfume,
                                        String nombre,
                                        String marca,
                                        String familia,
                                        String descripcion,
                                        double precio,
                                        String presentacion,
                                        boolean disponible,
                                        String rutaImagen,
                                        List<String> notasSalida,
                                        List<String> notasCorazon,
                                        List<String> notasFondo) {

        Perfume perfume = new Perfume();

        perfume.setIdPerfume(idPerfume);
        perfume.setNombre(nombre);
        perfume.setMarca(marca);
        perfume.setFamiliaOlfativa(familia);
        perfume.setDescripcion(descripcion);
        perfume.setPrecio(precio);
        perfume.setPresentacion(presentacion);
        perfume.setDisponible(disponible);
        perfume.setImagen(rutaImagen);
        perfume.setNotasSalida(notasSalida);
        perfume.setNotasCorazon(notasCorazon);
        perfume.setNotasFondo(notasFondo);

        VBox tarjeta = crearTarjetaPerfume(perfume);
        contenedorPerfumes.getChildren().add(tarjeta);
    }

    private VBox crearTarjetaPerfume(Perfume perfume) {
        VBox card = new VBox(10);

        card.setPrefWidth(220);
        card.setMinHeight(405);
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
            Image img = new Image(getClass().getResourceAsStream(perfume.getImagen()));
            imagen.setImage(img);
        } catch (Exception e) {
            System.out.println("No se encontró la imagen: " + perfume.getImagen());
        }

        imagen.setFitWidth(170);
        imagen.setFitHeight(170);
        imagen.setPreserveRatio(true);

        Label lblNombre = new Label(perfume.getNombre());
        lblNombre.setWrapText(true);
        lblNombre.setAlignment(Pos.CENTER);
        lblNombre.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #222222;"
        );

        Label lblDescripcion = new Label(perfume.getDescripcion());
        lblDescripcion.setWrapText(true);
        lblDescripcion.setAlignment(Pos.CENTER);
        lblDescripcion.setStyle(
                "-fx-font-size: 12px;"
                + "-fx-text-fill: #666666;"
        );

        Label lblPrecio = new Label(String.format("%.2f MXN", perfume.getPrecio()));
        lblPrecio.setStyle(
                "-fx-font-size: 15px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #000000;"
        );

        Button btnDetalles = new Button("Ver detalles");
        btnDetalles.setPrefWidth(180);
        btnDetalles.setStyle(estiloBotonDetalles());

        btnDetalles.setOnMouseEntered(e -> btnDetalles.setStyle(estiloBotonDetallesHover()));
        btnDetalles.setOnMouseExited(e -> btnDetalles.setStyle(estiloBotonDetalles()));

        btnDetalles.setOnAction(event -> abrirDetallesPerfume(perfume));

        Button btnCarrito = new Button("Agregar al carrito");
        btnCarrito.setPrefWidth(180);
        btnCarrito.setStyle(estiloBotonCarrito());

        btnCarrito.setOnMouseEntered(e -> btnCarrito.setStyle(estiloBotonCarritoHover()));
        btnCarrito.setOnMouseExited(e -> btnCarrito.setStyle(estiloBotonCarrito()));

        btnCarrito.setOnAction(event -> agregarAlCarrito(
                perfume.getIdPerfume(),
                perfume.getNombre(),
                perfume.getPrecio(),
                perfume.getImagen()
        ));

        card.getChildren().addAll(
                imagen,
                lblNombre,
                lblDescripcion,
                lblPrecio,
                btnDetalles,
                btnCarrito
        );

        return card;
    }

    private void abrirDetallesPerfume(Perfume perfumeSeleccionado) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/scenes/detallesPerfume.fxml")
            );

            Parent nuevaVista = loader.load();

            DetallesPerfumeController controller = loader.getController();

            /*
            ==========================================================
            AQUÍ SE ENVÍA EL PERFUME SELECCIONADO AL FXML DE DETALLES
            ==========================================================

            Este objeto trae idPerfume.

            En DetallesPerfumeController, ese idPerfume servirá para:

            ORACLE:
            - Buscar datos estructurados actualizados del perfume.

            NEO4J:
            - Buscar notas olfativas.
            - Buscar reseñas del perfume.

            Ejemplo futuro en DetallesPerfumeController:

            int id = perfumeSeleccionado.getIdPerfume();

            perfumeOracle = oracleDAO.obtenerPerfumePorId(id);

            notasSalida = neo4jDAO.obtenerNotas(id, "SALIDA");
            notasCorazon = neo4jDAO.obtenerNotas(id, "CORAZON");
            notasFondo = neo4jDAO.obtenerNotas(id, "FONDO");

            resenas = neo4jDAO.obtenerResenasPorPerfume(id);
            */

            controller.setPerfume(perfumeSeleccionado);

            Scene scene = contenedorPerfumes.getScene();

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

    private void agregarAlCarrito(int idPerfume, String nombre, double precio, String rutaImagen) {
        CarritoTemporal.agregarProducto(
                idPerfume,
                nombre,
                precio,
                rutaImagen
        );

        actualizarCantidadCarrito();

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Carrito");
        alerta.setHeaderText(null);
        alerta.setContentText(nombre + " fue agregado al carrito.");
        alerta.showAndWait();

        /*
        ==========================================================
        FUTURO ORACLE
        ==========================================================

        Aquí NO se registra la venta todavía.

        Solo se guarda temporalmente en carrito.

        Cuando el usuario confirme compra, se usará idPerfume para:
        - Buscar el perfume en Oracle.
        - Crear Venta.
        - Crear DetalleVenta.
        - Actualizar Inventario.
        */
    }

    private void actualizarCantidadCarrito() {
        if (cantidad != null) {
            cantidad.setText(String.valueOf(CarritoTemporal.cantidadProductos()));
        }
    }

    private String estiloBotonDetalles() {
        return "-fx-background-color: white;"
                + "-fx-text-fill: #111111;"
                + "-fx-border-color: #DADADA;"
                + "-fx-border-radius: 10;"
                + "-fx-background-radius: 10;"
                + "-fx-font-weight: bold;"
                + "-fx-cursor: hand;";
    }

    private String estiloBotonDetallesHover() {
        return "-fx-background-color: #F5F5F5;"
                + "-fx-text-fill: #111111;"
                + "-fx-border-color: #BDBDBD;"
                + "-fx-border-radius: 10;"
                + "-fx-background-radius: 10;"
                + "-fx-font-weight: bold;"
                + "-fx-cursor: hand;";
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