package cloud.heeki.oci;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class OciController {
    @RequestMapping("/")
    public String index() {
        Customer c1 = new Customer("John", "Doe", "1970-01-01", "john.doe@heeki.cloud", "+15551234567", true);
        return c1.toString();
    }
}
