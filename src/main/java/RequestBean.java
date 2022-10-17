import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Diese Klasse verwendet den EntityManager, um mit der eigenen PostgreSQL-Datenbank zu interagieren.
 */
@Stateful
public class RequestBean {

    /**
     * Der EntityManager wird benötigt, um z.B. SQL-Queries zu erzeugen.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Der Logger wird verwendet, um mögliche Fehler zu finden.
     */
    private static final Logger logger = Logger.getLogger("RequestBean");

    /**
     * Diese Funktion erzeugt neue Aufträge. Dafür erhält diese Methode die benötigten Parameter und speichert diese
     * im Nachhinein mit der persist-Methode.
     * @param id [int] Die ID des Auftrags, der Primary Key
     * @param customer_name [String] Der Name des Kunden
     * @param customer_address [String] Die Adresse des Kunden
     * @param customer_city [String] Die Stadt des Kunden
     * @param customer_plz [int] Die Postleizahl des Kunden
     * @param price_class [int] Die Preisklasse des Artikels, bedingt durch das Gewicht
     * @param order_status [int] Der Status des Auftrags, ausgezeichnet durch eine Zahl.
     */
    public void createOrder(Integer id,
                            String customer_name,
                            String customer_address,
                            String customer_city,
                            Integer customer_plz,
                            Integer price_class,
                            Integer order_status) {
        try {
            Order order = new Order(id, customer_name, customer_address, customer_city, customer_plz, price_class, order_status);
            em.persist(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Diese Methode nutzt einen SQL-Query, um alle Aufträge in der Datenbank zu finden und wiederzugeben.
     * @return [List<Order>] eine Liste von Aufträgen.
     */
    public List<Order> getOrders() {
        try {
            return em.createNativeQuery("SELECT * FROM orders", Order.class).getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Diese Methode holt ebenfalls über einen SQL-Query alle Aufträge aus der Datenbank und gibt die Anzahl der Aufträge wieder.
     * @return [int] die Anzahl der Aufträge.
     */
    public int countAllItems() {
        int count = 0;
        try {
            count = em.createNativeQuery(
                            "SELECT * FROM orders")
                    .getResultList()
                    .size();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
        return count;
    }


    /**
     * Diese Methode löscht einen Auftrag anhand der ID
     * @param id [int] Die ID des Auftrags, der Primary Key innerhalb der Datenbank
     */
    public void removeOrder(int id) {
        try {
            Order order = em.find(Order.class, id);
            em.remove(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
