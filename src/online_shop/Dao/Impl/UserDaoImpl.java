package online_shop.Dao.Impl;

import online_shop.Dao.UserDao;
import online_shop.database.DateBese;
import online_shop.models.User;

import java.util.Arrays;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        DateBese.users = Arrays.copyOf(DateBese.users,DateBese.users.length +1);
        DateBese.users[DateBese.users.length-1]=user;

    }

    @Override
    public User[] fintAll() {
        return DateBese.users;
    }
}
