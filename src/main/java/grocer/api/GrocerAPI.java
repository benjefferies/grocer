package grocer.api;

import grocer.dao.GrocerDAO;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by benjefferies on 16/07/15.
 */
@Path("/api/grocer")
@Component
public class GrocerAPI {

    private final GrocerDAO grocerDao;

    @Inject
    public GrocerAPI(final GrocerDAO grocerDao) {
        this.grocerDao = grocerDao;
    }

    @GET
    @Path("loadProducts")
    @Produces("application/json")
    public List<Product> loadProducts() {
        return grocerDao.loadProducts();
    }
}
