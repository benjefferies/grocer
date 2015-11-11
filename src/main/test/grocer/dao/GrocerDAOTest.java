package grocer.dao;

import grocer.api.Product;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by benjefferies on 16/07/15.
 */
public class GrocerDAOTest {

    private GrocerDAO grocerDAO;

    @Before
    public void setUp() throws Exception {
        EntityManager em = createEM();
        grocerDAO = new GrocerDAO(em);
        grocerDAO.insertOrUpdateProduct(new Product("apple", new BigDecimal(10), 1l));
    }

    private EntityManager createEM() {
        return Persistence.createEntityManagerFactory("test").createEntityManager();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        grocerDAO.insertOrUpdateProduct(new Product("apple", new BigDecimal(10), 2l));
        assertThat(grocerDAO.loadProducts().size(), equalTo(1));
        assertThat(grocerDAO.loadProducts().get(0).getStock(), equalTo(2l));
    }

    @Test
    public void testInsertNewProduct() throws Exception {
        grocerDAO.insertOrUpdateProduct(new Product("banana", new BigDecimal(10), 2l));
        assertThat(grocerDAO.loadProducts().size(), equalTo(2));
    }

    @Test
    public void testSearchProductByName() throws Exception {
        final Product apple = grocerDAO.searchForProduct("apple");
        assertThat(apple.getName(), equalTo("apple"));
    }

    @Test
    public void testRetrieveProductsOrderByUpdateDate() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        grocerDAO.insertOrUpdateProduct(new Product("banana", new BigDecimal(10), 2l));
        final List<Product> products = grocerDAO.loadProducts();
        assertThat(products.get(0).getName(), equalTo("apple"));
        assertThat(products.get(1).getName(), equalTo("banana"));
    }

}