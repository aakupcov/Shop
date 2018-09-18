package by.anjei.shop.db.service;

import by.anjei.shop.db.daoimplementation.ItemDaoImpl;
import by.anjei.shop.db.daoimplementation.OrderDaoImpl;
import by.anjei.shop.db.daoimplementation.OrderedItemDaoImpl;
import by.anjei.shop.db.daoimplementation.UserDaoImpl;
import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 23.05.18
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class ProductRepository {
    private ApplicationContext context;
    private UserDaoImpl userDao;
    private OrderDaoImpl orderDao;
    private OrderedItemDaoImpl orderedItemDao;
    private ItemDaoImpl itemDao;

    public ProductRepository() {
        context = new ClassPathXmlApplicationContext("SpringConfig.xml");
        userDao = (UserDaoImpl) context.getBean("userDaoImpl");
        orderDao = (OrderDaoImpl) context.getBean("orderDaoImpl");
        orderedItemDao = (OrderedItemDaoImpl) context.getBean("orderedItemDaoImpl");
        itemDao = (ItemDaoImpl) context.getBean("itemDaoImpl");
    }

    public List<Order> allUserOrders(User user) {
        return orderDao.getAllUserOrders(user);
    }

    public List<Item> allAvailableItems() {
        return itemDao.getAllItems();
    }

    public User userByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public List<Item> orderedItemsByOrderId(Order order) {
        return orderedItemDao.getItemsByOrderId(order);
    }

    public List<Item> orderedItems(User user) {
        List<Order> userOrders = allUserOrders(user);
        List<Item> orderedItems = new ArrayList<>();
        for (Order order : userOrders) {
            orderedItems.addAll(orderedItemsByOrderId(order));
        }
        return orderedItems;
    }

    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }

    public Order lastUserOrder(User user) {
        return orderDao.getLastUserOrder(user);
    }

    public void saveOrderedItems(List<Item> items, Order order) {
        orderedItemDao.saveItems(items, order);
    }
}
