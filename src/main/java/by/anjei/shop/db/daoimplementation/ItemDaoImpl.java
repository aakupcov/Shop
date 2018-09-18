package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.ItemDao;
import by.anjei.shop.db.daomappers.ItemMapper;
import by.anjei.shop.db.daomodel.Item;
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
public class ItemDaoImpl implements ItemDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Item> getAllItems() {
        String query = "select * from items order by item_id";
        List<Item> items = jdbcTemplateObject.query(query, new ItemMapper());
        return items;
    }

    @Override
    public Item getItemByName(String name) {
        String query = "select * from items where item_name = ?";
        Item item = jdbcTemplateObject.queryForObject(query, new Object[]{name}, new ItemMapper());
        return item;
    }

    @Override
    public void saveItem(Item item) {
        String query = "insert into items (item_name, item_price) values (?, ?)";
        jdbcTemplateObject.update(query, item.getItemName(), item.getPrice());
    }

    @Override
    public void deleteItem(Item item) {
        String query = "delete from items where item_name = ?";
        jdbcTemplateObject.update(query, item.getItemName());
    }
}
