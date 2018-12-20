/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service.specificimplementationservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specificimplementationbean.LineaBean;
import net.daw.bean.specificimplementationbean.ReplyBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.specificimplementationdao.LineaDao;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;
import net.daw.helper.ParameterCook;
import net.daw.service.genericimplementationservice.ServiceGeneric;
import net.daw.service.publicinterfaceservice.ServiceInterface;

/**
 *
 * @author Usuario
 */
public class LineaService extends ServiceGeneric implements ServiceInterface  {
    
    public LineaService(HttpServletRequest oRequest, String ob) {
        super(oRequest, ob);
    }
    
    public ReplyBean getcountXfactura() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
                        Integer idFact = Integer.parseInt(oRequest.getParameter("idfact"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			LineaDao oLineaDao = new LineaDao(oConnection, ob, oUsuarioBeanSession);
			int registros = oLineaDao.getcountXfactura(idFact);
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			oReplyBean = new ReplyBean(200, oGson.toJson(registros));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
        
        
        public ReplyBean getpageXfactura() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
			Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
                        HashMap<String, String> hmOrder = ParameterCook.getOrderParams(oRequest.getParameter("order"));
                        Integer idFact = Integer.parseInt(oRequest.getParameter("idfact"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			LineaDao oLineaDao = new LineaDao(oConnection, ob, oUsuarioBeanSession);
			ArrayList<LineaBean> alLineaBean = oLineaDao.getpageXfactura(iRpp, iPage,hmOrder,1,idFact);
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			oReplyBean = new ReplyBean(200, oGson.toJson(alLineaBean));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
    
}
