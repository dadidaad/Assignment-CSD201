package Manager;

import BinarySearchTree.*;
import Entity.Ordering;
import java.util.Scanner;

public class OrderingMenu {
    BSTreeOrdering bsto = new BSTreeOrdering();
    CustomerMenu y = new CustomerMenu();
    ProductMenu x = new ProductMenu();
    Validate vld = new Validate();
    OrderingMenu(ProductMenu x, CustomerMenu y){
        this.x = x;
        this.y = y;
    }
    public static Scanner sc = new Scanner(System.in);
    BSTreeProduct bstp = x.getBst();
    BSTreeCustomer bstc = y.getBst();
    public void input_data(){
        if(bstp.isEmpty() || bstc.isEmpty()){
            System.out.println("Product list or Customer list is empty");
            return;
        }
        while(true){
            try{
                System.out.print("Enter pcode: ");
                String pcode = vld.checkInput();
                if(bstp.searchByPcode(bstp.root, pcode) == null) throw new MyException("Can not found pcode in Product list");
                System.out.print("Enter ccode: ");
                String ccode = vld.checkInput();
                if(bstp.searchByPcode(bstp.root, ccode) == null) throw new MyException("Can not found ccode in Customer list");
                System.out.print("Enter quantity: ");
                int quantity = vld.checkNumber();
                if(vld.checkQuantityinOrdering(bstp, pcode, quantity)) throw new MyException("the product is  exhausted.");
                if(!vld.checkQuantityInput(bstp, pcode, quantity)) throw new MyException("Quantity is over!");
                Ordering x = new Ordering(pcode, ccode, quantity);
                bsto.insert(x);
            }
            catch(MyException e){
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Add successfully");
            break;
        }
    }
    public void display(){
        bsto.breadth(bsto.root);
    }
}
