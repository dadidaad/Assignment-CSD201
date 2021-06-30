package BinarySearchTree;

import Entity.*;
import java.util.*;

public class BSTreeProduct {
    public Node root;

    public BSTreeProduct() {
    }

    public void clear(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    
    public void insert(Product x){
        if(isEmpty()){
            root = new Node(x);
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while(p != null){
            if(p.infoProduct.getPcode().equals(x.getPcode())){
                System.out.println("Pcode" +x.getPcode() +" has exists in BSTree");
                return;
            }
            f = p;
            if(x.getPcode().compareTo(p.infoProduct.getPcode()) < 0){
                p = p.left;
            }
            else{
                p = p.right;
            }
        }
        if(x.getPcode().compareTo(f.infoProduct.getPcode()) < 0){
            f.left = new Node(x);
        }
        else{
            f.right = new Node(x);
        }
    }
    public void visit(Node p){
        if(p != null) System.out.println(p.infoProduct);
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
    public void breathCode(Node p){
        if(p == null)   return;
        Queue<Node> q = new LinkedList<>();
        q.add(p);
        Node r;
        while(!q.isEmpty()){
            r = q.poll();
            System.out.println(r.infoProduct.getPcode());
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
    public Node searchByPcode(Node p, String xPcode){
        if(p == null)   return null;
        if(p.infoProduct.getPcode().equals(xPcode)) return p;
        if(xPcode.compareTo(p.infoProduct.getPcode()) < 0){
            return searchByPcode(p.left, xPcode);
        }
        else{
            return searchByPcode(p.right, xPcode);
        }
    }
    public void delete(String xPcode){
       if(isEmpty())    return;
       Node current = root;
       Node previous = null;
       while(current != null && !current.infoProduct.getPcode().equals(xPcode)){
           previous = current;
           if(xPcode.compareTo(current.infoProduct.getPcode()) < 0){
               current = current.left;
           }
           else{
               current = current.right;
           }
       }
       if(current == null){
           System.out.println("Can not found "+ xPcode + " in BST");
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
           current.infoProduct = temp.infoProduct;
           temp = null;
       }
   }
    public void balanceTree(){
       Node p = root;
       Stack<Node> st = new Stack<>();
       ArrayList<Product> list = new ArrayList<>();
       while(!st.isEmpty() || p != null){
           if(p!= null){
               st.push(p);
               p = p.left;
           }
           else{
               p = st.pop();
               list.add(p.infoProduct);
               p = p.right;
           }
       }
       clear();
       int n = list.size();
       Product x = new Product();
       root = new Node(x);
       st.push(root);
       Stack<Integer>  leftIndexStack  = new Stack();
       leftIndexStack.push(0);
       Stack<Integer>  rightIndexStack = new Stack();
       rightIndexStack.push(n-1);
       while(!st.isEmpty()){
           Node temp = st.pop();
           int left = leftIndexStack.pop();
           int right = rightIndexStack.pop();
           int mid = left + (right - left)/2;
           temp.infoProduct = list.get(mid);
           if(left <= mid - 1){
               temp.left = new Node(x);
               st.push(temp.left);
               leftIndexStack.push(left);
               rightIndexStack.push(mid-1);
           }
           if(mid+1 <= right){
               temp.right = new Node(x);
               st.push(temp.right);
               leftIndexStack.push(mid+1);
               rightIndexStack.push(right);
           }
       }
   }
    public Node removeSubtree(Node p, String xPcode){
        if (p != null && p.infoProduct.getPcode().equals(xPcode)) return null;
        removeSubtreeRecursion(p, xPcode);
        return p;
    }

    public void removeSubtreeRecursion(Node parent, String xPcode) {
        if (parent.left != null && parent.left.infoProduct.getPcode().equals(xPcode)) parent.left = null;
        if (parent.right != null && parent.right.infoProduct.getPcode().equals(xPcode)) parent.right = null;
    }
    void balance(ArrayList<Product> t, int i, int j){
       if(i>j)  return;
       Product x;
       int k = (i+j)/2;
       x = t.get(k);
       insert(x);
       balance(t, i, k-1);
       balance(t, k+1, j);
   }
   void inOrderBalance(ArrayList<Product> t, Node p){
       if(p == null)    return;
       inOrderBalance(t, p.left);
       t.add(p.infoProduct);
       inOrderBalance(t, p.right);
   }
   public void balance(){
       ArrayList<Product> list = new ArrayList<>();
       Node p = searchByPcode(root, "3");
       inOrderBalance(list, p);
       root = removeSubtree(root, "3");
       int n = list.size();
       balance(list, 0, n-1);
   }
   public int count(Node p){
       if(p == null)    return 0;
       return 1 + count(p.left) + count(p.right);
   }
   int height(Node p){
        if(p == null)   return 0;
        int lHeight = height(p.left);
        int rHeight = height(p.right);
        if(lHeight > rHeight)   return lHeight + 1;
        else return rHeight + 1;
   }
   int max(int a, int b){
       return a > b ? a : b;
   }
   Node rightRotate(Node p){
       Node r = p.left;
       Node t = r.right;
       r.right = p;
       p.left = t;
       p.height = max(height(p.left), height(p.right)) + 1;
       r.height = max(height(r.left), height(r.right)) + 1;
       return r;
   }
   Node leftRotate(Node p){
       Node r = p.right;
       Node t = r.left;
       r.left = p;
       p.right = t;
       p.height = max(height(p.left), height(p.right)) + 1;
       r.height = max(height(r.left), height(r.right)) + 1;
       return r;
   }
   int getBalance(Node p){
       if(p == null)    return 0;
       return height(p.left) - height(p.right);
   }
}
