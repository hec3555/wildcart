/*
 * ADMINISTRADOR
 */
package net.daw.service.specificimplementationservice.implementationservice_2;

import net.daw.service.specificimplementationservice.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specificimplementationbean.ReplyBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.specificimplementationdao.implementationdao_2.UsuarioDao_2;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;

/**
 *
 * @author Usuario
 */
public class UsuarioService_2 extends ServiceGeneric implements ServiceInterface {
    
    public UsuarioService_2(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
    }
    
    protected Boolean checkPermission(String strMethodName) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        return oUsuarioBean != null;
    }
    
    public ReplyBean cargarUsuarios() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        RellenarService_2 rellenar = new RellenarService_2();
        if (this.checkPermission("fill")) {
            try {
                Integer numero = Integer.parseInt(oRequest.getParameter("numero"));
                oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
                oConnection = oConnectionPool.newConnection();
                UsuarioDao_2 oUsuarioDao = new UsuarioDao_2(oConnection, ob, oUsuarioBeanSession);
                ArrayList<UsuarioBean> alUsuarioBean = rellenar.fillUsuario(numero);

                for (UsuarioBean usuarios : alUsuarioBean) {
                    oUsuarioDao.create(usuarios);
                }
                Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
                oReplyBean = new ReplyBean(200, oGson.toJson(alUsuarioBean));
            } catch (Exception ex) {
                oReplyBean = new ReplyBean(500,
                        "ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
            } finally {
                oConnectionPool.disposeConnection();
            }
        } else {
            oReplyBean = new ReplyBean(401, "Unauthorized");
        }
        return oReplyBean;
    }

// Es un administrador ya logeado, no puede hacer login    
    
//    public ReplyBean login() throws Exception {
//        ReplyBean oReplyBean;
//        ConnectionInterface oConnectionPool = null;
//        Connection oConnection;
//        String strLogin = oRequest.getParameter("user");
//        String strPassword = oRequest.getParameter("pass");
//
//        try {
//            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
//            oConnection = oConnectionPool.newConnection();
//            UsuarioDao_2 oUsuarioDao = new UsuarioDao_2(oConnection, ob, oUsuarioBeanSession);
//
//            UsuarioBean oUsuarioBean = oUsuarioDao.login(strLogin, strPassword);
//            if (oUsuarioBean != null) {
//                oRequest.getSession().setAttribute("user", oUsuarioBean);
//                Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
//                oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
//            } else {
////            throw new Exception("ERROR Bad Authentication: Service level: get page: " + ob + " object");
//                oReplyBean = new ReplyBean(401, "Bad Authentication");
//            }
//        } catch (Exception ex) {
//            throw new Exception("ERROR Bad Authentication: Service level: login: " + ob + " object");
//        } finally {
//            oConnectionPool.disposeConnection();
//        }
//
//        return oReplyBean;
//    }

    public ReplyBean logout() throws Exception {
        oRequest.getSession().invalidate();
        return new ReplyBean(200, EncodingHelper.quotate("OK"));
    }

    public ReplyBean check() throws Exception {
        ReplyBean oReplyBean;
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
            oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
        } else {
            oReplyBean = new ReplyBean(401, "No active session");
        }
        return oReplyBean;
    }
}
