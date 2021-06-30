package BinarySearchTree;

import Entity.Customer;
import Entity.Node;
import java.util.*;

public class BSTreeCustomer {
    public Node root;

    public BSTreeCustomer() {
    }

    public void clear(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    
    public void insert(Customer x){
        if(isEmpty()){
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while(p != null){
            if(p.infoCustomer.getCcode().equals(x.getCcode())){
                System.out.println("Ccode" +x.getCcode() +" has exists in BSTree");
                return;
            }
            f = p;
            if(x.getCcode().compareTo(p.infoCustomer.getCcode()) < 0){
                p = p.left;
            }
            else{
                p = p.right;
            }
        }
        if(x.getCcode().compareTo(f.infoCustomer.getCcode()) < 0){
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
    public void inOrder(Node p){
        if(p == null)   return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
    public Node searchByCcode(Node p, String xCcode){
        if(p == null)   return null;
        if(p.infoCustomer.getCcode().equals(xCcode)) return p;
        if(xCcode.compareTo(p.infoCustomer.getCcode()) < 0){
            return searchByCcode(p.left, xCcode);
        }
        else{
            return searchByCcode(p.right, xCcode);
        }
    }
    public void delete(String xCcode){
       if(isEmpty())    return;
       Node current = root;
       Node previous = null;
       while(current != null && !current.infoCustomer.getCcode().equals(xCcode)){
           previous = current;
           if(xCcode.compareTo(current.infoCustomer.getCcode()) < 0){
               current = current.left;
           }
           else{
               current = current.right;
           }
       }
       if(current == null){
           System.out.println("Can not found "+ xCcode + " in BST");
           return;
       }
       if(current.left == null || current.right == null){
           Node newCurr;
           if(current.left == null){
               newCurr = current.right;
           }
           else{
               newCurr = current.left;
           }
           if(previous == null){
               newCurr = root;
           }
           if(current == previous.left){
               previous.left = newCurr;
           }
           else{
               previous.right = newCurr;
           }
           current = null;
       }
       else{
           Node p = null;
           Node temp;
           temp = current.right;
           while(temp.left != null){
               p = temp;
               temp = temp.left;
           }
           if(p != null){
               p.left = temp.right;
           }
           else{
               current.right = temp.right;
           }
           current.infoCustomer = temp.infoCustomer;
           temp = null;
       }
   }
}
