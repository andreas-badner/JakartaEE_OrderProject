import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * Diese Klasse erzeugt Dummy-Daten für die Datenbank. Diese werden beim Starten des Applikationsservers erzeugt.
 */
@Singleton
@Startup
public class InitBean {

    /**
     * Der RequestBean verwendet den EntityManager, um die Daten zu speichern oder zu modifizieren.
     */
    @EJB
    private RequestBean request;

    /**
     * Diese Methode erzeugt einige Beispielaufträge, die zur Darstellung verwendet werden.
     */
    @PostConstruct
    public void createData() {
        request.createOrder(516, "Alexander Busch", "Rosenstraße 17", "Wolfsburg", 41512, 6, 0);
        request.createOrder(612, "Felix Schauf", "Serienweg 31", "Kaiserslautern", 51261, 4, 1);
        request.createOrder(12, "Andreas Badner", "Rosengartenweg 4", "Herford", 32052, 1, 3);
        request.createOrder(161, "Peter Krausendorf", "Kleineweg 5", "Nürnburg", 51611, 5, 2);
        request.createOrder(1511, "Jessica Gauß", "Petersweg 11", "Berlin", 21515, 3, 0);
        request.createOrder(241, "Felix Smith", "Kreuzstraße 151", "München", 15115, 3, 2);
        request.createOrder(519, "Julia Sieker", "Schneestraße 1", "Freiburg", 12511, 2, 1);
    }

    @PreDestroy
    public void deleteData() {

    }
}
