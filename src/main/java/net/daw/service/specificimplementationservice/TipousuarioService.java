/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service.specificimplementationservice;

import javax.servlet.http.HttpServletRequest;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;

/**
 *
 * @author Usuario
 */
public class TipousuarioService extends ServiceGeneric implements ServiceInterface  {
    
    public TipousuarioService(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
    }
    
}
