package org.main;

import org.models.Category;
import org.models.Item;
import org.models.Product;
import org.models.Sale;
import org.service.impl.ItemImpl;
import org.service.impl.ProductImpl;
import org.service.impl.SaleImpl;
import org.service.inter.ItemInter;
import org.service.inter.ProductInter;
import org.service.inter.SaleInter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Operations {
    Scanner scanner =new Scanner(System.in);
    public void deleteCase(ProductInter productInter ){
        System.out.println("enter id");
        Long deleteProduct = scanner.nextLong();
        productInter.deleteProduct(deleteProduct);
    }
    public void maxMinvalueCase(ProductInter productInter){
        System.out.println("enter min and max price");
        for (Product p : productInter.showProductsByPrice(scanner.nextDouble(), scanner.nextDouble())) {
            System.out.println(p.toString());
        }
    }
    public  void showProductByCategory(ProductInter productInter){
        System.out.println("enter category d e w");
        for (Product p : productInter.showProductsByCategory(scanner.next())) {
            System.out.println(p.toString());
        }
    }
    public void showProductByName(ProductInter productInter){
        System.out.println("enter product name");
        for (Product p : productInter.showProductsByName(scanner.next())) {
            System.out.println(p.toString());
        }
    }
    public void useList(){


        ProductImpl productImpl = new ProductImpl();
        Product prod = new Product(1L, "aa11", "aa11", Category.Wearable, 1d, 111);
        Product prod1 = new Product(2l, "ab11", "ab11", Category.Eatable, 2d, 222);
        Product prod2 = new Product(3l, "bb11", "bb11", Category.Drinks, 3d, 333);
        Product prod3 = new Product(4l, "ba11", "ba11", Category.Eatable, 4d, 444);
        Product prod4 = new Product(5l, "bc11", "bc11", Category.Wearable, 5d, 555);
        ProductImpl.productList.add(prod);
        ProductImpl.productList.add(prod1);
        ProductImpl.productList.add(prod2);
        ProductImpl.productList.add(prod3);
        ProductImpl.productList.add(prod4);
        SaleImpl saleImpl = new SaleImpl();
    }
public void updateChooseCase(ProductInter productInter ){


    boolean updateChoice = true;
    while (updateChoice) {
        System.out.println("select update case 1-name, 2-category, 3-price, 4-amount");
        int alter = scanner.nextInt();
        if (alter == 1) {
            System.out.println("enter id and update name");
            productInter.updateProductName(scanner.nextLong(), scanner.next());
        } else if (alter == 2) {
            System.out.println("enter id and update category(e w d)");
            productInter.updateProductCategory(scanner.nextLong(), scanner.next());
        } else if (alter == 3) {
            System.out.println("enter id and update price");
            productInter.updateProductPrice(scanner.nextLong(), scanner.nextDouble());
        } else if (alter == 4) {
            System.out.println("enter id and update amount");
            productInter.updateProductAmount(scanner.nextLong(), scanner.nextInt());
        } else System.out.println("invalid choice");
        System.out.println("continue update 0-no, 1-yes");
        int c = scanner.nextInt();
        if (c == 0) {
            updateChoice = false;
        } else if (c == 1) {
            updateChoice = true;
        } else System.out.println("invalid operation");

    }
}

    public Product addProduct(){
        Scanner scanner =new Scanner(System.in);
        Product product = new Product();
        System.out.println("name");

        product.setName(scanner.next());
        boolean b= true;
        while (b) {
            System.out.println("enter barcode 4 character");
            String barcode = scanner.next();
            if (barcode.matches("^.{4}$")) {
                product.setBarcode(barcode);
                b = false;
            } else System.out.println("invalid barcode");
        }
        System.out.println("enter  :d,e,w ");
        String s=scanner.next();
        if(s.equals("d"))
            product.setCategory(Category.Drinks);
        else if (s.equals("e")) {
            product.setCategory(Category.Eatable);
        }else if (s.equals("w")) {
            product.setCategory(Category.Wearable);
        }
        System.out.println("price, amount");
        product.setPrice(scanner.nextDouble());
        product.setAmount(scanner.nextInt());
        return product;
    }

    public List<Item> addSale(ItemInter itemInter,ProductInter productInter){

        Scanner scanner = new Scanner(System.in);

        boolean item=true;
        List<Item> items=new ArrayList<>();

        while (item){
            Item item1 = new Item();
            System.out.println("add item  barcode (only 4 char)");
            String t=scanner.next();
            item1.setBarcode(t);

            try{
                Product p =productInter.getProductByBarcode(item1.getBarcode());

                System.out.println(p.toString());
            }catch (NoSuchElementException noSuchElementException){
                System.out.println("no such product");
                noSuchElementException.getMessage();
            }
            System.out.println(" How many");
            int c1= scanner.nextInt();
            item1.setCount(c1);
            System.out.println("want add new item? 1-yes, 0-no");
            item1.setTotal(itemInter.itemTotal(t,c1));
            items.add(item1);
            int c = scanner.nextInt();
            if (c==1){
                item=true;
            }
            else if(c==0){
                item=false;
            }
            else System.out.println("invalid operation");
        }

        return items;
    }
public void removeItem(){
        SaleImpl saleImpl = new SaleImpl();
        Scanner sc=new Scanner(System.in) ;
    System.out.println("enter sale id and barcode of product to remove");
    saleImpl.removeItem(sc.nextLong(),sc.next());
}
public void showProduct(ProductInter productInter){
    for (Product p : productInter.showProducts()) {
        System.out.println(p.toString());
    }
}

 public LocalDateTime convertDatetoLocalDateTime(){
        Scanner scanner = new Scanner(System.in);
     System.out.println("enter start date  (yyyy-MM-dd):");
     String input = scanner.next();
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     LocalDate date = LocalDate.parse(input, dateFormatter);
     LocalTime time = LocalTime.of(0, 0); // Assuming the time as midnight (00:00)
     LocalDateTime dateTime1 = LocalDateTime.of(date, time);
        return dateTime1;
 }
 public void showSale(SaleInter saleInter){
     for (Sale s : saleInter.showSale()) {
         System.out.println(s.toString());
     }
 }
 public void removeSaleItem(SaleInter saleInter){
     System.out.println("enter sale id and barcode of product to remove");
     saleInter.removeItem(scanner.nextLong(), scanner.next());
 }
 public  void removeSale(SaleInter  saleInter){
     System.out.println("enter sale id to remove");
     saleInter.removeSale(scanner.nextLong());
 }
public  void showSaleBetweenDate(SaleInter saleInter){
    for (Sale s :saleInter.showSaleByDuration(convertDatetoLocalDateTime(), convertDatetoLocalDateTime())) {
        System.out.println(s.toString());
    }
}
public void showSaleBetweenPrice(SaleInter saleInter){
    System.out.println("enter min and max price ");
    Double minPrice = scanner.nextDouble();
    Double maxPrice = scanner.nextDouble();
    for (Sale s : saleInter.showSaleByPrice(minPrice, maxPrice)) {
        System.out.println(s.toString());
    }
}
public void showProductByDate(SaleInter saleInter){
    System.out.println("enter date   (yyyy-MM-dd):");
    String input = scanner.next();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(input, dateFormatter);
    for (Sale s : saleInter.showSaleByDate(date)) {
        System.out.println(s.toString());
    }
}
public void getSaleById(SaleInter saleInter){
    System.out.println("enter id for search sale");
    Long id = scanner.nextLong();
    System.out.println(saleInter.showSaleById(id).toString());

}


}
