/*
 * CLIENTE
 */
package net.daw.service.specificimplementationservice.implementationservice_1;

import javax.servlet.http.HttpServletRequest;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;

/**
 *
 * @author Usuario
 */
public class TipoproductoService_1 extends ServiceGeneric implements ServiceInterface  {
    
    public TipoproductoService_1(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
    }
    
}
