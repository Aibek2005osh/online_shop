package online_shop.service;

import online_shop.models.User;

public interface UserSevice {
    String signUp(User user);

      User[] finAll();

    User sigIn(String email, String password);
}
