package by.anjei.shop.db.daomappers;

import by.anjei.shop.db.daomodel.Item;
import by.anjei.shop.db.daomodel.OrderedItem;
import org.springframework.jdbc.core.*;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 09.05.18
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class OrderedItemMapper implements RowMapper<Item> {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public OrderedItemMapper(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer itemId = new Integer(rs.getInt("item_id"));
        String query = "select * from items where item_id = ?";
        Item item = jdbcTemplateObject.queryForObject(query, new ItemMapper(), itemId);
        return item;
    }
}
