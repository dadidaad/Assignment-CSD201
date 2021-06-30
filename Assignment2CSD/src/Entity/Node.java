package Entity;

public class Node {
    public Product infoProduct;
    public Customer infoCustomer;
    public Ordering infoOrdering;
    public Node left, right;
    public int height;
    public Node() {
    }
    public Node(Product x){
        infoProduct = x;
        left = right = null;
        height = 1;
    }
    public Node(Customer x){
        infoCustomer = x;
        left = right = null;
    }
    public Node(Ordering x){
        infoOrdering = x;
        left = right = null;
    }

    public void setInfoProduct(Product infoProduct) {
        this.infoProduct = infoProduct;
    }
    
}

