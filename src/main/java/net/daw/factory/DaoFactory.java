/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.factory;

import java.sql.Connection;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.dao.specificimplementationdao.FacturaDao;
import net.daw.dao.specificimplementationdao.LineaDao;
import net.daw.dao.specificimplementationdao.ProductoDao;
import net.daw.dao.specificimplementationdao.TipoproductoDao;
import net.daw.dao.specificimplementationdao.TipousuarioDao;
import net.daw.dao.specificimplementationdao.UsuarioDao;

/**
 *
 * @author a004631408j
 */
public class DaoFactory {

    public static DaoInterface getDao(Connection oConnection, String ob) throws Exception {
        DaoInterface oDao = null;
        switch (ob) {
            case "usuario":
                oDao = new UsuarioDao(oConnection, ob);
                break;
            case "tipousuario":
                oDao = new TipousuarioDao(oConnection, ob);
                break;
            case "tipoproducto":
                oDao = new TipoproductoDao(oConnection, ob);
                break;
            case "producto":
                oDao = new ProductoDao(oConnection, ob);
                break;
            case "factura":
                oDao = new FacturaDao(oConnection, ob);
                break;
            case "linea":
                oDao = new LineaDao(oConnection, ob);
                break;
            default:
                throw new Exception("Operation doesn't exist " + ob);
        }

        return oDao;
    }
}
