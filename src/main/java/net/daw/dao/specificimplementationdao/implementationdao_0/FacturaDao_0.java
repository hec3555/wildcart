/*
 * SIN SESION
 */
package net.daw.dao.specificimplementationdao.implementationdao_0;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.publicinterfacebean.BeanInterface;
import net.daw.bean.specificimplementationbean.FacturaBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;

/**
 *
 * @author a004631408j
 */
public class FacturaDao_0 extends DaoGeneric implements DaoInterface{
    
    
    
    public FacturaDao_0(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
        
    }
    
    public int getcountXusuario(Integer idUsuario) throws Exception {
        return this.getcount();
    }
    
    @Override
    public BeanInterface get(int id, Integer expand) throws Exception {
        throw new Exception("Error en Dao get de " + ob + ": No autorizado");
    }
    
    @Override
    public int remove(int id) throws Exception {
        throw new Exception("Error en Dao remove de " + ob + ": No autorizado");
    }

    @Override
    public int getcount() throws Exception {
        if (oUsuarioBeanSession != null) {
            strSQL_getcount = "SELECT COUNT(id) FROM " + ob + " WHERE id_usuario=" + oUsuarioBeanSession.getId();
        }
        return super.getcount();
    }

    @Override
    public BeanInterface create(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao create de " + ob + ": No autorizado");
    }
    
    // kevin lo tenia comentao el update, no deberia estar comentado. Si falla, probar a comentarlo.
    @Override
    public int update(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao update de " + ob + ": No autorizado");
    }
    
    @Override
    public ArrayList<BeanInterface> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new Exception("Error en Dao getpage de " + ob + ": No autorizado");

    }    
    
    public ArrayList<FacturaBean> getpageXusuario(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand, Integer idUsuario) throws Exception {
        throw new Exception("Error en Dao update de " + ob + ": No autorizado");
    }
    



    
    
    
    
    
    
    
    
}
