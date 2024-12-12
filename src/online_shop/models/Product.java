package online_shop.models;

import online_shop.enoms.Categori;
import online_shop.enoms.Size;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;

public class Product {
    private long id;
    private Categori categori;

    private String name;
    private BigDecimal price;
    private Size[] size;
    private String color;
    private String imageUrl;

    private static long generateId = 0;

    public Product() {
        this.id = generateId++;

    }

    public Product(Categori categori,String name, BigDecimal price, Size[] size, String color, String imageUrl) {
        this.id = generateId++;
        this.categori = categori;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Size[] getSize() {
        return size;
    }

    public void setSize(Size[] size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categori getCategori() {
        return categori;
    }

    public void setCategori(Categori categori) {
        this.categori = categori;
    }

    @Override
    public String toString() {
        return "Product{" +
               "id = " +id +'/' +
               "categori = " + categori +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", size=" + Arrays.toString(size) +
               ", color='" + color + '\'' +
               ", imageUrl='" + imageUrl + '\'' +"\n"+
               '}';
    }
}
