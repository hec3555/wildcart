/*
 * CLIENTE
 */
package net.daw.dao.specificimplementationdao.implementationdao_1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.publicinterfacebean.BeanInterface;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;

/**
 *
 * @author hec3555
 */
public class UsuarioDao_1 extends DaoGeneric implements DaoInterface {

    public UsuarioDao_1(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
    }

    @Override
    public BeanInterface get(int id, Integer expand) throws Exception {
        if (id == oUsuarioBeanSession.getId()) {
            return (UsuarioBean) super.get(id, expand);
        } else {
            throw new Exception("Error en Dao get de " + ob + ": No autorizado");
        }
    }

    @Override
    public int update(BeanInterface oBean) throws Exception {
        if (oBean.getId() == oUsuarioBeanSession.getId()) {
            return super.update(oBean);
        } else {
            throw new Exception("Error en Dao update de " + ob + ": No autorizado");
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

    @Override
    public BeanInterface create(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao create de " + ob + ": No autorizado");
    }

    @Override
    public ArrayList<BeanInterface> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new Exception("Error en Dao getpage de " + ob + ": No autorizado");
    }
}
