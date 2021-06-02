



public class Product {
    String pcode;
    String pro_name;
    int quantity;
    int saled;
    double price;

    public Product() {
    }

    public Product(String pcode, String pro_name, int quantity, int saled, double price) {
        this.pcode = pcode.toUpperCase();
        this.pro_name = pro_name.substring(0, 1).toUpperCase() + pro_name.substring(1).toLowerCase();
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }  

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return String.format("%s,%s,%d,%d,%.1f", pcode, pro_name, quantity, saled, price);
    }
    
}
