package org.service.impl;

import org.models.Category;
import org.models.Product;
import org.service.inter.ProductInter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductImpl implements ProductInter {
    public static List<Product> productList=new ArrayList<>();


    @Override
    public void addProduct(Product product) {
        productList.add(product);

    }

    @Override
    public void deleteProduct(Long id) {
        Product product=productList.stream().filter(product1 -> product1.getId()==id).findFirst().get();
//        Product product= new Product();
//        for (Product p:productList) {
//          if (p.getId()==id){
//              product=p;
//          }
//        }
        productList.remove(product);
        System.out.println(id+" product deleted");
    }

    @Override
    public void updateProductName(Long id, String name) {
        Product product = productList.stream().filter(product1 -> product1.getId()==id).findFirst().get();
//        Product product= new Product();
//        for (Product p:productList) {
//            if (p.getId()==id){
//                product=p;
//            }
//        }
        productList.remove(product);
        product.setName(name);
        productList.add(product);
    }

    @Override
    public void updateProductPrice(Long id, Double price) {

        Product product = productList.stream().filter(product1 -> product1.getId()==id).findFirst().get();
//        Product product = new Product();
//        for (Product p:productList) {
//            if (p.getId()==id){
//                product=p;
//
//            }
//        }
        productList.remove(product);
        product.setPrice(price);
        productList.add(product);
    }

    @Override
    public void updateProductAmount(Long id, int amount) {
        Product product = productList.stream().filter(product1 -> product1.getId()==id).findFirst().get();

//        Product product =new Product();
//        for (Product p:productList) {
//            if (p.getId()==id){
//                product=p;
//            }
//        }
        productList.remove(product);
        product.setAmount(amount);
        productList.add(product);
    }

    @Override
    public void updateProductCategory(Long id, String category) {
        Product product = productList.stream().filter(product1 -> product1.getId()==id).findFirst().get();

//        Product product = new Product();
//        for (Product p:productList) {
//            if (p.getId()==id){
//                product=p;
//            }
//        }
        productList.remove(product);
        if(category.equals("d"))
            product.setCategory(Category.Drinks);
        else if (category.equals("e")) {
            product.setCategory(Category.Eatable);
        }else if (category.equals("w")) {
            product.setCategory(Category.Wearable);
        }else {
            System.out.println("invalid value, category update failed, try again");
        }
        productList.add(product);
    }

    @Override
    public List<Product> showProducts() {
        return productList;
    }

    @Override
    public List<Product> showProductsByName(String name) {
        List<Product> list=productList.stream().filter(product -> product.getName().equals(name)).collect(Collectors.toList());
//        List<Product> list=new ArrayList<>();
//        for (Product p:productList) {
//            if (p.getName().equals(name)){
//                list.add(p);
//            }
//        }
        return list;
    }

    @Override
    public List<Product> showProductsByCategory(String category) {
        List<Product>  productList1= productList.stream().filter(product -> product.getCategory().getName().equals(category)).collect(Collectors.toList());
       return productList1;
    }

    @Override
    public List<Product> showProductsByPrice(Double minPrice, Double maxPrice) {
        List<Product> list1=productList.stream().filter(product -> product.getPrice()>=minPrice&&product.getPrice()<=maxPrice).collect(Collectors.toList());
//        List<Product> list1=new ArrayList<>();
//        for (Product p:productList) {
//            if ((p.getPrice()>=minPrice)&&(p.getPrice()<=maxPrice)){
//                list1.add(p);
//            }
//        }
        return list1;
    }

    @Override
    public Product getProductByBarcode(String barcode) {
        Product product1 = productList.stream().filter(product -> product.getBarcode().equals(barcode)).findFirst().get();
        return product1;
    }
}
