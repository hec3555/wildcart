/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service.specificimplementationservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specificimplementationbean.ProductoBean;
import net.daw.bean.specificimplementationbean.ReplyBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.specificimplementationdao.ProductoDao;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Usuario
 */
public class ProductoService extends ServiceGeneric implements ServiceInterface  {
    
    public ProductoService(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
    }
    
    public ReplyBean cargarProductos() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        RellenarService rellenar = new RellenarService();
        try {
            Integer numero = Integer.parseInt(oRequest.getParameter("numero"));
            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            ProductoDao oProductoDao = new ProductoDao(oConnection, ob);
            ArrayList<ProductoBean> alProductoBean = rellenar.fillProducto(numero);

            for (ProductoBean productos : alProductoBean) {
                oProductoDao.create(productos);
            }
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            oReplyBean = new ReplyBean(200, oGson.toJson(alProductoBean));
        } catch (Exception ex) {
            oReplyBean = new ReplyBean(500,
                    "ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
        } finally {
            oConnectionPool.disposeConnection();
        }
        return oReplyBean;
    }

    public ReplyBean addimage() throws Exception {
        String name = "";
        ReplyBean oReplyBean;
        HashMap<String, String> hash = new HashMap<>();
        if (ServletFileUpload.isMultipartContent(oRequest)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(oRequest);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        name = new File(item.getName()).getName();
                        item.write(new File(".//..//webapps//images//" + name));
//                        item.write(new File(name));  // para comprobar cual es la raiz del tomcat, donde la sube

                    } else {
                        hash.put(item.getFieldName(), item.getString());
                    }
                }
                oReplyBean = new ReplyBean(200, EncodingHelper.quotate("File uploaded Successfully: " + name));
            } catch (Exception ex) {
                oReplyBean = new ReplyBean(500, "Error while uploading file: " + name + ex);
            }
        } else {
            oReplyBean = new ReplyBean(500, "Cant read image");
        }
        return oReplyBean;
    }
    
}
