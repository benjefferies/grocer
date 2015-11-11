package grocer;

import grocer.api.GrocerAPI;
import grocer.dao.GrocerDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by benjefferies on 16/07/15.
 */
public class GrocerAPITest {

    private GrocerAPI grocerAPI;

    @Mock private GrocerDAO grocerDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.grocerAPI = new GrocerAPI(grocerDao);
    }

    @Test
    public void testRetrieveProducts() throws Exception {
        grocerAPI.loadProducts();
        verify(grocerDao).loadProducts();
    }

}