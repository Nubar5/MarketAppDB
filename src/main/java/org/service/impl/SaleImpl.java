package org.service.impl;

import org.models.Item;
import org.models.Product;
import org.models.Sale;
import org.service.inter.SaleInter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaleImpl implements SaleInter {

    public static List<Sale> saleList = new ArrayList<>();

    @Override
    public List<Sale> showSale() {

        return saleList;
    }

    @Override
    public Sale showSaleById(Long id) {
        Sale sale1 = saleList.stream().filter(sale -> sale.getId() == id).findFirst().orElse(new Sale());

        return sale1;
    }

    @Override
    public List<Sale> showSaleByPrice(Double minPrice, Double maxPrice) {
        List<Sale> list = saleList.stream().filter(sale -> (sale.getTotal()>=minPrice&&sale.getTotal()<=maxPrice)).collect(Collectors.toList());
//        List<Sale> list = new ArrayList<>();
//        for (Sale s : saleList) {
//            if (s.getTotal() >= minPrice && s.getTotal() <= maxPrice) {
//
//                list.add(s);
//            }
//        }
        return list;
    }

    @Override
    public List<Sale> showSaleByDate(LocalDate time) {
        List<Sale> list = saleList.stream().filter(sale -> sale.getDate().isEqual(time.atStartOfDay())).collect(Collectors.toList());
//        =  new ArrayList<>();
//        for (Sale s : saleList) {
//
//            if (s.getDate().toLocalDate().isEqual(time)) {
//                list.add(s);
//            }
//        }
        return list;
    }

    @Override
    public List<Sale> showSaleByDuration(LocalDateTime startTime, LocalDateTime endTime) {
        List<Sale> list = saleList.stream().filter(sale -> sale.getDate().isAfter(startTime) && sale.getDate().isBefore(endTime)).collect(Collectors.toList());                new ArrayList<>();
//        for (Sale s : saleList) {
//            if (s.getDate().isAfter(startTime) && s.getDate().isBefore(endTime)) {
//                list.add(s);
//            }
//        }
        return list;
    }

    @Override
    public void addSale(List<Item> items) {
        ProductImpl productimpl = new ProductImpl();

        double setTotal = 0;
        for (Item i : items) {
            Product p = productimpl.getProductByBarcode(i.getBarcode());
            setTotal += p.getPrice() * i.getCount();
            ProductImpl.productList.remove(p);
            p.setAmount(p.getAmount() - i.getCount());
            ProductImpl.productList.add(p);
        }

        Sale sale = new Sale();
        sale.setTotal(setTotal);
        sale.setItems(items);
        sale.setDate(LocalDateTime.now());
        saleList.add(sale);
    }

    @Override
    public void removeSale(Long id) {
        Sale sale = saleList.stream().filter(sale1 -> sale1.getId()==id).findFirst().get();
         saleList.remove(sale);
        System.out.println("sale removed");
    }

    @Override
    public void removeItem(Long saleId, String barcode) {
    ProductImpl productImpl = new ProductImpl();

    Sale sale1=SaleImpl.saleList.stream().filter(sale -> sale.getId()==saleId).findFirst().get();
    if(sale1.getItems().size()==1) {
        String barcode1 =sale1.getItems().get(0).getBarcode();
        SaleImpl.saleList.remove(sale1);
        Product p=productImpl.getProductByBarcode(barcode1);
        ProductImpl.productList.remove(p);
        p.setAmount(sale1.getItems().get(0).getCount()+p.getAmount());
        ProductImpl.productList.add(p);
    }
    else {
Item item1=sale1.getItems().stream().filter(item -> item.getBarcode().equals(barcode)).findFirst().get();

        SaleImpl.saleList.remove(sale1);
        sale1.getItems().remove(item1);
        sale1.setTotal(sale1.getTotal()-item1.getTotal());
        SaleImpl.saleList.add(sale1);

        String barcode1 =item1.getBarcode();
        Product p=productImpl.getProductByBarcode(barcode1);
        ProductImpl.productList.remove(p);
        p.setAmount(sale1.getItems().get(0).getCount()+p.getAmount());
        ProductImpl.productList.add(p);
    }

            }



}
