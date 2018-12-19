
package net.daw.bean.publicinterfacebean;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author a004631408j
 */
public interface BeanInterface {
    public int getId();

    public void setId(int id);

    public BeanInterface fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception;

    public String getColumns();

    public String getValues();

    public String getPairs();
}
