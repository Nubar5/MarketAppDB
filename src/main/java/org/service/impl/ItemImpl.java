package org.service.impl;

import org.models.Product;
import org.service.inter.ItemInter;

import java.util.Optional;

public class ItemImpl implements ItemInter {


    @Override
    public Double itemTotal(String barcode, int count) {
        Double total =0d;
        Optional<Product> product1=ProductImpl.productList.stream().filter(product -> product.getBarcode().equals(barcode)).findFirst();
       total= product1.get().getPrice()*count;

//        ProductImpl product=new ProductImpl();
//       for (Product p:product.showProducts()) {
//         if(p.getBarcode().equals(barcode)){
//            total=count*p.getPrice();
//         }
//        }

        return total;
    }
}
