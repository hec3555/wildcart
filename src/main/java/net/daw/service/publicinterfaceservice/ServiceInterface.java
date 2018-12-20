/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service.publicinterfaceservice;

import net.daw.bean.specificimplementationbean.ReplyBean;

/**
 *
 * @author hec3555
 */
public interface ServiceInterface {
    public ReplyBean get() throws Exception;

    public ReplyBean remove() throws Exception;

    public ReplyBean getcount() throws Exception;

    public ReplyBean create() throws Exception;

    public ReplyBean update() throws Exception;

    public ReplyBean getpage() throws Exception;
}
