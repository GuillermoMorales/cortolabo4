/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Producto {
    private String codigo;
    private double precio;
    private String nombre;
    private int cantidad;
    private boolean disponibilidad;
    private String tipo;

    public Producto() {
    }

    public Producto(String codigo, double precio, String nombre, int cantidad, boolean disponibilidad, String tipo) {
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
    }
    
    
    public Producto( String nombre, String codigo, String tipo, int cantidad, double precio,boolean disponibilidad) {
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
    }

    public Producto(double precio, String nombre, int cantidad, boolean disponibilidad, String tipo) {
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
    }

    public Producto(String nombre, int cantidad, boolean disponibilidad, String tipo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
    }

    public Producto(String codigo, double precio, boolean disponibilidad, String tipo) {
        this.codigo = codigo;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.tipo = tipo;
    }

    public Producto(String codigo) {
        this.codigo = codigo;
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
