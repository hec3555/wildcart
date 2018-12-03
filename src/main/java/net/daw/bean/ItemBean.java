package net.daw.bean;

import com.google.gson.annotations.Expose;


public class ItemBean {
    @Expose
    private int cantidad;
    @Expose
    private ProductoBean producto;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoBean getProducto() {
        return producto;
    }

    public void setProducto(ProductoBean producto) {
        this.producto = producto;
    }
    
}
