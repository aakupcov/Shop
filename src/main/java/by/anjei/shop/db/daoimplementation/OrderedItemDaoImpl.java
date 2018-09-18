package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.OrderedItemDao;
import by.anjei.shop.db.daomappers.OrderedItemMapper;
import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.OrderedItem;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 21.04.18
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class OrderedItemDaoImpl implements OrderedItemDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Item>  getItemsByOrderId(Order order) {
        String query = "select item_id from ordered_items where order_id = ?";
        List<Item> items = jdbcTemplateObject.query(query, new OrderedItemMapper(dataSource), order.getOrderId());
        return items;
    }

    @Override
    public void saveItem(Integer orderId, Integer itemId) {
        String query = "insert into ordered_items (order_id, item_id) values (?, ?)";
        jdbcTemplateObject.update(query, orderId, itemId);
    }

    @Override
    public void saveItems(List<Item> currentItems, Order order) {
        for (Item item : currentItems) {
            saveItem(order.getOrderId(), item.getId());
        }
    }


    @Override
    public void deleteItem(OrderedItem orderedItem) {
        String query = "delete from ordered_items where ordered_item_id = ?";
        jdbcTemplateObject.update(query, orderedItem.getId());
    }
}
