package by.anjei.shop.db.dao;

import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.OrderedItem;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 17.04.18
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public interface OrderedItemDao {
    public void setDataSource(DataSource dataSource);
    List<Item> getItemsByOrderId(Order order);
    void saveItem(Integer orderId, Integer itemId);
    void saveItems(List<Item> orderedItems, Order order);
    void deleteItem(OrderedItem orderedItem);
}
