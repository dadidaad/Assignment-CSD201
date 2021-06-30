package Manager;

import static Manager.ProductMenu.sc;
import java.io.*;

public class Program {
    ProductMenu pm = new ProductMenu();
    CustomerMenu cm = new CustomerMenu();
    OrderingMenu om = new OrderingMenu(pm, cm);
    public void menuProduct() throws IOException{
        while(true){
            System.out.println("1.1.      Load data from file\n" +
                                "1.2.      Input & insert data\n" +
                                "1.3.      In-order traverse\n" +
                                "1.4.      Breadth-first traverse\n" +
                                "1.5.      In-order traverse to file\n" +
                                "1.6.      Search by pcode\n" +
                                "1.7.      Delete by pcode by copying\n" +
                                "1.8.      Simply balancing\n" +
                                "1.9.      Count number of products\n"+
                                "1.10.     Return main");
            System.out.print(">> Your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 10)    break;
            switch(choice){
                case 1:
                    pm.loadData();
                    sc.nextLine();
                    continue;
                case 2:
                    pm.input_add();
                    sc.nextLine();
                    continue;
                case 3: 
                    pm.inOrder();
                    sc.nextLine();
                    continue;
                case 4:
                    pm.breadth();
                    sc.nextLine();
                    continue;
                case 5:
                    pm.inOrderToFile();
                    sc.nextLine();
                    continue;
                case 6:
                    pm.searchByPcode();
                    sc.nextLine();
                    continue;
                case 7:
                    pm.delete();
                    sc.nextLine();
                    continue;
                case 8:
                    pm.simpleBalance();
                    sc.nextLine();
                    continue;
                case 9:
                    pm.countProduct();
                    sc.nextLine();
                    continue;
                case 11:
                    pm.f1();
                    pm.f2();
                    pm.f3();
                    pm.f4();
                    pm.f5();
                    continue;
                case 12:
                    pm.f2();
                    continue;
                case 13:
                    pm.f3();
                    continue;
                case 14:
                    pm.f4();
                    continue;
                case 15:
                    pm.f5();
                    continue;
                default:
                    System.out.println("Don't have this option!");
                    continue;
            }
            
          }
    }
    public void menuCustomer() throws IOException{
        while(true){
            System.out.println("2.1.      Load data from file\n" +
                                "2.2.      Input & add to the end\n" +
                                "2.3.      Display data\n" +
                                "2.4.      Save customer list to file\n" +
                                "2.5.      Search by ccode\n" +
                                "2.6.      Delete by ccode\n"+ "2.7.      Return");
            System.out.print(">> Your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 7){
                break;
            }
            switch(choice){
                case 1:
                    cm.loadDatafromFile();
                    sc.nextLine();
                    continue;
                case 2:
                    cm.input_add();
                    sc.nextLine();
                    continue;
                case 3:
                    cm.display();
                    sc.nextLine();
                    continue;
                case 4:
                    cm.BreadthToFile();
                    sc.nextLine();
                    continue;
                case 5:
                    cm.searchByCcode();
                    sc.nextLine();
                    continue;
                case 6:
                    cm.delete();
                    sc.nextLine();
                    continue;
                default:
                    System.out.println("Can not found this option!");
                    continue;
            }
        }
    }
     public void menuOrdering(){
        while(true){
            System.out.println("3.1.      Input data\n" +
                                "3.2.      Display data with total value\n" +
                                "3.3.      Sort  by pcode + ccode\n"+ "3.4.      Return");
            System.out.print(">> Your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 4) break;
            switch(choice){
                case 1:
                    om.input_data();
                    sc.nextLine();
                    continue;
                case 2:
                    om.display();
                    sc.nextLine();
                    continue;
                default:
                    System.out.println("Don't have this option");
                    continue;
            }
        }
        
    }
    public void program() throws IOException{
        int choice;
        while(true){
            try{
                System.out.println("Sale Management System");
                System.out.println("========================");
                System.out.println("1. Product list");
                System.out.println("2. Customer list");
                System.out.println("3. Ordering list");
                System.out.println("4. Exit");
                System.out.print(">> Your choice: ");
                choice = Integer.parseInt(sc.nextLine());
                if(choice == 4) break;
                switch(choice){
                    case 1:
                        menuProduct();
                        break;
                    case 2:
                        menuCustomer();
                        break;
                    case 3:
                        menuOrdering();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Don't have this option!");
                        break;
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong format!");
                continue;
            }  
        }
    }
}
