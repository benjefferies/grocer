package grocer;

import grocer.api.GrocerAdminAPI;
import grocer.api.Product;
import grocer.dao.GrocerDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

/**
 * Created by benjefferies on 16/07/15.
 */
public class GrocerAdminAPITest {

    private GrocerAdminAPI grocerAdminAPI;

    private String name = "banana";
    private BigDecimal price = new BigDecimal(10.99);
    private Long stock = 1l;

    @Mock private GrocerDAO grocerDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        grocerAdminAPI = new GrocerAdminAPI(grocerDao);
    }

    @Test
    public void testUploadProduct() throws Exception {
        final Product product = new Product(name, price, stock);
        grocerAdminAPI.uploadProduct(product);

        verify(grocerDao).insertOrUpdateProduct(product);
    }

    @Test
    public void testUpdateProductPrice() throws Exception {
        final BigDecimal newPrice = new BigDecimal(2l);

        grocerAdminAPI.updatePrice(name, newPrice);

        verify(grocerDao).updatePrice(name, newPrice);
    }

    @Test
    public void testSearchForProductByName() throws Exception {
        grocerAdminAPI.searchForProduct(name);

        verify(grocerDao).searchForProduct(name);
    }
}