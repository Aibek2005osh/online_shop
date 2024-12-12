package online_shop.models;


import online_shop.enoms.Role;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private Role role;
    private Product[] basket;

    private static long geneteId = 0;

    public User() {
        this.id = geneteId++;
    }

    public User(String email, String password, String name, Role role) {
        this.id = geneteId++;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Product[] getBasket() {
        return basket;
    }

    public void setBasket(Product[] basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", name='" + name + '\'' +
               ", role=" + role +
               '}';
    }
}
