/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.factory;

/**
 *
 * @author a004631408j
 */
public class DaoFactory {
//    public static DaoInterface getDao(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) throws Exception {
//        DaoInterface oDao = null;
//        int idUsuario = 0;
//        if (oUsuarioBeanSession != null) {
//            idUsuario = oUsuarioBeanSession.getObj_tipoUsuario().getId();
//        } else {
//            idUsuario = 0;
//        }
//
//        switch (idUsuario) {
//            case 1:
//                switch (ob) {
//                    case "usuario":
//                        oDao = new UsuarioDao_1(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "tipousuario":
//                        oDao = new TipousuarioDao_1(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "tipoproducto":
//                        oDao = new TipoproductoDao_1(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "producto":
//                        oDao = new ProductoDao_1(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "factura":
//                        oDao = new FacturaDao_1(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "linea":
//                        oDao = new LineaDao_1(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                }
//                break;
//            case 2:
//                switch (ob) {
//                    case "usuario":
//                        oDao = new UsuarioDao_2(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "tipousuario":
//                        oDao = new TipousuarioDao_2(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "tipoproducto":
//                        oDao = new TipoproductoDao_2(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "producto":
//                        oDao = new ProductoDao_2(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "factura":
//                        oDao = new FacturaDao_2(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                    case "linea":
//                        oDao = new LineaDao_2(oConnection, ob, oUsuarioBeanSession);
//                        break;
//                }
//                break;
//            case 0:
//                if ("usuario".equals(ob)) {
//                    oDao = new UsuarioDao_0(oConnection, ob,oUsuarioBeanSession);
//                    break;
//                }
//                break;
//            default:
//                throw new Exception("Error en Dao factory de " + ob);
//        }
//
//        return oDao;
//    }
}
