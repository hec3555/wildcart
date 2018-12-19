/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.daw.bean.specificimplementationbean.FacturaBean;
import net.daw.bean.specificimplementationbean.ItemBean;
import net.daw.bean.specificimplementationbean.LineaBean;
import net.daw.bean.specificimplementationbean.ProductoBean;
import net.daw.bean.specificimplementationbean.ReplyBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;

import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.FacturaDao;
import net.daw.dao.LineaDao;
import net.daw.dao.ProductoDao;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author usuario
 */
public class CarritoService{

    HttpServletRequest oRequest;
    String ob = null;
    Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
//    Gson oGson = new Gson();
    ReplyBean oReplyBean;
    ArrayList<ItemBean> carrito = null;
    Connection oConnection = null;

    public CarritoService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

    public ReplyBean add() throws Exception {
        ConnectionInterface oConnectionPool = null;
        //Obtenemos la sesion actual
        HttpSession sesion = oRequest.getSession();

        try {


            //Si no hay carrito en la sesion, lo creamos, si lo hay, lo recuperamos
            if (sesion.getAttribute("carrito") == null) {
                carrito = new ArrayList<>();
            } else {
                carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
            }

            //Obtenemos el producto que deseamos a�adir al carrito 
            Integer id = Integer.parseInt(oRequest.getParameter("producto"));
            // y la cantidad
            Integer cant = Integer.parseInt(oRequest.getParameter("cantidad"));
            
            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            ProductoDao oProductoDao = new ProductoDao(oConnection, "producto");
            // creamos el producto
            ProductoBean oProductoBean = oProductoDao.get(id, 1);
            //Recogemos las existencias que tiene dicho producto
            Integer existencias = oProductoBean.getExistencias();

            //Para saber si tenemos agregado el producto al carrito de compras
            int indice = -1;
            //recorremos todo el carrito
            for (int i = 0; i < carrito.size(); i++) {
                if (oProductoBean.getId() == carrito.get(i).getObj_Producto().getId()) {
                    //Si el producto ya esta en el carrito, obtengo el indice para
                    // no a�adir dos iguales, y en su lugar aumentar la cantidad
                    indice = i;
                    break;
                }
            }
            // Creamos un item
            ItemBean oItemBean = new ItemBean();
            if (indice == -1) { //Si es -1 es porque el producto es nuevo en el carrito
                if (existencias > 0 && existencias >= cant) {
                    // si hay existencias, y estas son mayores o igual a la cantidad demandada,
                    // creamos el item.
                    oItemBean.setObj_Producto(oProductoBean);
                    oItemBean.setCantidad(cant);
                    // y lo agregamos al carrito
                    carrito.add(oItemBean);
                } else {
                    // Si la cantidad demandada es mayor a las existencias
                    // ponemos las existencias maximas de ese producto.                    
                    if (existencias > 0) {
                        oItemBean.setObj_Producto(oProductoBean);
                        oItemBean.setCantidad(existencias);
                        carrito.add(oItemBean);
                    }
                }
            } else {
                //Si es otro valor es porque el producto esta en el carrito
                //y vamos actualizar la cantidad
                Integer cantidad = carrito.get(indice).getCantidad() + cant;
                if (existencias >= cantidad) {
                    carrito.get(indice).setCantidad(cantidad);
                }
            }
            //Actualizamos el carrito en la sesion
            sesion.setAttribute("carrito", carrito);

            oReplyBean = new ReplyBean(200, oGson.toJson(carrito));

        } catch (Exception ex) {
            oReplyBean = new ReplyBean(500, "Error en add carritoService: " + ex.getMessage());
        } finally {
            oConnectionPool.disposeConnection();
        }
        return oReplyBean;
    }

    public ReplyBean empty() throws Exception {
        HttpSession sesion = oRequest.getSession();
        carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
        carrito.clear();
        sesion.setAttribute("carrito", carrito);
        oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Carrito vacio"));
        return oReplyBean;
    }

    public ReplyBean show() throws Exception {
        HttpSession sesion = oRequest.getSession();
        try {
            carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
            if (carrito == null || carrito.size() <= 0) { // si esta vacio
                oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Carrito vacio"));
            } else { // si no, mandamos el carrito que esta en la sesion
                // Sacamos del request el carrito, el request viene del cliente, no se puede 
                // sacar el carrito directamente del cliente sin tener que hacer peticion al servidor?
                oReplyBean = new ReplyBean(200, oGson.toJson(oRequest.getSession().getAttribute("carrito")));
            }

        } catch (Exception e) {
            oReplyBean = new ReplyBean(500, "Error en show carritoService: " + e.getMessage());
        }
        return oReplyBean;
    }

