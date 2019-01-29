/*
 * NO LOGEADO
 */
package net.daw.service.specificimplementationservice.implementationservice_0;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specificimplementationbean.ReplyBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.specificimplementationdao.implementationdao_0.UsuarioDao_0;
import net.daw.factory.ConnectionFactory;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;

/**
 *
 * @author Usuario
 */
public class UsuarioService_0 extends ServiceGeneric implements ServiceInterface {

    public UsuarioService_0(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
        this.oRequest = oRequest;
        this.ob = ob;
    }

    @Override
    public ReplyBean get() throws Exception {
        throw new Exception("Error en Service get de " + ob + ": No autorizado");
    }

    @Override
    public ReplyBean remove() throws Exception {
        throw new Exception("Error en Service remove de " + ob + ": No autorizado");
    }

    @Override
    public ReplyBean getcount() throws Exception {
        throw new Exception("Error en Service getcount de " + ob + ": No autorizado");
    }

    @Override
    public ReplyBean create() throws Exception {
        throw new Exception("Error en Service create de " + ob + ": No autorizado");
    }

    @Override
    public ReplyBean update() throws Exception {
        throw new Exception("Error en Service update de " + ob + ": No autorizado");
    }

    @Override
    public ReplyBean getpage() throws Exception {
        throw new Exception("Error en Service getpage de " + ob + ": No autorizado");
    }

    public ReplyBean login() throws Exception {
        ReplyBean oReplyBean;
        ConnectionInterface oConnectionPool = null;
        Connection oConnection;
        String strLogin = oRequest.getParameter("user");
        String strPassword = oRequest.getParameter("pass");

        try {
            oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
            oConnection = oConnectionPool.newConnection();
            UsuarioDao_0 oUsuarioDao = new UsuarioDao_0(oConnection, ob, oUsuarioBeanSession);

            UsuarioBean oUsuarioBean = oUsuarioDao.login(strLogin, strPassword);
            UsuarioBean oUsuarioBean2 = oUsuarioBean;
            if (oUsuarioBean != null) {
                if (oUsuarioBean.getId() > 0) {
                    oRequest.getSession().setAttribute("user", oUsuarioBean);
                    Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
                    oReplyBean = new ReplyBean(200, oGson.toJson(oUsuarioBean));
                } else {
                    oReplyBean = new ReplyBean(401, "jajaj tus muertos");
                }
            } else {
//            throw new Exception("ERROR Bad Authentication: Service level: get page: " + ob + " object");
                oReplyBean = new ReplyBean(401, "Bad Authentication");
            }
        } catch (Exception ex) {
            throw new Exception("ERROR Bad Authentication: Service level: login: " + ob + " object");
        } finally {
            oConnectionPool.disposeConnection();
        }

        return oReplyBean;
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
