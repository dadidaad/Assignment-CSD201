package Manager;

import BinarySearchTree.BSTreeCustomer;
import Entity.Customer;
import Entity.Node;
import java.io.*;
import java.util.*;

public class CustomerMenu {
    BSTreeCustomer bst = new BSTreeCustomer();
    Validate vld = new Validate();
    public static String CustomerFile = "Customer.txt";

    public BSTreeCustomer getBst() {
        return bst;
    }
    
    public void loadDatafromFile() throws IOException{
        File file = new File(CustomerFile);
        if(!file.exists()){
            file.createNewFile();
        }
        while(true){
            try{
                Scanner read = new Scanner(file);
                if(!read.hasNextLine()){
                    System.out.println("File is empty");
                    read.close();
                    return;
                }
                FileReader fwRead = new FileReader(file);
                BufferedReader br = new BufferedReader(fwRead);
                String line = "";
                while((line = br.readLine()) != null){
                    String text = line.replaceAll("//s+", "");
                    String[] info = text.trim().split("[|]");
                    String ccode = info[0].trim();
                    String cus_name = info[1].trim();
                    String phone = info[2].trim();
                    if(vld.checkPhone(phone)){
                        continue;
                    }
                    else{
                        Customer x = new Customer(ccode, cus_name, phone);
                        bst.insert(x);
                    }
                    
                }
                read.close();
                br.close();
                fwRead.close();
            }catch(FileNotFoundException e){
                continue;
            } catch (IOException e) {  
                continue;
            }
            System.out.println("Load succesfully!");
            break;
        }
    }
    public void input_add(){
        System.out.print("Enter ccode: ");
        String ccode = vld.checkInput();
        System.out.print("Enter cus name: ");
        String cus_name = vld.checkInput();
        System.out.print("Enter phone: ");
        String phone = vld.checkPhoneInput();
        Customer x = new Customer(ccode, cus_name, phone);
        bst.insert(x);
    }
    public void display(){
        bst.breadth(bst.root);
    }
    
    public void BreadthToFile() throws FileNotFoundException, IOException{
        File txt = new File(CustomerFile);
        if(!txt.exists()){
            txt.createNewFile();
        }
        PrintWriter pw = new PrintWriter(txt);
        pw.write("");
        pw.close();
        FileWriter fw = new FileWriter(txt);
        Node p = bst.root;
        Queue<Node> q = new LinkedList<>();
        q.add(p);
        Node r;
        while(!q.isEmpty()){
            r = q.poll();
            fw.write(r.infoCustomer.toString() + "\n");
            if(r.left != null)  q.add(r.left);
            if(r.right != null) q.add(r.right);
            
        }
        fw.close();
        System.out.println("Save successfully!");
    }
    public void searchByCcode(){
        System.out.print("Enter Ccode you want to search: ");
        String xCcode = vld.checkInput();
        Node p = bst.searchByCcode(bst.root, xCcode);
        if(p == null){
            System.out.println("Can not find Pcode in tree");
            return;
        }
        else{
            System.out.println(p.infoCustomer.toString());
        }
    }
    public void delete(){
        System.out.print("Enter Ccode you want to delete: ");
        String delCcode = vld.checkInput();
        bst.delete(delCcode);
        System.out.println("Traversal after delete");
        display();
    }
}
