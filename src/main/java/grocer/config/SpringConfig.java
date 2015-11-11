package grocer.config;

import grocer.dao.GrocerDAO;
import grocer.dao.Product;
import org.hibernate.ejb.Ejb3Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * Created by benjefferies on 16/07/15.
 */
@Configuration
@ComponentScan("grocer.api")
public class SpringConfig {

    @Bean
    EntityManager entityManager() {
        Properties properties = new Properties();
        properties.put("javax.persistence.provider", "org.hibernate.ejb.HibernatePersistence");
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("hibernate.connection.username", "sa");
        properties.put("hibernate.connection.password" ,"");
        properties.put("hibernate.connection.driver_class","org.h2.Driver");
        properties.put("hibernate.connection.url", "jdbc:h2:mem:test;MODE=DB2");
        properties.put("hibernate.dialect" ,"org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto","create-drop");

        Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.setProperties(properties);
        cfg.addAnnotatedClass(Product.class);

        EntityManagerFactory factory = cfg.buildEntityManagerFactory();
        return factory.createEntityManager();
    }

    @Bean
    GrocerDAO grocerDAO(EntityManager entityManager) {
        return new GrocerDAO(entityManager);
    }
}
