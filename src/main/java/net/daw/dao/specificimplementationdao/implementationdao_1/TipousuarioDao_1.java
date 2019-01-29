/*
 * CLIENTE
 */
package net.daw.dao.specificimplementationdao.implementationdao_1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.publicinterfacebean.BeanInterface;
import net.daw.bean.specificimplementationbean.TipousuarioBean;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;

/**
 *
 * @author Usuario
 */
public class TipousuarioDao_1 extends DaoGeneric implements DaoInterface {

    public TipousuarioDao_1(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
    }

    @Override
    public BeanInterface get(int id, Integer expand) throws Exception {
        if (id == oUsuarioBeanSession.getObj_tipoUsuario().getId()) {
            TipousuarioBean oTipousuarioBean = (TipousuarioBean) super.get(id, expand);
            return oTipousuarioBean;
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

    @Override
    public BeanInterface create(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao create de " + ob + ": No autorizado");
    }

    @Override
    public int update(BeanInterface oBean) throws Exception {
        throw new Exception("Error en Dao update de " + ob + ": No autorizado");
    }

    @Override
    public ArrayList<BeanInterface> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        throw new Exception("Error en Dao getpage de " + ob + ": No autorizado");
    }

}
