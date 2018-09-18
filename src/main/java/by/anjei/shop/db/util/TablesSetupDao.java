package by.anjei.shop.db.util;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 10.05.18
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public interface TablesSetupDao {
    public void setDataSource(DataSource dataSource);
    public void createDefaultTables();
}
