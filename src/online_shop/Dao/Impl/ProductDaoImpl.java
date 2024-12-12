package online_shop.Dao.Impl;

import online_shop.Dao.ProductDao;
import online_shop.database.DateBese;
import online_shop.models.Product;

import java.util.Arrays;

public class ProductDaoImpl implements ProductDao {


    @Override
    public void addProduct(Product product) {
        DateBese.products = Arrays.copyOf(DateBese.products,DateBese.products.length +1);
        DateBese.products[DateBese.products.length -1] = product;
    }

    @Override
    public Product[] getAll() {
        return DateBese.products;
    }

}
