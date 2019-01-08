/*
 * ADMINISTRADOR
 */
package net.daw.dao.specificimplementationdao.implementationdao_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.specificimplementationbean.LineaBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.helper.SqlBuilder;

/**
 *
 * @author Usuario
 */
public class LineaDao_2 extends DaoGeneric implements DaoInterface {
    
    public LineaDao_2(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
    }
   
   
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
