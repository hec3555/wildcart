/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao.specificimplementationdao;

import java.sql.Connection;
import net.daw.dao.genericimplementationdao.DaoGeneric;
import net.daw.dao.publicinterfacedao.DaoInterface;

/**
 *
 * @author Usuario
 */
public class ProductoDao extends DaoGeneric implements DaoInterface{
    
    public ProductoDao(Connection oConnection, String ob) {
        super(oConnection, ob);
    }
    
}
