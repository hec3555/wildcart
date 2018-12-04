package net.daw.bean;

import com.google.gson.annotations.Expose;


public class ItemBean {
    @Expose
    private int cantidad;
    @Expose
    private ProductoBean obj_Producto;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoBean getObj_Producto() {
        return obj_Producto;
    }

    public void setObj_Producto(ProductoBean obj_Producto) {
        this.obj_Producto = obj_Producto;
    }
    
}
