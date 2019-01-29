/*
 * ADMINISTRADOR
 */
package net.daw.dao.specificimplementationdao.implementationdao_2;

import java.sql.Connection;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;

/**
 *
 * @author Usuario
 */
public class TipousuarioDao_2 extends DaoGeneric implements DaoInterface {

    public TipousuarioDao_2(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
    }

}
