package online_shop.service.Impl;

import config.Validation;
import online_shop.Dao.UserDao;
import online_shop.database.DateBese;

import online_shop.models.User;
import online_shop.service.UserSevice;

public class UserServiceImpl implements UserSevice {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String signUp(User user) {
        if (!Validation.checkEmail(user.getEmail())) {
            throw new RuntimeException("Invalitd email");
        } else {
            User[] users = userDao.fintAll();
            for (User u : users) {
                if (u.getEmail().equals(user.getEmail())) {
                    throw new IllegalArgumentException("mydai Email bar !");
                }
            }
        }
        if (!Validation.checkPasswort(user.getPassword())) {
            throw new RuntimeException("invalid");
        }
        userDao.save(user);
        return "successfully";
    }

    @Override
    public User[] finAll() {

        return DateBese.users;
    }

    @Override
    public User sigIn(String email, String password) {

        for (User user : finAll()) {
            if (user.getEmail().equals(email)){
                if (user.getPassword().equals(password)) {


                    return user;
                }
            }

        }
        throw new RuntimeException("user not fount");

    }
}
