package Entity;

public class Customer {
    String ccode;
    String cus_name;
    String phone;

    public Customer() {
    }

    public Customer(String ccode, String cus_name, String phone) {
        this.ccode = ccode.toUpperCase();
        this.cus_name = cus_name.substring(0, 1).toUpperCase() + cus_name.substring(1).toLowerCase();
        this.phone = phone;
    }

    public String getCcode() {
        return ccode;
    }
    

    @Override
    public String toString() {
        return String.format("%s   |   %-7s   |  %4s", ccode, cus_name, phone);
    }
    
}
