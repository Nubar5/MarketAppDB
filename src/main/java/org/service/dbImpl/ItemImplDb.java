package org.service.dbImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.config.AbstractDAO;
import org.models.Item;
import org.models.Product;
import org.service.impl.ProductImpl;
import org.service.inter.ItemInter;

import java.util.Optional;

public class ItemImplDb extends AbstractDAO implements ItemInter {

    @Override
    public Double itemTotal(String barcode, int count) {
        ProductImplDb productImplDb  =new ProductImplDb();
        Double total =0d;

        Product product =productImplDb.getProductByBarcode(barcode);
        total= product.getPrice()*count;
        return total;
    }
    public void removeItem(String barcode){
        EntityManager em =entityManager();
        em.getTransaction().begin();

        em.remove(findItembyBarcode(barcode));
        em.getTransaction().commit();
        em.close();
    }
    public Item findItembyBarcode(String itemBarcode){
        EntityManager em = entityManager();
        em.getTransaction().begin();
        TypedQuery<Item> query = em.createQuery("select i from Item i where i.barcode=:itemBarcode", Item.class);
        query.setParameter("barcode",itemBarcode);
        Item item =query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return item;


    }
}
