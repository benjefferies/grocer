package grocer.api;

import grocer.dao.GrocerDAO;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by benjefferies on 16/07/15.
 */
@Path("/api/admin")
@Component
public class GrocerAdminAPI {

    private final GrocerDAO grocerDAO;

    @Inject
    public GrocerAdminAPI(final GrocerDAO grocerDAO) {
        this.grocerDAO = grocerDAO;
    }

    @POST
    @Path("upload")
    @Consumes("application/json")
    public void uploadProduct(final Product product) {
        grocerDAO.insertOrUpdateProduct(product);
    }

    @POST
    @Path("uploads")
    @Consumes("application/json")
    public void uploadProducts(final List<Product> products) {
        products.stream().forEach(grocerDAO::insertOrUpdateProduct);
    }

    @PUT
    @Path("update/{name}/{newPrice}")
    public void updatePrice(@PathParam("name")final String name, @PathParam("newPrice") final BigDecimal newPrice) {
        grocerDAO.updatePrice(name, newPrice);
    }

    @GET
    @Path("search/{name}")
    @Produces("application/json")
    public Product searchForProduct(@PathParam("name") final String name) {
        return grocerDAO.searchForProduct(name);
    }
}
