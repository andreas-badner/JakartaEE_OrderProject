import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Diese Klasse interagiert mit den JSF-Aufrufen aus den jeweiligen xhtml-Seiten.
 */
@Named("orderView")
@ViewScoped
public class OrderManager implements Serializable {
    @EJB
    private RequestBean request;
    private List<Order> orders;
    private int count;
    private int new_id;
    private String new_customer_name;
    private String new_customer_address;
    private String new_customer_city;
    private int new_customer_plz;
    private int new_weight;
    private static final Logger logger = Logger.getLogger("OrderService");


    /**
     * Diese Methode wird von einer JSF-Seite aufgerufen. Diese Methode ruft dann anschließend die getOrders()-Methode
     * des RequestBeans auf.
     * @return [List<Order>] Liste aller Aufträge.
     */
    public List<Order> getOrders() {
        try {
            this.orders = request.getOrders();

        } catch (Exception e) {
            logger.warning("Aufträge konnten nicht aufgerufen werden.");
            return null;
        }
        return orders;
    }

    /**
     * Diese Methode soll lediglich etwas mehr Logik einbinden. Die Methode erhält ein Parameter Gewicht, mit dem
     * eine Preisklasse ermittelt werden kann.
     * @param weight [int] ein Gewicht des Artikels.
     * @return [int] eine Preisklasse für den Artikel.
     */
    public int calcPriceClass(int weight) {
        if (weight < 25) {
            return 1;
        } else if (weight < 40) {
            return 2;
        } else if (weight < 80) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Diese Methode wird von JSF-Seiten aufgerufen. Diese erhält alle Parameter aus dem Formular und erzeugt
     * damit einen neuen Auftrag in der Datenbank mittels dem RequestBean.
     */
    public void submitOrder() {
        try {
            int price_class = calcPriceClass(new_weight);
            Random random = new Random();
            new_id = random.nextInt(9999);
            new_id += 1;

            request.createOrder(new_id,
                    new_customer_name,
                    new_customer_address,
                    new_customer_city,
                    new_customer_plz,
                    price_class,
                    0);
            this.new_id = 0;
            this.new_customer_name = null;
            this.new_customer_address = null;
            this.new_customer_city = null;
            this.new_customer_plz = 0;
            this.new_weight = 0;
        } catch (Exception e) {
            logger.warning("Ein Problem beim Erstellen eines neuen Auftrags ist geschehen.");
        }
    }


    /**
     * Diese Methode wird von einer JSF-Seite aufgerufen. Diese ruft den RequestBean auf und ermittelt die Anzahl
     * aller Aufträge.
     * @return [int] die Anzahl aller Aufträge.
     */
    public int getCount() {
        try {
            this.count = request.countAllItems();
        } catch (Exception e) {
            logger.warning("Aufträge konnten nicht gezählt werden.");
            return 0;
        }
        return count;
    }


    /**
     * Diese Methode kann benutzt werden, um einen Auftrag zu löschen.
     * @param order Diese Methode nimmt einen Auftrag entgegen, um diese zu löschen.
     */
    public void deleteOrder(Order order) {
        try {
            request.removeOrder(order.getId());
        } catch (Exception e) {
            logger.warning("Der Auftrag konnte nicht gelöscht werden.");
        }
    }



    // Es folgen viele Getter und Setter Methoden

    public int getNew_id() {
        return new_id;
    }

    public void setNew_id(int new_id) {
        this.new_id = new_id;
    }

    public String getNew_customer_name() {
        return new_customer_name;
    }

    public void setNew_customer_name(String new_customer_name) {
        this.new_customer_name = new_customer_name;
    }

    public String getNew_customer_address() {
        return new_customer_address;
    }

    public void setNew_customer_address(String new_customer_address) {
        this.new_customer_address = new_customer_address;
    }

    public String getNew_customer_city() {
        return new_customer_city;
    }

    public void setNew_customer_city(String new_customer_city) {
        this.new_customer_city = new_customer_city;
    }

    public int getNew_customer_plz() {
        return new_customer_plz;
    }

    public void setNew_customer_plz(int new_customer_plz) {
        this.new_customer_plz = new_customer_plz;
    }

    public int getNew_weight() {
        return new_weight;
    }

    public void setNew_weight(int new_weight) {
        this.new_weight = new_weight;
    }
}
