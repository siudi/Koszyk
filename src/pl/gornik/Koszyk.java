package pl.gornik;

import pl.gornik.model.Book;
import pl.gornik.model.Disc;
import pl.gornik.model.Product;
import pl.gornik.model.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Koszyk {
    public static void main(String[] args) {


        List<Product> products = new ArrayList<>();
        initializeShop(products);
        List<Product> basket = new ArrayList<>();


        double shopMoney = 0;
        for(Product element: products){
            shopMoney = shopMoney + element.countPrice();
            element.displayProduct();
        }
        System.out.println("Wartość sklepu: " + (int)shopMoney + "zł");


        Scanner scanner = new Scanner(System.in);
        String name;
        int quantity;
        String choice = "T";
        boolean isFind = false;
        while(choice.equalsIgnoreCase("T") || choice.equalsIgnoreCase("Tak") ||
                choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("Y")){
            System.out.println("Podaj nazwe produktu ktory chcesz dodac do koszyka");
            name = scanner.nextLine();
            System.out.println("Podaj liczbe sztuk produktu");
            quantity = scanner.nextInt();
            scanner.nextLine();

            for(Product product : products){
                if(product.getTitle().equalsIgnoreCase(name)){
                    Product prod = new Product(product);
                    if(product.getQuantity() > quantity){
                        prod.setQuantity(quantity);
                        basket.add(prod);
                        product.setQuantity(product.getQuantity() - quantity);
                        isFind = true;
                    }
                    else if (product.getQuantity() == quantity){
                        prod.setQuantity(quantity);
                        basket.add(prod);
                        isFind = true;
                        products.remove(product);
                        break;
                    }
                    else {
                        System.out.printf("Nie ma %d %s w sklepie. Do koszyka dodano %d \n", quantity, name, product.getQuantity());
                        basket.add(product);
                        isFind = true;
                        products.remove(product);
                        break;
                    }
                }

            }

            if(!isFind) System.out.println("Tego produktu w sklepie nie ma");


            System.out.println("Czy dodac kolejny produkt? Wpisz Tak/T/Yes/Y jeśli tak");
            choice = scanner.nextLine();

        }
        if(!isFind) System.out.println("Nie ma takiego produktu");
        isFind = false;

        double basketMoney = 0;
        System.out.println("Twój koszyk");
        for(Product prod : basket){
            prod.displayProduct();
            System.out.printf("\n");
            basketMoney += prod.countPrice();
        }
        System.out.println("\n-----------------------------------");
        System.out.printf("Wartośc koszyka to %.2f zł\n ", basketMoney);
        System.out.println("-----------------------------------");


        shopMoney = 0;
        System.out.println("Twój sklep: ");
        for(Product product : products){
            product.displayProduct();
            shopMoney += product.countPrice();
        }
        System.out.println("-----------------------------------");
        System.out.printf("Wartośc sklepu to %.2f zł\n ", shopMoney);
        System.out.println("-----------------------------------");






    }

    public static void initializeShop(List<Product> products){
        Book book1 = new Book("Pan Tadeusz",7, 50.0, "Adam Mickiewicz");
        Book book2 = new Book("Balladyna",4, 45.0, "Juliusz Słowacki");
        Book book3 = new Book("Quo Vadis",5, 48.0, "Henryk Sienkiewicz");
        Book book4 = new Book("Zemsta",10, 36.0, "Aleksander Fredro");

        Disc disc1 = new Disc("1967-1970 Blue Album",15,99.00,"The Beatles");
        Disc disc2 = new Disc("MTV Unplugged",9,39.99,"Lady Pank");
        Disc disc3 = new Disc("Heavy Horses",11,60.99,"Jethro Tull");
        Disc disc4 = new Disc("Naucz mnie tańczyc",23,49.99,"Kortez");

        Toy toy1 = new Toy("Pajacyk",15, 14.99, 9);
        Toy toy2 = new Toy("Lego",8, 59.99, 11);
        Toy toy3 = new Toy("Piłka",23, 9.99, 5);
        Toy toy4 = new Toy("Ukulele",15, 20.99, 7);

        products.add(book1);
        products.add(book2);
        products.add(book3);
        products.add(book4);

        products.add(disc1);
        products.add(disc2);
        products.add(disc3);
        products.add(disc4);

        products.add(toy1);
        products.add(toy2);
        products.add(toy3);
        products.add(toy4);
    }
}
