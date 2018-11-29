/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.FacturaBean;
import net.daw.bean.ReplyBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.FacturaDao;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;
import net.daw.helper.ParameterCook;


public class FacturaService {
    HttpServletRequest oRequest;
	String ob = null;

	public FacturaService(HttpServletRequest oRequest) {
		super();
		this.oRequest = oRequest;
		ob = oRequest.getParameter("ob");
	}

	public ReplyBean get() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			FacturaBean oFacturaBean = oFacturaDao.get(id,1);
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			oReplyBean = new ReplyBean(200, oGson.toJson(oFacturaBean));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}

	public ReplyBean remove() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			int iRes = oFacturaDao.remove(id);
			oReplyBean = new ReplyBean(200, Integer.toString(iRes));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}
		return oReplyBean;

	}

	public ReplyBean getcount() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			int registros = oFacturaDao.getcount();
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

	public ReplyBean create() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			String strJsonFromClient = oRequest.getParameter("json");
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			FacturaBean oFacturaBean = new FacturaBean();
			oFacturaBean = oGson.fromJson(strJsonFromClient, FacturaBean.class);
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			oFacturaBean = oFacturaDao.create(oFacturaBean);
			oReplyBean = new ReplyBean(200, oGson.toJson(oFacturaBean));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}
		return oReplyBean;
	}

	public ReplyBean update() throws Exception {
		int iRes = 0;
		ReplyBean oReplyBean = null;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			String strJsonFromClient = oRequest.getParameter("json");
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			FacturaBean oFacturaBean = oGson.fromJson(strJsonFromClient, FacturaBean.class);
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			iRes = oFacturaDao.update(oFacturaBean);
                        oReplyBean = new ReplyBean(200, Integer.toString(iRes));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}
		return oReplyBean;
	}

	public ReplyBean getpage() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
			Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
                        HashMap<String, String> hmOrder = ParameterCook.getOrderParams(oRequest.getParameter("order"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			ArrayList<FacturaBean> alFacturaBean = oFacturaDao.getpage(iRpp, iPage,hmOrder,1);
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			oReplyBean = new ReplyBean(200, oGson.toJson(alFacturaBean));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
        
        
        public ReplyBean getcountXusuario() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
                    Integer idUsu = Integer.parseInt(oRequest.getParameter("idUsu"));
                    oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
                    oConnection = oConnectionPool.newConnection();
                    FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
                    int registros = oFacturaDao.getcountXusuario(idUsu);
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
        
        
        public ReplyBean getpageXusuario() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection;
		try {
			Integer iRpp = Integer.parseInt(oRequest.getParameter("rpp"));
			Integer iPage = Integer.parseInt(oRequest.getParameter("page"));
                        HashMap<String, String> hmOrder = ParameterCook.getOrderParams(oRequest.getParameter("order"));
                        Integer idUsu = Integer.parseInt(oRequest.getParameter("idUsu"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			FacturaDao oFacturaDao = new FacturaDao(oConnection, ob);
			ArrayList<FacturaBean> alFacturaBean = oFacturaDao.getpageXusuario(iRpp, iPage,hmOrder,1,idUsu);
			Gson oGson = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
			oReplyBean = new ReplyBean(200, oGson.toJson(alFacturaBean));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500,
					"ERROR: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
        
        
}
