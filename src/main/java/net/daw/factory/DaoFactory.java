/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.factory;

import java.sql.Connection;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.dao.specificimplementationdao.implementationdao_0.FacturaDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_0.LineaDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_0.ProductoDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_0.TipoproductoDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_0.TipousuarioDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_0.UsuarioDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_1.FacturaDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_1.LineaDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_1.ProductoDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_1.TipoproductoDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_1.TipousuarioDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_1.UsuarioDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_2.FacturaDao_2;
import net.daw.dao.specificimplementationdao.implementationdao_2.LineaDao_2;
import net.daw.dao.specificimplementationdao.implementationdao_2.ProductoDao_2;
import net.daw.dao.specificimplementationdao.implementationdao_2.TipoproductoDao_2;
import net.daw.dao.specificimplementationdao.implementationdao_2.TipousuarioDao_2;
import net.daw.dao.specificimplementationdao.implementationdao_2.UsuarioDao_2;

/**
 *
 * @author a004631408j
 */
public class DaoFactory {

    public static DaoInterface getDao(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) throws Exception {
        DaoInterface oDao = null;
        int idUsuario;
        if (oUsuarioBeanSession != null) {
            idUsuario = oUsuarioBeanSession.getObj_tipoUsuario().getId();
        } else {
            idUsuario = 0;
        }

        // En mi bbdd, el tipousuario 1 es cliente y el 2 es admin.
        switch (idUsuario) {
            case 0:
                switch (ob) {
                    case "usuario":
                        oDao = new UsuarioDao_0(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "tipousuario":
                        oDao = new TipousuarioDao_0(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "tipoproducto":
                        oDao = new TipoproductoDao_0(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "producto":
                        oDao = new ProductoDao_0(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "factura":
                        oDao = new FacturaDao_0(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "linea":
                        oDao = new LineaDao_0(oConnection, ob, oUsuarioBeanSession);
                        break;
                }
                break;
            case 1:
                switch (ob) {
                    case "usuario":
                        oDao = new UsuarioDao_1(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "tipousuario":
                        oDao = new TipousuarioDao_1(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "tipoproducto":
                        oDao = new TipoproductoDao_1(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "producto":
                        oDao = new ProductoDao_1(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "factura":
                        oDao = new FacturaDao_1(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "linea":
                        oDao = new LineaDao_1(oConnection, ob, oUsuarioBeanSession);
                        break;
                }
                break;
            case 2:
                switch (ob) {
                    case "usuario":
                        oDao = new UsuarioDao_2(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "tipousuario":
                        oDao = new TipousuarioDao_2(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "tipoproducto":
                        oDao = new TipoproductoDao_2(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "producto":
                        oDao = new ProductoDao_2(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "factura":
                        oDao = new FacturaDao_2(oConnection, ob, oUsuarioBeanSession);
                        break;
                    case "linea":
                        oDao = new LineaDao_2(oConnection, ob, oUsuarioBeanSession);
                        break;
                }
                break;
            default:
                throw new Exception("Error en Dao factory de " + ob);
        }

        return oDao;
    }
}
