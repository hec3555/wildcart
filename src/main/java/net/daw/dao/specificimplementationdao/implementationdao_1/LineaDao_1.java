/*
 * CLIENTE
 */
package net.daw.dao.specificimplementationdao.implementationdao_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.publicinterfacebean.BeanInterface;
import net.daw.bean.specificimplementationbean.LineaBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.helper.SqlBuilder;

/**
 *
 * @author Usuario
 */
public class LineaDao_1 extends DaoGeneric implements DaoInterface {

    public LineaDao_1(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
    }

    @Override
    public BeanInterface get(int id, Integer expand) throws Exception {
        LineaBean oLineaBean = (LineaBean) super.get(id, expand);
        if (oLineaBean.getObj_Factura().getObj_Usuario().getId() == oUsuarioBeanSession.getId()) {
            return oLineaBean;
        } else {
            throw new Exception("Error en Dao get de " + ob + ": No autorizado");
        }
    }

    @Override
    public int remove(int id) throws Exception {
        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
    }

    @Override
    public int getcount() throws Exception {
        throw new Exception("Error en Dao getcount de " + ob + ": No autorizado");
    }

//    Un cliente debe poder crear lineas para una factura, pero habra que validar
//    que la factura le pertenece a ese cliente...
    
//    @Override 
//    public BeanInterface create(BeanInterface oBean) throws Exception {
//        throw new Exception("Error en Dao create de " + ob + ": No autorizado");
//    }

    @Override
    public int update(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao update de " + ob + ": No autorizado");
    }

    @Override
    public ArrayList<BeanInterface> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new Exception("Error en Dao getpage de " + ob + ": No autorizado");

    }
    
    
//  lo mismo, un cliente puede saber cuantas lineas tiene una factura, y verlas
//  pero esa factura debe pertenecerle al cliente, de esta manera puede ver las
//  lineas de cualquier factura que pertenecera a cualquier usuario, sea o no 
//  el que hay en sesion
    
    public int getcountXfactura(Integer idFact) throws Exception {
        String strSQL = "SELECT COUNT(id) FROM " + ob + " WHERE id_factura = ?";
        int res = 0;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, idFact);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                res = oResultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return res;
    }

    public ArrayList<LineaBean> getpageXfactura(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand, Integer idFact) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_factura = ?";
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<LineaBean> alLineaBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, idFact);
                oResultSet = oPreparedStatement.executeQuery();
                alLineaBean = new ArrayList<>();
                while (oResultSet.next()) {
                    LineaBean oLineaBean = new LineaBean();
                    oLineaBean.setId(oResultSet.getInt("id"));
                    oLineaBean.fill(oResultSet, oConnection, expand, oUsuarioBeanSession);
                    alLineaBean.add(oLineaBean);
                }
            } catch (SQLException e) {
                throw new Exception("Error en Dao getpage de " + ob, e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        } else {
            throw new Exception("Error en Dao getpage de " + ob);
        }
        return alLineaBean;

    }

}
