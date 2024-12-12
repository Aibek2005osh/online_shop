package online_shop;

import config.Validation;
import online_shop.Dao.Impl.ProductDaoImpl;
import online_shop.Dao.Impl.UserDaoImpl;
import online_shop.Dao.UserDao;
import online_shop.database.DateBese;
import online_shop.enoms.Categori;
import online_shop.enoms.Role;
import online_shop.enoms.Size;
import online_shop.models.Product;
import online_shop.models.User;
import online_shop.service.Impl.ProducrServiceImpl;
import online_shop.service.Impl.UserServiceImpl;
import online_shop.service.ProductService;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static User admin = new User("Aibek@gmail.com", "Aibek123!", "Aibek", Role.ADMIN);

    public static void main(String[] args) {
        ProductDaoImpl productDao = new ProductDaoImpl();
        UserDao userDao = new UserDaoImpl();
        ProductService productService = new ProducrServiceImpl(productDao);
        UserServiceImpl userService = new UserServiceImpl(userDao);

        User current = null;
        while (true) {
            System.out.println("""
                    Press t0 0. exit
                    Press to 1. sing up
                    Press to 2. sign-ip
                    Press ro 3. getAll users
                    
                    """);

            switch (checkVaildCommand()) {
                case 0 -> {
                    return;
                }

                case 1 -> {
                    System.out.println("             REGISTRATION ");

                    String validEmail = isValidData(" email : ", Validation.emailPattern());

                    String validPass = isValidData(" password : ", Validation.passworPattern());


                    System.out.print("enter name : ");
                    User user = new User(validEmail, validPass, scannerStr.nextLine(), Role.CLIEMT);
                    String res = userService.signUp(user);
                    System.out.println(res);
                }
                case 2 -> {
                    User user = new User();

                    System.out.println("Sign - in");
                    String validEmail = isValidData(" email : ", Validation.emailPattern());
                    String validPass = isValidData(" password : ", Validation.passworPattern());

                    for (User dt : DateBese.users) {
                        if (dt.getEmail().equals(validEmail)) {
                            if (dt.getPassword().equals(validPass)) {
                                loginClient();
                            }
                        }
                    }

                    if (validEmail.equals(admin.getEmail()) && validPass.equals(admin.getPassword())) {
                        adminChas();
                    }

                    try {
                        current = userService.sigIn(validEmail, validPass);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }


                }
                case 3 -> {
                    System.out.println("All users : ");
                    for (User user : userService.finAll()) {
                        System.out.println(user);
                    }

                }
            }

        }

    }

    public static String isValidData(String label, String pattern) {
        System.out.printf("enter the %s :", label);
        String data;

        do {
            data = new Scanner(System.in).nextLine();
            if (!data.matches(pattern)) {
                System.out.printf("INVAILD  %s, ENTER VAILD %s : ", label, label);
            }

        }
        while (!data.matches(pattern));
        return data;
    }

    public static int checkVaildCommand() {
        int command = 0;
        boolean invalidComand;
        System.out.print("write the comand : ");

        do {
            try {
                command = new Scanner(System.in).nextInt();
                invalidComand = false;
            } catch (InputMismatchException e) {
                invalidComand = true;
                System.err.print("INVALID COMAAND, ENTER VALID COMAAND : ");

            }
        } while (invalidComand);
        return command;
    }

    static ProductService productService = new ProducrServiceImpl(new ProductDaoImpl());

    public static void adminChas() {
        Product product = new Product();


        while (true) {
            System.out.println("                        Админ");
            System.out.println("1.               Продукттарды кошуу");
            System.out.println("2.            Бардык продукттарды көрсөтүү");
            System.out.println("3.                       өчүрүү");
            System.out.println("4                  Продуктты ozgortyy ");
            System.out.println("0.                       Чыгуу");
            System.out.print("comand : ");

            String comand = scannerStr.nextLine();
            switch (comand) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    addProduct(productService);
                }
                case "2" -> {
                    Product[] products = DateBese.products;
                    System.out.println(Arrays.toString(products));

                }
                case "3" -> {
                    System.out.print("write id :");
                    int num = scannerInt.nextInt();
                    delateProduct(num);
                }
                case "4" -> {

                    System.out.print("write id : ");
                    long productid = scannerInt.nextLong();
                    String res = upDateProduct(productid, productUpdete());
                    System.out.println(res);
                }

            }

            System.err.println(" i'am Admin");
        }
    }

    public static void addProduct(ProductService productService) {
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();

        Categori category = category();
        product.setCategori(category);

        product.setName(productName());


        System.out.print("Продукттын баасын киргизиңиз : ");

        BigDecimal price = getValidPrice(scannerInt);
        product.setPrice(price);


        System.out.println("Прадукттын размерин киргизиңиз");
        product.setSize(getSize());

        System.out.print("Прадукттын color киргизиңиз : ");
        product.setColor(scanner.nextLine());

        System.out.print("URL киргизиңиз : ");
        product.setImageUrl(scannerStr.nextLine());


        productService.addProduct(product);
        System.out.println("Продукт ийгиликтүү кошулду!");

    }

    public static Size[] getSize() {
        Size[] sizes = new Size[0];
        Size[] defSize = Size.values();
        boolean loop = true;
        for (int i = 0; i < defSize.length; i++) {
            System.out.println((i + 1) + ". " + defSize[i]);
        }
        System.out.println((defSize.length + 1) + ". Done");
        while (loop) {
            System.out.print("Choice the size (one or more):");
            String text = new Scanner(System.in).nextLine();
            if (text.matches("\\d+")) {
                if (Integer.valueOf(text) >= (defSize.length + 1)) {
                    loop = false;
                    break;
                } else {
                    sizes = Arrays.copyOf(sizes, sizes.length + 1);
                    sizes[sizes.length - 1] = defSize[Integer.valueOf(text) - 1];//defSize[1]
                }
            } else {
                if (text.equalsIgnoreCase("done")) {
                    loop = false;
                    break;
                } else {
                    for (Size size : defSize) {
                        if (text.equalsIgnoreCase(size.toString())) {
                            sizes = Arrays.copyOf(sizes, sizes.length + 1);
                            sizes[sizes.length - 1] = size;
                        }
                    }
                }
            }

        }
        return sizes;
    }

    public static BigDecimal getValidPrice(Scanner scanner) {
        BigDecimal price = null;
        boolean isValid = false;

        while (!isValid) {
            try {
                String input = scanner.nextLine();
                price = new BigDecimal(input);
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.print("error 0don jogory ber : ");
                } else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {

            }
        }
        return price;
    }

    public static void delateProduct(long id) {
        boolean isblok = false;
        for (int i = 0; i < DateBese.products.length; i++) {
            if (DateBese.products[i].getId().equals(id)) {
                isblok = true;
                for (int j = i; j < DateBese.products.length - 1; j++) {
                    DateBese.products[j] = DateBese.products[j + 1];

                }
            }
        }
        DateBese.products = Arrays.copyOf(DateBese.products, DateBese.products.length - 1);
        if (!isblok) {
            System.out.println("pruduct not fount" + id);
        }

    }

    public static String productName() {
        String name = null;
        boolean invalid;

        System.out.print("Продукттун атын киргизиңиз: ");

        do {
            try {
                name = new Scanner(System.in).nextLine();


                if (name == null || name.equalsIgnoreCase("")) {
                    System.err.print("ERROR : NAME BOSH BOLBOSH KEREK KAIRA JAZ : ");
                    invalid = true;
                } else {
                    invalid = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ката: Туура эмес киргизүү.");
                invalid = true;
            }
        } while (invalid);

        return name;
    }

    public static void loginClient() {
        System.out.println("hello 🥰 welcome to online-shop ");
        while (true) {
            System.out.println("""
                    Press to 0 : exit
                    Press to 1 : getall Pucduct
                    Press to 2 : getAll Pruduct Catygori | saze
                    """);
            switch (checkVaildCommand()) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    Product[] products = DateBese.products;
                    System.out.println(Arrays.toString(products));
                }
                case 2 ->{
                    Product product =new Product();

                    catigorySaze(category(),getSize());


                }
            }

            Product[] products = DateBese.products;
            System.out.println(Arrays.toString(products));
        }
    }

    public static String upDateProduct(long id, Product newProduct) {
        boolean isblok = false;

        for (Product product : DateBese.products) {
            if (product.getId() == id) {
                product.setName(productName());
                product.setPrice(newProduct.getPrice());
                product.setSize(newProduct.getSize());
                product.setColor(newProduct.getColor());
                product.setImageUrl(newProduct.getImageUrl());
                isblok = true;
                break;

            }
        }
        if (isblok) {
            System.out.println("success update !");
        } else {
            return "puduct with id" + id + "not fount";
        }
return null;
    }

    public static Product productUpdete() {
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();

//        productName();

        System.out.print("Продукттын баасын киргизиңиз : ");
        BigDecimal price = getValidPrice(scannerInt);
        product.setPrice(price);


        System.out.println("Прадукттын размерин киргизиңиз");
        product.setSize(getSize());

        System.out.print("Прадукттын color киргизиңиз : ");
        product.setColor(scanner.nextLine());

        System.out.print("URL киргизиңиз : ");
        product.setImageUrl(scannerStr.nextLine());


        return product;
    }


    public static Categori category() {


        boolean isblok = true;
        do {
            System.out.print(" (man, woman, children): ");
            String category = scannerStr.nextLine();

            try {
                isblok = false;
                switch (category.toLowerCase()) {
                    case "man" -> {
                        return Categori.MAN;
                    }
                    case "woman" -> {
                        return Categori.WOMAN;
                    }
                    case "children" -> {
                        return Categori.CHILDREN;
                    }
                    default -> {
                        System.out.println("Туура эмес .кайра  тада : /man, woman же children |:");
                        isblok = true;
                        throw new RuntimeException("error");
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } while (isblok);
        return null;
    }

    public static void catigorySaze(Categori category, Size[] size) {

        for (Product product : DateBese.products) {
            if (product.getCategori().equals(category)) {
                if (product.getSize().equals(size)){
                    System.out.println(product);
                }


            }

        }
    }
}






