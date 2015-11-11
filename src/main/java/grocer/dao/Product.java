package grocer.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by benjefferies on 16/07/15.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    private String name;
    private BigDecimal price;
    private Long stock;
    private Date updated;

    public Product(final String name, final BigDecimal price, final Long stock, final Date updated) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.updated = updated;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(final Long stock) {
        this.stock = stock;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", updated=" + updated +
                '}';
    }
}
