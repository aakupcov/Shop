package by.anjei.shop.db.daomappers;

import by.anjei.shop.db.daomodel.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 09.05.18
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setItemName(rs.getString("item_name"));
        item.setPrice(rs.getDouble("item_price"));
        return item;
    }
}
