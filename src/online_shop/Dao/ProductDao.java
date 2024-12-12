package online_shop.Dao;

import online_shop.models.Product;

public interface ProductDao {
    void addProduct(Product product);
 Product[] getAll();
}
