package org.service.dbImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.config.AbstractDAO;
import org.models.Category;
import org.models.Product;
import org.service.inter.ProductInter;

import java.util.List;

public class ProductImplDb extends AbstractDAO implements ProductInter {
    @Override
    public void addProduct(Product product) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        em.persist(em.merge(product)); //m1+1 --> merge tesdiqleyirem ki bunnan yoxdu bazada
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void deleteProduct(Long id) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateProductName(Long id, String name) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            product.setName(name);
            em.merge(product);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateProductPrice(Long id, Double price) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            product.setPrice(price);
            em.merge(product);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateProductAmount(Long id, int amount) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            product.setAmount(amount);
            em.merge(product);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateProductCategory(Long id, String category) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            if(category.equals("w")){
                System.out.println(Category.Wearable.name());
            product.setCategory(Category.Wearable);}
            else if (category.equals("e")) {
                product.setCategory(Category.Eatable);
            } else if (category.equals("d")) {
                product.setCategory(Category.Drinks);
            }else System.out.println("invalid category");
            em.merge(product);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Product> showProducts() {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        List<Product> productList = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return productList;
    }

    @Override
    public List<Product> showProductsByName(String name) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Product p where p.name =:productName";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("productName", name);
        List<Product> productList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return productList;

    }

    @Override
    public List<Product> showProductsByCategory(String category) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        Category userCategory = null;
        if (category.equalsIgnoreCase("e")) {
            userCategory = Category.Eatable;
        } else if (category.equalsIgnoreCase("w")) {
            userCategory=Category.Wearable;
        } else if (category.equalsIgnoreCase("d")) {
            userCategory=Category.Drinks;
        }

        String jpql = "SELECT p FROM Product p WHERE p.category = :productCategory";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("productCategory", userCategory);

        List<Product> productList = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return productList;
    }

    @Override
    public List<Product> showProductsByPrice(Double minPrice, Double maxPrice) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Product p where p.price between :minPrice and :maxPrice";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        List<Product> productList = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return productList;

    }

    @Override
    public Product getProductByBarcode(String productBarcode) {
        EntityManager em = entityManager();
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Product p where p.barcode =:productBarcode";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("productBarcode", productBarcode);
        Product product = query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return product;
    }
}
