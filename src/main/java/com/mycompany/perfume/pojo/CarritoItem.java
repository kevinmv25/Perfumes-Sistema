package com.mycompany.perfume.pojo;

public class CarritoItem {

    private int idPerfume;
    private String nombre;
    private double precio;
    private String imagen;
    private int cantidad;

    public CarritoItem(int idPerfume, String nombre, double precio, String imagen, int cantidad) {
        this.idPerfume = idPerfume;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }

    public int getIdPerfume() {
        return idPerfume;
    }

    public void setIdPerfume(int idPerfume) {
        this.idPerfume = idPerfume;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        cantidad++;
    }

    public void disminuirCantidad() {
        if (cantidad > 1) {
            cantidad--;
        }
    }

    public double getSubtotal() {
        return precio * cantidad;
    }
}