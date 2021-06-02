public class LinkedListProduct {
    Node head, tail;

    
    public LinkedListProduct() {
        head = tail = null;
    }

    public Node getHead() {
        return head;
    }
    public void clear(){
        Node q = head;
        while(true){
            if(q == null)   break;
            remove(q);
            q = q.next;
        }
    }
    public boolean isEmpty(){
        return head == null;
    }
    int size()
     {
         Node q = head;
         int i=0;
         while(q != null){
             q=q.next;
             i++;
         }
         return(i);
     }
    
    void addLast(Product x)
     {Node q = new Node(x);
       if(isEmpty()) {head=tail=q;return;}
       tail.next = q;
       tail = q;
     }
    public void visitProduct(Node p){
        if(p != null){
            System.out.println(p.infoProduct.toString());
        }
    }
   
    public void traverseProduct(){
        Node p = head;
        while(p != null){
            visitProduct(p);
            p = p.next;
        }
    }
    
    public Node searchByPcode(String xCode){
        if(isEmpty())   return null;
        Node q = head;
        while(q != null && !q.infoProduct.pcode.equalsIgnoreCase(xCode)){
            q = q.next;
        }
        return q;
    }
    public void deleteByPcode(String xCode){
        if(isEmpty())   return;
        Node q = head;
        Node p = null;
        if(q != null && q.infoProduct.pcode.equalsIgnoreCase(xCode)){
            q = head.next;
            return;
        }
        while(q != null && !q.infoProduct.pcode.equalsIgnoreCase(xCode)){
            p = q;
            q = q.next;
        }
        if(q == null){
            return;
        }
        p.next = q.next;
    }
    void sortByPcode(){
        if(isEmpty())   return;
        for(Node i = head; i != null; i = i.next){
            for(Node j = i.next; j != null; j = j.next){
                if(i.infoProduct.pcode.compareToIgnoreCase(j.infoProduct.pcode) > 0){
                    Product temp = i.infoProduct;
                    i.infoProduct = j.infoProduct;
                    j.infoProduct = temp;
                }
            }
        }
    }
    void addAfterPosK(int k, Product x){
        if(isEmpty()){
            return;
        }
        int i = 0;
        Node q = head;
        while(q != null){
            if(i == k){
                Node p = new Node(x, null);
                p.next = q.next;
                q.next = p;
            }
            i++;
            q = q.next;
        }
    }
    void remove(Node q)
     {
         Node temp = head;
         Node prev = null;
         if(temp != null && temp == q){
             head =  temp.next;
             return;
         }
         while(temp != null && temp != q){
             prev = temp;
             temp = temp.next;
         }
         if(temp == null){
             return;
         }
         prev.next = temp.next;
     }
    void deleteNodeAfterNodeHavingXcode(String xCode){
        if(isEmpty())   return;
        Node q = head;
        if(q != null && q.infoProduct.pcode.equalsIgnoreCase(xCode)){
            remove(q.next);
            return;
        }
        while(q != null){
            if(q.infoProduct.pcode.equalsIgnoreCase(xCode)){
                remove(q.next);
            }
            q = q.next;
        }
    }
    
    public void sortbysaled(){
        if(isEmpty())   return;
        for(Node i = head; i != null; i = i.next){
            for(Node j = i.next; j != null; j = j.next){
                if(i.infoProduct.saled > j.infoProduct.saled){
                    Product temp = i.infoProduct;
                    i.infoProduct = j.infoProduct;
                    j.infoProduct = temp;
                }
            }
        }
    }
}
