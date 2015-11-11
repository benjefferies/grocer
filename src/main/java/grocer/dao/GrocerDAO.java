package grocer.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by benjefferies on 16/07/15.
 */
public class GrocerDAO {

    private final EntityManager entityManager;

    @Inject
    public GrocerDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<grocer.api.Product> loadProducts() {
        final List<grocer.dao.Product> products = entityManager.createQuery("from Product").getResultList();
        return products.stream().map(p -> new grocer.api.Product(p.getName(), p.getPrice(), p.getStock())).collect(Collectors.toList());
    }

    public void updatePrice(final String name, final BigDecimal newPrice) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            grocer.dao.Product product = new grocer.dao.Product();
            product.setName(name);
            product = entityManager.find(grocer.dao.Product.class, product);
            product.setPrice(newPrice);
            entityManager.merge(product);
            transaction.commit();
        } catch (RuntimeException e) {
            // Log
            transaction.rollback();
        }
    }

    public void insertOrUpdateProduct(final grocer.api.Product product) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(new grocer.dao.Product(product.getName(), product.getPrice(), product.getStock(), new Date()));
            transaction.commit();
        } catch (RuntimeException e) {
            // Log
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public grocer.api.Product searchForProduct(final String name) {
        final grocer.dao.Product product = entityManager.find(grocer.dao.Product.class, name);
        return product == null ? null : new grocer.api.Product(product.getName(), product.getPrice(), product.getStock());
    }
}
