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
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.dao.specificimplementationdao.implementationdao_0.FacturaDao_0;
import net.daw.dao.specificimplementationdao.implementationdao_1.FacturaDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_2.FacturaDao_2;
import net.daw.factory.DaoFactory;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author a004631408j
 */
public class UsuarioBean extends BeanGeneric implements BeanInterface {

    @Expose
    private String dni;

    @Expose
    private String nombre;

    @Expose
    private String ape1;

    @Expose
    private String ape2;

    @Expose
    private String login;

    @Expose(serialize = false)
    private String pass;

    @Expose(deserialize = false)
    private String pass2client = "";

    @Expose(serialize = false)
    private int id_tipoUsuario;

    @Expose(deserialize = false)
    private TipousuarioBean obj_tipoUsuario;

    @Expose
    private int numFactura;

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public TipousuarioBean getObj_tipoUsuario() {
        return obj_tipoUsuario;
    }

    public void setObj_tipoUsuario(TipousuarioBean obj_tipoUsuario) {
        this.obj_tipoUsuario = obj_tipoUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPass2client() {
        return pass2client;
    }

    public void setPass2client(String pass2client) {
        this.pass2client = pass2client;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId_tipoUsuario() {
        return id_tipoUsuario;
    }

    public void setId_tipoUsuario(int id_tipoUsuario) {
        this.id_tipoUsuario = id_tipoUsuario;
    }

    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection oConnection, Integer expand, UsuarioBean oUsuarioBeanSession) throws Exception {

        this.setId(oResultSet.getInt("id"));
        this.setDni(oResultSet.getString("dni"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setApe1(oResultSet.getString("ape1"));
        this.setApe2(oResultSet.getString("ape2"));
        this.setLogin(oResultSet.getString("login"));
        this.setPass(oResultSet.getString("pass"));
        DaoInterface oFacturaDao = DaoFactory.getDao(oConnection, "factura", oUsuarioBeanSession);
        if (oFacturaDao != null) {
            if (oFacturaDao.getClass() == FacturaDao_1.class) {
                FacturaDao_1 oFacturaDao_1 = (FacturaDao_1) oFacturaDao;
                this.setNumFactura(oFacturaDao_1.getcountXusuario(this.id));
            } else if (oFacturaDao.getClass() == FacturaDao_2.class) {
                FacturaDao_2 oFacturaDao_2 = (FacturaDao_2) oFacturaDao;
                this.setNumFactura(oFacturaDao_2.getcountXusuario(this.id));
            } else {
                FacturaDao_0 oFacturaDao_0 = (FacturaDao_0) oFacturaDao;
                this.setNumFactura(oFacturaDao_0.getcountXusuario(this.id));
            }
        }
        if (expand > 0) {
            DaoInterface otipousuarioDao = DaoFactory.getDao(oConnection, "tipousuario", oUsuarioBeanSession);
            this.setObj_tipoUsuario((TipousuarioBean) otipousuarioDao.get(oResultSet.getInt("id_tipoUsuario"), expand - 1));
        } else {
            this.setId_tipoUsuario(oResultSet.getInt("id_tipoUsuario"));
        }
        return this;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "dni,";
        strColumns += "nombre,";
        strColumns += "ape1,";
        strColumns += "ape2,";
        strColumns += "login,";
        strColumns += "pass,";
        strColumns += "id_tipoUsuario";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(dni) + ",";
        strColumns += EncodingHelper.quotate(nombre) + ",";
        strColumns += EncodingHelper.quotate(ape1) + ",";
        strColumns += EncodingHelper.quotate(ape2) + ",";
        strColumns += EncodingHelper.quotate(login) + ",";
        strColumns += EncodingHelper.quotate(pass) + ",";
        strColumns += id_tipoUsuario;
        return strColumns;
    }

    @Override
    public String getPairs() {
        String strPairs = "";
        strPairs += "dni =" + EncodingHelper.quotate(dni) + ",";
        strPairs += "nombre =" + EncodingHelper.quotate(nombre) + ",";
        strPairs += "ape1 =" + EncodingHelper.quotate(ape1) + ",";
        strPairs += "ape2 =" + EncodingHelper.quotate(ape2) + ",";
        strPairs += "login =" + EncodingHelper.quotate(login) + ",";
        if (pass != null && !pass.equals("")) {
            strPairs += "pass =" + EncodingHelper.quotate(pass) + ",";
        }
        strPairs += "id_tipoUsuario =" + id_tipoUsuario;
        strPairs += " WHERE id=" + id;
        return strPairs;

    }
}
