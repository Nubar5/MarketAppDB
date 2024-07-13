package org.service.inter;

import org.models.Product;

import java.util.List;

public interface ProductInter {

     void addProduct(Product product);
     void deleteProduct(Long id);
     void updateProductName(Long id,String name);
     void updateProductPrice(Long id,Double price);
     void updateProductAmount(Long id,int amount);
     void updateProductCategory(Long id, String category);
     List<Product> showProducts();
     List<Product> showProductsByName(String name);
     List<Product> showProductsByCategory(String category);
     List<Product> showProductsByPrice(Double minPrice,Double maxPrice);

     Product getProductByBarcode(String barcode);

}
