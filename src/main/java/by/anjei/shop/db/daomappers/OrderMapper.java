package by.anjei.shop.db.daomappers;

import by.anjei.shop.db.daomodel.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 09.05.18
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setTotalPrice(rs.getDouble("total_price"));
        return order;
    }
}
