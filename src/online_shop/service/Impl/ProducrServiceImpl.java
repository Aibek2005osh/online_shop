package online_shop.service.Impl;

import online_shop.Dao.ProductDao;
import online_shop.database.DateBese;
import online_shop.models.Product;
import online_shop.service.ProductService;

import java.util.Arrays;

public class ProducrServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProducrServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(Product product) {



        DateBese.products = Arrays.copyOf(DateBese.products, DateBese.products.length + 1);
        DateBese.products[DateBese.products.length - 1] = product;
    }

    @Override
    public Product[] getAll() {

     return DateBese.products;
    }


}
