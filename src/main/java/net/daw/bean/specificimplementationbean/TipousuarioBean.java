/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.specificimplementationbean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import net.daw.bean.genericimplementationbean.BeanGeneric;
import net.daw.bean.publicinterfacebean.BeanInterface;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author a004631408j
 */
public class TipousuarioBean extends BeanGeneric implements BeanInterface {
    
    @Expose
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public TipousuarioBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        this.setId(oResultSet.getInt("id"));
        this.setDesc(oResultSet.getString("desc"));
        return this;
    }
    
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "tipousuario.id,";
        strColumns += "tipousuario.desc";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(desc);
        return strColumns;
    }

    @Override
    public String getPairs() {
        String strPairs = "";
        strPairs += "tipousuario.id=" + id + ",";
        strPairs += "tipousuario.desc=" + EncodingHelper.quotate(desc);
        strPairs += " WHERE tipousuario.id=" + id;
        return strPairs;
    }
}
