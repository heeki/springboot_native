package cloud.heeki.oci;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cloud.heeki.oci.lib.Customer;
import cloud.heeki.oci.lib.PropertiesLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Properties;

@RestController
public class OciController {
    private Properties props = PropertiesLoader.loadProperties("application.properties");
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private Gson g = new GsonBuilder().setPrettyPrinting().create();
    private String dbserver, dbport, dbname;

    OciController() {
        // initialization: database
        // dbserver = props.getProperty("database.server");
        // dbport = props.getProperty("database.port");
        // dbname = props.getProperty("database.name");
        System.out.print(g.toJson(this.props));

        // initialization: data structures
        Customer c1 = new Customer("John", "Doe", "1970-01-01", "john.doe@heeki.cloud", "+15551234567", true);
        Customer c2 = new Customer("Jane", "Doe", "1970-01-01", "jane.doe@heeki.cloud", "+15551234567", true);
        customers.add(c1);
        customers.add(c2);
    }

    @GetMapping("/")
    String getBase() {
        return g.toJson(this.props);
    }

    @GetMapping("/customer")
    String getCustomers() {
        return this.customers.toString();
    }

    @PostMapping("/customer")
    String createCustomer(@RequestBody Customer c) {
        customers.add(c);
        return c.uuid.toString();
    }

    @DeleteMapping("/customer/{id}")
    void deleteCustomer(@PathVariable String id) {
        customers.removeIf(c -> c.uuid.toString().equals(id));
    }
}
