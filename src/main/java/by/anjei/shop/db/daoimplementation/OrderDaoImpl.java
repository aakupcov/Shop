package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.OrderDao;
import by.anjei.shop.db.daomappers.OrderMapper;
import by.anjei.shop.db.daomodel.Order;
import by.anjei.shop.db.daomodel.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class OrderDaoImpl implements OrderDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Order> getAllUserOrders(User user) {
        String query = "select * from orders where user_id = ? order by order_id";
        List<Order> orders = jdbcTemplateObject.query(query, new OrderMapper(), user.getId());
        return orders;
    }

    @Override
    public Order getLastUserOrder(User user) {
        String query = "select * from orders where order_id = (select max(order_id) from orders) and user_id = ?";
        Order order = jdbcTemplateObject.queryForObject(query, new Object[]{user.getId()}, new OrderMapper());
        return order;
    }

    @Override
    public void saveOrder(Order order) {
        String query = "insert into orders (user_id, total_price) values (?, ?)";
        jdbcTemplateObject.update(query, order.getUserId(), order.getTotalPrice());
    }

    @Override
    public void deleteOrder(Order order) {
        String query = "delete from orders where order_id = ?";
        jdbcTemplateObject.update(query, order.getOrderId());
    }
}
