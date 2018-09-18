package by.anjei.shop.db.daoimplementation;

import by.anjei.shop.db.dao.UserDao;
import by.anjei.shop.db.daomappers.UserMapper;
import by.anjei.shop.db.daomodel.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 16.04.18
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        String query = "select * from users order by user_id";
        List<User> users = jdbcTemplateObject.query(query, new UserMapper());
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        String query = "select * from users where user_login = ?";
        List<User> users = jdbcTemplateObject.query(query, new Object[]{login}, new UserMapper());
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        String query = "insert into users (user_login, user_password) values (?, ?)";
        jdbcTemplateObject.update(query, user.getLogin(), user.getPassword());
    }

    @Override
    public void deleteUser(User user) {
        String query = "delete from users where user_login = ?";
        jdbcTemplateObject.update(query, user.getLogin());
    }
}
