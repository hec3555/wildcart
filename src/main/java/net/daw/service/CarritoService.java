package net.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.ItemBean;
import net.daw.bean.ReplyBean;

/**
 *
 * @author hec3555
 */
public class CarritoService {

    HttpServletRequest oRequest;
    String ob = null;
    ReplyBean oReplyBean;
    ArrayList<ItemBean> carrito = null;
    Connection oConnection = null;
    Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
    
    
    public CarritoService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }
}
