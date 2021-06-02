

public class Node {
    Product infoProduct;
    public Customer infoCustomer;
    Ordering infoOrdering;
    public Node next;
    Node() {
    }
    public Node(Product infoProduct, Node next) {
        this.infoProduct = infoProduct;
        this.next = next;
    }
    Node(Product x){
        this(x,null);
    }
    public Node(Customer infoCustomer, Node next) {
        this.infoCustomer = infoCustomer;
        this.next = next;
    }
    public Node(Customer x){
        this(x,null);
    }
    public Node(Ordering infoOrdering, Node next) {
        this.infoOrdering = infoOrdering;
        this.next = next;
    }
    Node(Ordering x){
        this(x,null);
    }

    public void setInfoProduct(Product infoProduct) {
        this.infoProduct = infoProduct;
    }
    
 
}
