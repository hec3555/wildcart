/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.specificimplementationbean;

import com.google.gson.annotations.Expose;

/**
 *
 * @author a004631408j
 */
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
