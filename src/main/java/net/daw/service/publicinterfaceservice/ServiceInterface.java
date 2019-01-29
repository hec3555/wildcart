
package net.daw.service.publicinterfaceservice;

import net.daw.bean.specificimplementationbean.ReplyBean;

/**
 *
 * @author a004631408j
 */
public interface ServiceInterface {

    public ReplyBean get() throws Exception;

    public ReplyBean remove() throws Exception;

    public ReplyBean getcount() throws Exception;

    public ReplyBean create() throws Exception;

    public ReplyBean update() throws Exception;

    public ReplyBean getpage() throws Exception;
}
