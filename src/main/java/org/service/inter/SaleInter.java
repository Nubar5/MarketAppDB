package org.service.inter;

import org.models.Item;
import org.models.Sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleInter {
    List<Sale> showSale();
    Sale showSaleById(Long id);
    List<Sale> showSaleByPrice(Double minPrice, Double maxPrice);
    List<Sale> showSaleByDate(LocalDate time);
    List<Sale> showSaleByDuration(LocalDateTime startTime, LocalDateTime endTime);

    void addSale(List<Item> items);

    void removeSale(Long id);

    void removeItem(Long saleId,String barcode);

}
