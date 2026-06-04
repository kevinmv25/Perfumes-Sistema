package com.mycompany.perfume.controllers;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DetallesPerfumeController implements Initializable {

    @FXML private ImageView imgLogo;
    @FXML private ImageView imgUsuario;
    @FXML private ImageView imgFavorito;
    @FXML private ImageView imgBuscar;
    @FXML private ImageView imgCarrito;
    @FXML private ImageView imgPerfume;

    
    @FXML private Label lblNombre;
    @FXML private Label lblEstrellas;
    @FXML private Label lblCantidadResenas;
    @FXML private Label lblPrecio;
    @FXML private Label lblDisponible;
    @FXML private Label lblPresentacion;
    @FXML private Label lblDescripcion;
    @FXML private Label lblMarca;
    @FXML private Label lblFamilia;
    @FXML private Label lblNotasSalida;
    @FXML private Label lblNotasCorazon;
    @FXML private Label lblNotasFondo;
    @FXML private Label lblCantidad;

    @FXML private Button btnRestar;
    @FXML private Button btnSumar;
    @FXML private Button btnAgregarCarrito;
    @FXML private Button btnFavorito;
    @FXML private Button btnAgregarResena;

    @FXML private VBox contenedorResenas;

    private Perfume perfumeActual;
    private int cantidad = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarIconos();
        configurarBotones();

        // Datos temporales para probar la interfaz.
        // Después esto se quitará cuando principal.fxml mande el perfume seleccionado.
        cargarPerfumeDePrueba();
    }

    private void cargarIconos() {
        // Ajusta estas rutas según tu carpeta real de imágenes.
        // Ejemplo recomendado:
        // src/main/resources/images/logo-colibri.png

        try {
            imgLogo.setImage(new Image(getClass().getResourceAsStream("/images/logo-colibri.png")));
            imgUsuario.setImage(new Image(getClass().getResourceAsStream("/images/icono-usuario.png")));
            imgFavorito.setImage(new Image(getClass().getResourceAsStream("/images/icono-corazon.png")));
            imgBuscar.setImage(new Image(getClass().getResourceAsStream("/images/icono-lupa.png")));
            imgCarrito.setImage(new Image(getClass().getResourceAsStream("/images/icono-carrito.png")));
        } catch (Exception e) {
            System.out.println("No se pudieron cargar algunos iconos. Revisa las rutas en /images/");
        }
    }

    private void configurarBotones() {
        btnSumar.setOnAction(e -> {
            cantidad++;
            lblCantidad.setText(String.valueOf(cantidad));
        });

        btnRestar.setOnAction(e -> {
            if (cantidad > 1) {
                cantidad--;
                lblCantidad.setText(String.valueOf(cantidad));
            }
        });
    }

    private void cargarPerfumeDePrueba() {
        Perfume perfume = new Perfume();

        perfume.setIdPerfume(1);
        perfume.setNombre("Versace Eros 100ML EDP Spray");
        perfume.setMarca("Versace");
        perfume.setFamiliaOlfativa("Aromática Fougère");
        perfume.setPrecio(1199.00);
        perfume.setPresentacion("100 ml");
        perfume.setDisponible(true);
        perfume.setImagen("/images/perfumesPNG/VersaceEros-EDP.png");

        perfume.setDescripcion(
                "Fragancia masculina intensa y fresca, ideal para uso nocturno. "
                + "Combina notas cítricas, aromáticas y amaderadas con un toque dulce y elegante."
        );

        perfume.setNotasSalida(Arrays.asList("Menta", "Manzana verde", "Limón"));
        perfume.setNotasCorazon(Arrays.asList("Haba tonka", "Geranio", "Ambroxan"));
        perfume.setNotasFondo(Arrays.asList("Vainilla", "Cedro", "Vetiver", "Musgo de roble"));

        setPerfume(perfume);
    }

    public void setPerfume(Perfume perfume) {
        this.perfumeActual = perfume;

        /*
        ==========================================================
        AQUÍ DESPUÉS SE TRAERÁN LOS DATOS DESDE ORACLE
        ==========================================================

        Ejemplo futuro:

        OraclePerfumeDAO daoOracle = new OraclePerfumeDAO();
        Perfume perfumeBD = daoOracle.obtenerPerfumePorId(perfume.getIdPerfume());

        this.perfumeActual = perfumeBD;

        Oracle debería traer:
        - idPerfume
        - nombre
        - descripción
        - precio
        - imagen
        - presentación
        - disponible
        - marca
        - familiaOlfativa
        - notasSalida
        - notasCorazon
        - notasFondo
        */

        mostrarDatosPerfume();
        cargarResenasDePrueba();

        /*
        ==========================================================
        AQUÍ DESPUÉS SE TRAERÁN LAS RESEÑAS DESDE NEO4J
        ==========================================================

        Ejemplo futuro:

        Neo4jResenaDAO daoNeo4j = new Neo4jResenaDAO();
        List<Resena> resenas = daoNeo4j.obtenerResenasPorPerfume(perfumeActual.getIdPerfume());

        mostrarResenas(resenas);

        Neo4j debería traer las reseñas relacionadas con:
        (Cliente)-[:REALIZO_RESEÑA]->(Perfume)
        filtrando por idPerfume.
        */
    }

    private void mostrarDatosPerfume() {
        if (perfumeActual == null) {
            return;
        }

        
        lblNombre.setText(perfumeActual.getNombre());
        lblMarca.setText(perfumeActual.getMarca());
        lblFamilia.setText(perfumeActual.getFamiliaOlfativa());
        lblPrecio.setText(String.format("%.2f MXN", perfumeActual.getPrecio()));
        lblPresentacion.setText("Presentación: " + perfumeActual.getPresentacion());
        lblDescripcion.setText(perfumeActual.getDescripcion());

        if (perfumeActual.isDisponible()) {
            lblDisponible.setText("DISPONIBLE");
            lblDisponible.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111111;");
            btnAgregarCarrito.setDisable(false);
        } else {
            lblDisponible.setText("NO DISPONIBLE");
            lblDisponible.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #C94C4C;");
            btnAgregarCarrito.setDisable(true);
        }

        lblNotasSalida.setText(String.join(", ", perfumeActual.getNotasSalida()));
        lblNotasCorazon.setText(String.join(", ", perfumeActual.getNotasCorazon()));
        lblNotasFondo.setText(String.join(", ", perfumeActual.getNotasFondo()));

        try {
            imgPerfume.setImage(new Image(getClass().getResourceAsStream(perfumeActual.getImagen())));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen del perfume: " + perfumeActual.getImagen());
        }
    }

    private void cargarResenasDePrueba() {
        contenedorResenas.getChildren().clear();

        agregarResenaVisual(
                "Excelente",
                "Precio",
                5,
                "Llegó en excelentes condiciones y el aroma es exquisito. Lo recomiendo bastante.",
                "Sailormoon",
                "14/01/26"
        );

        agregarResenaVisual(
                "Todo muy bien",
                "Calidad",
                5,
                "El perfume huele muy rico y se siente original. La duración es muy buena.",
                "Luis",
                "11/01/26"
        );

        agregarResenaVisual(
                "Muy buena compra",
                "Calidad",
                4,
                "Me gustó mucho la presentación y el aroma. Lo volvería a comprar.",
                "Andrea",
                "09/01/26"
        );

        lblCantidadResenas.setText(contenedorResenas.getChildren().size() + " Reseñas");
    }

    private void agregarResenaVisual(String titulo, String categoria, int estrellas,
                                     String comentario, String usuario, String fecha) {

        VBox card = new VBox();
        card.setSpacing(8);
        card.setStyle("-fx-background-color: white; -fx-padding: 0 0 18 0; -fx-border-color: transparent transparent #E5E5E5 transparent;");

        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        Label lblContenido = new Label(
                categoria + "   " + generarEstrellas(estrellas) + "  " + comentario
        );
        lblContenido.setWrapText(true);
        lblContenido.setStyle("-fx-font-size: 15px; -fx-text-fill: #666666;");

        Label lblAutor = new Label("Comentario por " + usuario + "  " + fecha);
        lblAutor.setStyle("-fx-font-size: 14px; -fx-text-fill: #777777;");

        card.getChildren().addAll(lblTitulo, lblContenido, lblAutor);
        contenedorResenas.getChildren().add(card);
    }

    private String generarEstrellas(int cantidad) {
        StringBuilder estrellas = new StringBuilder();

        for (int i = 0; i < cantidad; i++) {
            estrellas.append("★");
        }

        for (int i = cantidad; i < 5; i++) {
            estrellas.append("☆");
        }

        return estrellas.toString();
    }

    @FXML
    private void agregarAlCarrito() {
        if (perfumeActual == null) {
            return;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Carrito");
        alerta.setHeaderText(null);
        alerta.setContentText("Se agregó al carrito: " + perfumeActual.getNombre()
                + "\nCantidad: " + cantidad);
        alerta.showAndWait();

        /*
        ==========================================================
        AQUÍ DESPUÉS SE AGREGARÁ AL CARRITO REAL
        ==========================================================

        Ejemplo futuro:

        CarritoService.agregarProducto(perfumeActual, cantidad);
        */
    }

    @FXML
    private void abrirFormularioResena() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Reseña");
        alerta.setHeaderText(null);
        alerta.setContentText("Aquí se abrirá el formulario para agregar una reseña.");
        alerta.showAndWait();

        /*
        ==========================================================
        AQUÍ DESPUÉS SE ABRIRÁ EL FORMULARIO PARA AGREGAR RESEÑA
        ==========================================================

        Esa reseña se guardará en Neo4j relacionada al perfume actual.

        Ejemplo futuro:

        (Cliente)-[:REALIZO_RESEÑA {
            comentario: "...",
            calificacion: 5,
            fecha: "..."
        }]->(Perfume {idPerfume: perfumeActual.getIdPerfume()})
        */
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
    
    @FXML
    private void scenePrincipal(javafx.event.ActionEvent event) {
        cambiarEscena("/scenes/principal.fxml", (Node) event.getSource());
    }
}