package by.anjei.shop.db.dao;

import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public interface OrderDao {
    public void setDataSource(DataSource dataSource);
    List<Order> getAllUserOrders(User user);
    Order getLastUserOrder(User user);
    void saveOrder(Order order);
    void deleteOrder(Order order);
}