    public ReplyBean reduce() throws Exception {
        //Obtenemos la sesion actual
        HttpSession sesion = oRequest.getSession();

        try {
            //obtemos el carrito 
            carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");

            //Obtenemos el producto que deseamos a�adir al carrito
            Integer id = Integer.parseInt(oRequest.getParameter("producto"));
            Integer cantidad = Integer.parseInt(oRequest.getParameter("cantidad"));
            Integer contenedor;
            Integer resta;
            //Para saber si tenemos agregado el producto al carrito de compras
            //recorremos todo el carrito de compras
            for (int i = 0; i < carrito.size(); i++) {
                if (id == carrito.get(i).getObj_Producto().getId()) {
                    contenedor = carrito.get(i).getCantidad();
                    resta = contenedor - cantidad;
                    if (resta == 0) { 
                    // si la resta es 0, significa que quiere cantidad 0 de ese producto, ergo lo eliminamos del carrito.
                        carrito.remove(i);
                    } else {
                    // si no, se hace la reduccion
                        carrito.get(i).setCantidad(resta);
                    }
                    break;
                }
            }
            // metemos el carrito con la info actualizada a la sesion
            sesion.setAttribute("carrito", carrito);

            oReplyBean = new ReplyBean(200, oGson.toJson(carrito));

        } catch (Exception ex) {
            oReplyBean = new ReplyBean(500, "Error en reduce carritoService: " + ex.getMessage());
        }
        return oReplyBean;
    }

// ================ En principio un update no pinta nada ======================
//    public ReplyBean update() throws Exception {
//
//        ConnectionInterface oConnectionPool = null;
//        //Obtenemos la sesion actual
//        HttpSession sesion = oRequest.getSession();
//
//        carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
//
//        try {
//            Integer id = Integer.parseInt(oRequest.getParameter("producto"));
//            Integer cant = Integer.parseInt(oRequest.getParameter("cantidad"));
//            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
//            oConnection = oConnectionPool.newConnection();
//            ProductoDao oProductoDao = new ProductoDao(oConnection, "producto");
//            ProductoBean oProductoBean = oProductoDao.get(id, 2);
//
//            Integer existencias = oProductoBean.getExistencias();
//
//            for (ItemBean ib : carrito) {
//
//                if (ib.getObj_Producto().getId() == id) {
//
//                    if (oProductoBean.getExistencias() > 0) {
//
//                        if (cant <= oProductoBean.getExistencias()) {
//                            ib.setCantidad(cant);
//                        } else {
//
//                            ib.setCantidad(oProductoBean.getExistencias());
//                        }
//                    }
//                }
//
//            }
//
//            oReplyBean = new ReplyBean(200, oGson.toJson(carrito));
//
//        } catch (Exception e) {
//            oReplyBean = new ReplyBean(500, "Error en update carritoService: " + e.getMessage());
//        } finally {
//            oConnectionPool.disposeConnection();
//        }
//
//        return oReplyBean;
//    }
// =============================================================================

    public ReplyBean buy() throws Exception {

        ConnectionInterface oConnectionPool = null;
        //Obtenemos la sesion actual
        HttpSession sesion = oRequest.getSession();

        try {

            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            // para que se ejecute todo junto, si se para a mitad (por ejemplo corte de luz o algun error)
            // no se realiza ningun cambio en la bbdd.
            oConnection.setAutoCommit(false);
            int id = ((UsuarioBean) sesion.getAttribute("user")).getId();
            carrito = (ArrayList<ItemBean>) sesion.getAttribute("carrito");
            
            // creamos el bean de factura
            FacturaBean oFacturaBean = new FacturaBean();
            Date fechaHoraAhora = new Date();
            oFacturaBean.setId_usuario(id);
            oFacturaBean.setFecha(fechaHoraAhora);
            oFacturaBean.setIva(21.0f);

            
            FacturaDao oFacturaDao = new FacturaDao(oConnection, "factura");
            // y la creamos en la bbdd
            FacturaBean oFacturaBeanCreada = oFacturaDao.create(oFacturaBean);
            // obtenemos el id de la factura creada para meterle las lineas
            int id_factura = oFacturaBeanCreada.getId();
            
            LineaDao oLineaDao;
            LineaBean oLineaBean;
            ProductoDao oProductoDao = new ProductoDao(oConnection, "producto");
            oLineaDao = new LineaDao(oConnection, "linea");
            ProductoBean oProductoBean;

            for (ItemBean ib : carrito) { // por cada item del carrito, generamos una linea

                //CREAMOS LA L�NEA
                int cant = ib.getCantidad();
                
                // instanciamos el bean de linea para que se vacie 
                // (aunque con los set ya modificariamos sus valores, no hace falta)
                oLineaBean = new LineaBean();

                // llenamos el bean
                oLineaBean.setId_factura(id_factura);
                oLineaBean.setId_producto(ib.getObj_Producto().getId());
                oLineaBean.setCantidad(cant);
                
                // y creamos la linea en la bbdd
                oLineaDao.create(oLineaBean);

                //RESTAMOS EXISTENCIAS DE LA BBDD
                oProductoBean = new ProductoBean();

                oProductoBean.setId(ib.getObj_Producto().getId());
                

                oProductoBean = ib.getObj_Producto();
                // Le restamos la cantidad comprada a las existencias del producto
                oProductoBean.setExistencias(oProductoBean.getExistencias() - cant);
                
                // y actualizamos la info en la bbdd
                oProductoDao.update(oProductoBean);

            }
            // Ejecutamos toda la transaccion
            oConnection.commit();
            
            // vaciamos el carrito tras la compra
            carrito.clear();
            sesion.setAttribute("carrito", carrito);
            // si quiero que le llegue al cliente el id factura para luego hacerle
            // un view al crearla:
            // oReplyBean = new ReplyBean(200, oGson.toJson(id_factura));
            oReplyBean = new ReplyBean(200, EncodingHelper.quotate("Factura n� " + id_factura + " creada con �xito"));

        } catch (Exception e) {

            try {
                oConnection.rollback();
            } catch (SQLException excep) {

            }

            oReplyBean = new ReplyBean(500, "Error en buy carritoService: " + e.getMessage());
        } finally {
            oConnectionPool.disposeConnection();
        }

        return oReplyBean;

    }

}