package cloud.heeki.oci;

import com.google.gson.Gson;

public class Customer {
    public String given_name;
    public String family_name;
    public String birthdate;
    public String email;
    public String phone_number;
    public boolean phone_number_verified;

    public Customer(String given_name, String family_name, String birthdate, String email, String phone_number, boolean phone_number_verified) {
        this.given_name = given_name;
        this.family_name = family_name;
        this.birthdate = birthdate;
        this.email = email;
        this.phone_number = phone_number;
        this.phone_number_verified = phone_number_verified;
    }

    public String toString() {
        Gson g = new Gson();
        return g.toJson(this);
    }
}
