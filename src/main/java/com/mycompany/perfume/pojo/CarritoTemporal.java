package com.mycompany.perfume.pojo;

import java.util.ArrayList;
import java.util.List;

public class CarritoTemporal {

    private static final List<CarritoItem> carrito = new ArrayList<>();

    public static void agregarProducto(int idPerfume, String nombre, double precio, String imagen){

        for (CarritoItem item : carrito) {

            if (item.getIdPerfume() == idPerfume) {
                item.aumentarCantidad();
                return;
            }
        }

        carrito.add(
                new CarritoItem(
                        idPerfume,
                        nombre,
                        precio,
                        imagen,
                        1
                )
        );
    }

    public static List<CarritoItem> obtenerCarrito() {
        return carrito;
    }

    public static void eliminarProducto(CarritoItem item) {
        carrito.remove(item);
    }

    public static void vaciarCarrito() {
        carrito.clear();
    }

    public static double calcularTotal() {

        double total = 0;

        for (CarritoItem item : carrito) {
            total += item.getSubtotal();
        }

        return total;
    }

    public static int cantidadProductos() {

        int total = 0;

        for (CarritoItem item : carrito) {
            total += item.getCantidad();
        }

        return total;
    }
}