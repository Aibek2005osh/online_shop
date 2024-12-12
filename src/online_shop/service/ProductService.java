package online_shop.service;

import online_shop.models.Product;

public interface ProductService {
    void addProduct(Product product);
      Product [] getAll  ();
}
