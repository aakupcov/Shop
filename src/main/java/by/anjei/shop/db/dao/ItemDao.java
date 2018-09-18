package by.anjei.shop.db.dao;



import by.anjei.shop.db.daomodel.Item;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public interface ItemDao {
    public void setDataSource(DataSource dataSource);
    List<Item> getAllItems();
    Item getItemByName(String itemName);
    void saveItem(Item item);
    void deleteItem(Item item);
}
