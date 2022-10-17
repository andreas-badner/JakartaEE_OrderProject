import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Diese Klasse ist die Entity-Klasse für alle Aufträge. Hier werden die Attribute festgelegt, die auch in der
 * Datenbank sein werden.
 */
@Entity(name = "orders")
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "customer_name", nullable = false)
    private String customer_name;
    @Column(name = "customer_address", nullable = false)
    private String customer_address;
    @Column(name = "customer_city", nullable = false)
    private String customer_city;
    @Column(name = "customer_plz", nullable = false)
    private int customer_plz;
    @Column(name = "price_class", nullable = false)
    private Integer price_class;
    @Column(name = "order_status", nullable = false)
    private Integer order_status;

    public Order(Integer id,
                 String customer_name,
                 String customer_address,
                 String customer_city,
                 int customer_plz,
                 Integer price_class,
                 Integer order_status) {
        this.id = id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_city = customer_city;
        this.customer_plz = customer_plz;
        this.price_class = price_class;
        this.order_status = order_status;
    }

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(String customer_city) {
        this.customer_city = customer_city;
    }

    public int getCustomer_plz() {
        return customer_plz;
    }

    public void setCustomer_plz(int customer_plz) {
        this.customer_plz = customer_plz;
    }

    public Integer getPrice_class() {
        return price_class;
    }

    public void setPrice_class(Integer price_class) {
        this.price_class = price_class;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_name='" + customer_name + '\'' +
                ", customer_address='" + customer_address + '\'' +
                ", customer_city='" + customer_city + '\'' +
                ", customer_plz=" + customer_plz +
                ", price_class=" + price_class +
                ", order_status=" + order_status +
                '}';
    }
}
