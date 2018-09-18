package by.anjei.shop.db.service;

import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 23.05.18
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Order> allUserOrders(User user) {
        return productRepository.allUserOrders(user);
    }

    public List<Item> allAvailableItems() {
        return productRepository.allAvailableItems();
    }

    public User userByLogin(String login) {
        return productRepository.userByLogin(login);
    }

    public void saveUser(User user) {
        productRepository.saveUser(user);
    }

    public List<Item> orderedItemsByOrderId(Order order) {
        return productRepository.orderedItemsByOrderId(order);
    }

    public List<Item> orderedItems(User user) {
        return productRepository.orderedItems(user);
    }

    public void saveOrder(Order order) {
        productRepository.saveOrder(order);
    }

    public Order lastUserOrder(User user) {
        return productRepository.lastUserOrder(user);
    }

    public void saveOrderedItems(List<Item> items, Order order) {
        productRepository.saveOrderedItems(items, order);
    }
}
