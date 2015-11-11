package grocer.api;

import java.math.BigDecimal;

/**
 * Created by benjefferies on 16/07/15.
 */
public class Product {
    private final String name;
    private final BigDecimal price;
    private final Long stock;

    public Product(final String name, final BigDecimal price, final Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
