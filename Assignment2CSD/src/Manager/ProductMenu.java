package Manager;

import BinarySearchTree.BSTreeProduct;
import Entity.Node;
import Entity.Product;
import java.io.*;
import java.util.*;

public class ProductMenu {
    BSTreeProduct bst = new BSTreeProduct();
    public static Scanner sc = new Scanner(System.in);
    public static String productFile = "Product.txt";
    Validate vld = new Validate();

    public BSTreeProduct getBst() {
        return bst;
    }
    
    public void loadData() throws IOException {
        File file = new File(productFile);
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
                    String pcode = info[0].trim();
                    int quantity = Integer.parseInt(info[2].trim());
                    int saled = Integer.parseInt(info[3].trim());
                    if(quantity < saled ){
                        continue;
                    }
                    else{
                        Product x = new Product(pcode, info[1].trim(), quantity, saled, Double.parseDouble(info[4].trim()));
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
            System.out.println();
            break;
        }    
    }
    
    public void input_add(){
        System.out.print("Enter pcode: ");
        String pcode = vld.checkInput();
        System.out.print("Enter pro name: ");
        String pro_name = vld.checkInput();
        int quan, saled;
        while(true){
            try{
                System.out.print("Enter quantity: ");
                quan = vld.checkNumber();
                System.out.print("Enter saled: ");
                saled = vld.checkNumber();
                if(saled > quan)    throw new MyException("Saled must be <= quantity");
            }
            catch(MyException e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        System.out.print("Enter price: ");
        double price = vld.checkNumber();
        Product x = new Product(pcode, pro_name, quan, saled, price);
        bst.insert(x);
    }
    public void inOrder(){
        bst.inOrder(bst.root);
    }
    public void breadth(){
        bst.breadth(bst.root);
    }
    public void inOrderToFile() throws FileNotFoundException, IOException{
        File txt = new File(productFile);
        if(!txt.exists()){
            txt.createNewFile();
        }
        PrintWriter pw = new PrintWriter(txt);
        pw.write("");
        pw.close();
        FileWriter fw = new FileWriter(txt);
        Node p = bst.root;
        Stack<Node> st = new Stack();
        while(!st.empty() || p != null){
            if(p != null){
                st.push(p);
                p = p.left;
            }
            else{
                p = st.pop();
                fw.write(p.infoProduct.toString() + "\n");
                p = p.right;
            }
        }
        fw.close();
        System.out.println("Save successfully!");
    }
    public void searchByPcode(){
        System.out.print("Enter Pcode you want to search: ");
        String xPcode = vld.checkInput();
        Node p = bst.searchByPcode(bst.root, xPcode);
        if(p == null){
            System.out.println("Can not find Pcode in tree");
            return;
        }
        else{
            System.out.println(p.infoProduct.toString());
        }
    }
    public void delete(){
        System.out.print("Enter Pcode you want to delete: ");
        String delPcode = vld.checkInput();
        bst.delete(delPcode);
        System.out.println("Traversal after delete");
        breadth();
    }
    public void simpleBalance(){
        bst.balanceTree();
        breadth();
    }
    public void countProduct(){
        System.out.println("The Number of Product is "+ bst.count(bst.root));
    }
    
    public void f1() throws IOException{
        bst.clear();
        loadData();
        breadth();
        System.out.println();
        inOrder();
    }
    public void f2() throws IOException{
        bst.clear();
        loadData();
        Node q = bst.searchByPcode(bst.root, "6");
        Product x = q.infoProduct;
        x.setQuantity(9);
        q.setInfoProduct(x);
        breadth();
    }
    public void f3() throws IOException{
        bst.clear();
        loadData();
        bst.delete("2");
        breadth();
    }
    public void f4() throws IOException{
        bst.clear();
        loadData();
        bst.insert(new Product("7", "G", 3, 2, 1));
        breadth();
    }
    public void f5() throws IOException{
        System.out.println("F5");
        bst.clear();
        loadData();
        bst.balance();
        breadth();
        bst.clear();
        loadData();
        bst.balanceTree();
        breadth();
    }
}
