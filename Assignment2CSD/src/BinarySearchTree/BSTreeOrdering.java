package BinarySearchTree;

import Entity.Node;
import Entity.Ordering;
import java.util.*;

public class BSTreeOrdering {
    public Node root;

    public BSTreeOrdering() {
    }

    public void clear(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    
    public void insert(Ordering x){
        if(isEmpty()){
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while(p != null){
            if(p.infoOrdering.getPcode().equals(x.getPcode())){
                if(p.infoOrdering.getCcode().equals(x.getCcode())){
                    System.out.println(x.getPcode() + " " + x.getCcode() + " has exists in list");
                    return;
                }
                f = p;
                if(x.getCcode().compareTo(p.infoOrdering.getCcode()) < 0){
                    p = p.left;
                }
                else{
                    p = p.right;
                }
            }
            else{
                f = p;
                if(x.getCcode().compareTo(p.infoOrdering.getCcode()) < 0){
                    p = p.left;
                }
                else{
                    p = p.right;
                }
            }
        }
        if(x.getPcode().equals(f.infoOrdering.getPcode())){
            if(x.getCcode().compareTo(f.infoOrdering.getCcode()) < 0){
                f.left = new Node(x);
            }
            else{
                f.right = new Node(x);
            }
        }
        else if(x.getCcode().compareTo(f.infoCustomer.getCcode()) < 0){
            f.left = new Node(x);
        }
        else{
            f.right = new Node(x);
        }
    }
    public void visit(Node p){
        if(p != null) System.out.println(p.infoCustomer);
    }
    public void breadth(Node p){
        if(p == null)   return;
        Queue<Node> q = new LinkedList<>();
        q.add(p);
        Node r;
        while(!q.isEmpty()){
            r = q.poll();
            visit(r);
            if(r.left != null)  q.add(r.left);
            if(r.right != null) q.add(r.right);
        }
    }
}
