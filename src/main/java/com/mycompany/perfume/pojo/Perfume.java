package com.mycompany.perfume.pojo;

import java.util.ArrayList;
import java.util.List;

public class Perfume {

    private int idPerfume;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;
    private String presentacion;
    private boolean disponible;

    private String marca;
    private String familiaOlfativa;

    private List<String> notasSalida;
    private List<String> notasCorazon;
    private List<String> notasFondo;

    public Perfume() {
        this.notasSalida = new ArrayList<>();
        this.notasCorazon = new ArrayList<>();
        this.notasFondo = new ArrayList<>();
    }

    public Perfume(int idPerfume, String nombre, String descripcion, double precio,
                   String imagen, String presentacion, boolean disponible,
                   String marca, String familiaOlfativa,
                   List<String> notasSalida, List<String> notasCorazon, List<String> notasFondo) {
        this.idPerfume = idPerfume;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.presentacion = presentacion;
        this.disponible = disponible;
        this.marca = marca;
        this.familiaOlfativa = familiaOlfativa;
        this.notasSalida = notasSalida;
        this.notasCorazon = notasCorazon;
        this.notasFondo = notasFondo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFamiliaOlfativa() {
        return familiaOlfativa;
    }

    public void setFamiliaOlfativa(String familiaOlfativa) {
        this.familiaOlfativa = familiaOlfativa;
    }

    public List<String> getNotasSalida() {
        return notasSalida;
    }

    public void setNotasSalida(List<String> notasSalida) {
        this.notasSalida = notasSalida;
    }

    public List<String> getNotasCorazon() {
        return notasCorazon;
    }

    public void setNotasCorazon(List<String> notasCorazon) {
        this.notasCorazon = notasCorazon;
    }

    public List<String> getNotasFondo() {
        return notasFondo;
    }

    public void setNotasFondo(List<String> notasFondo) {
        this.notasFondo = notasFondo;
    }

    public void agregarNotaSalida(String nota) {
        this.notasSalida.add(nota);
    }

    public void agregarNotaCorazon(String nota) {
        this.notasCorazon.add(nota);
    }

    public void agregarNotaFondo(String nota) {
        this.notasFondo.add(nota);
    }

    @Override
    public String toString() {
        return nombre + " - " + marca;
    }
}