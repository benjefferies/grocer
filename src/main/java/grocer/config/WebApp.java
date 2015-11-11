package grocer.config;

import grocer.api.GrocerAPI;
import grocer.api.GrocerAdminAPI;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by benjefferies on 16/07/15.
 */
public class WebApp extends ResourceConfig {

    public WebApp() {
        super(GrocerAPI.class, GrocerAdminAPI.class);
    }

}