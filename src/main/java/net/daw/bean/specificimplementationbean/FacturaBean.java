
package net.daw.bean.specificimplementationbean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import net.daw.bean.genericimplementationbean.BeanGeneric;
import net.daw.bean.publicinterfacebean.BeanInterface;
import net.daw.dao.publicinterfacedao.DaoInterface;
import net.daw.dao.specificimplementationdao.implementationdao_1.LineaDao_1;
import net.daw.dao.specificimplementationdao.implementationdao_2.LineaDao_2;
import net.daw.factory.DaoFactory;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author a004631408j
 */
public class FacturaBean extends BeanGeneric implements BeanInterface{
    @Expose
    private Date fecha;
    @Expose
    private Float iva;

    @Expose(serialize = false)
    private int id_usuario;

    @Expose(deserialize = false)
    private UsuarioBean obj_Usuario;

    @Expose
    private int numLinea;

    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
    }

    public UsuarioBean getObj_Usuario() {
        return obj_Usuario;
    }

    public void setObj_Usuario(UsuarioBean obj_Usuario) {
        this.obj_Usuario = obj_Usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    @Override
    public FacturaBean fill(ResultSet oResultSet, Connection oConnection, Integer expand, UsuarioBean oUsuarioBeanSession) throws Exception {

        this.setId(oResultSet.getInt("id"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setIva(oResultSet.getFloat("iva"));
        DaoInterface oLineaDao = DaoFactory.getDao(oConnection, "linea", oUsuarioBeanSession);
        if (oLineaDao.getClass() == LineaDao_1.class) {
            LineaDao_1 oLineaDao_1 = (LineaDao_1) oLineaDao;
            this.setNumLinea(oLineaDao_1.getcountXfactura(this.id));
        } else {
            LineaDao_2 oLineaDao_2 = (LineaDao_2) oLineaDao;
            this.setNumLinea(oLineaDao_2.getcountXfactura(this.id));
        }
        if (expand > 0) {
            DaoInterface oUsuarioDao = DaoFactory.getDao(oConnection, "usuario", oUsuarioBeanSession);
            this.setObj_Usuario((UsuarioBean) oUsuarioDao.get(oResultSet.getInt("id_usuario"), expand - 1));
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        return this;
    }
    
    @Override
    public String getPairs() {

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Instant instant = fecha.toInstant();

        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        System.out.println("Local Date is: " + localDate);

        String strPairs = "";
        strPairs += "id=" + id + ",";
        strPairs += "fecha=" + EncodingHelper.quotate(localDate.toString()) + ",";
        strPairs += "iva=" + iva + ",";
        strPairs += "id_usuario=" + getId_usuario();
        strPairs += " WHERE id=" + id;
        return strPairs;

    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fecha,";
        strColumns += "iva,";
        strColumns += "id_usuario";
        return strColumns;
    }

    @Override
    public String getValues() {

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Instant instant = fecha.toInstant();

        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        System.out.println("Local Date is: " + localDate);
        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(localDate.toString()) + ",";
        strColumns += iva + ",";        
        if (getObj_Usuario() != null) {
            strColumns += this.getObj_Usuario().getId();
        } else {
            strColumns += this.getId_usuario();
        }
        return strColumns;
    }

}
