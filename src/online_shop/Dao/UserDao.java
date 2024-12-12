package online_shop.Dao;

import online_shop.models.User;

public interface UserDao {

    void save(User user);

      User[] fintAll();

}
