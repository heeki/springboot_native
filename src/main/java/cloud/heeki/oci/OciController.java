package cloud.heeki.oci;

import com.google.gson.Gson;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OciController {
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    OciController() {
        Customer c1 = new Customer("John", "Doe", "1970-01-01", "john.doe@heeki.cloud", "+15551234567", true);
		customers.add(c1);
        // Gson g = new Gson();
		// String s = "{\"given_name\":\"Jane\",\"family_name\":\"Doe\",\"birthdate\":\"1970-01-01\",\"email\":\"jane.doe@heeki.cloud\",\"phone_number\":\"+15551234567\",\"phone_number_verified\":true}";
        // Customer c2 = g.fromJson(s, Customer.class);
        // customers.add(c2);
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
