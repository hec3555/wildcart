/*
 * CLIENTE
 */
package net.daw.dao.specificimplementationdao.implementationdao_1;

import java.sql.Connection;
import net.daw.bean.specificimplementationbean.UsuarioBean;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;

/**
 *
 * @author Usuario
 */
public class ProductoDao_1 extends DaoGeneric implements DaoInterface{
    
    public ProductoDao_1(Connection oConnection, String ob, UsuarioBean oUsuarioBeanSession) {
        super(oConnection, ob, oUsuarioBeanSession);
    }
    
}
