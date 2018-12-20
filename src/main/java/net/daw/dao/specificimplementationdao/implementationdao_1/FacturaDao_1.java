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
import net.daw.bean.specificimplementationbean.FacturaBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.helper.SqlBuilder;

/**
 *
 * @author a004631408j
 */
public class FacturaDao_1 extends DaoGeneric implements DaoInterface {

    public FacturaDao_1(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);

    }

    @Override
    public BeanInterface get(int id, Integer expand) throws Exception {
        FacturaBean oFacturaBean = (FacturaBean) super.get(id, expand);
        if (oFacturaBean.getObj_Usuario().getId() == oUsuarioBeanSession.getId()) {
            return oFacturaBean;
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
        strSQL_getcount = "SELECT COUNT(id) FROM " + ob + " WHERE id_usuario=" + oUsuarioBeanSession.getId();
        return super.getcount();
    }

//    un cliente debe poder crear su factura (pero al hacer un buy), habra que pensar
//    como validar que la factura creada sea para el usuario que hay en sesion
    
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

    public int getcountXusuario(Integer idUsuario) throws Exception {
        return this.getcount();
    }

    public ArrayList<FacturaBean> getpageXusuario(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand, Integer idUsuario) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_usuario = ?";
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<FacturaBean> alFacturaBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);

                oPreparedStatement.setInt(1, oUsuarioBeanSession.getId());
                // Le obligo a que las facturas visualizadas seran del usuario que hay en sesion, aqui iba la variable isUsuario

                oResultSet = oPreparedStatement.executeQuery();
                alFacturaBean = new ArrayList<>();
                while (oResultSet.next()) {
                    FacturaBean oFacturaBean = new FacturaBean();
                    oFacturaBean.fill(oResultSet, oConnection, expand, oUsuarioBeanSession);
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
