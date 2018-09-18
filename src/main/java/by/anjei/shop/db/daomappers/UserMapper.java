package by.anjei.shop.db.daomappers;

import by.anjei.shop.db.daomodel.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: xxxx
 * Date: 10.05.18
 * Time: 7:48
 * To change this template use File | Settings | File Templates.
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setLogin(rs.getString("user_login"));
            user.setPassword(rs.getString("user_password"));
            return user;
    }
}
