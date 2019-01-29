/*
 * CLIENTE
 */
package net.daw.service.specificimplementationservice.implementationservice_1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specificimplementationbean.ReplyBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.helper.EncodingHelper;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;

/**
 *
 * @author Usuario
 */
public class UsuarioService_1 extends ServiceGeneric implements ServiceInterface {

    public UsuarioService_1(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
    }

    protected Boolean checkPermission(String strMethodName) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        return oUsuarioBean != null;
    }

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
