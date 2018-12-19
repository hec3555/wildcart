/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao.specificimplementationdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.specificimplementationbean.FacturaBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.helper.SqlBuilder;

/**
 *
 * @author a004631408j
 */
public class FacturaDao extends DaoGeneric implements DaoInterface{
    
    
    
    public FacturaDao(Connection oConnection, String ob) {
        super(oConnection, ob);
        
    }
    
    
    public int getcountXusuario(Integer idUsuario) throws Exception {
        strSQL_getcount = "SELECT COUNT(id) FROM " + ob + " WHERE id_usuario=" + idUsuario;
        return super.getcount();
    }
    
    
    public ArrayList<FacturaBean> getpageXusuario(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand, Integer idUsuario) throws Exception {
        String strSQL = "SELECT * FROM " + ob +" WHERE id_usuario = ?";
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<FacturaBean> alFacturaBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1,idUsuario);
                oResultSet = oPreparedStatement.executeQuery();
                alFacturaBean = new ArrayList<>();
                while (oResultSet.next()) {
                    FacturaBean oFacturaBean = new FacturaBean();
                    oFacturaBean.fill(oResultSet, oConnection, expand);
                    alFacturaBean.add(oFacturaBean);
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
        return alFacturaBean;

    }
    
    
    
    
    
    
    
    
}
