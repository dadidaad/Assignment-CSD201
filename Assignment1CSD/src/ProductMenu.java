
import java.util.*;
import java.io.*;
public class ProductMenu {
    LinkedListProduct t = new LinkedListProduct();
    Validate vld = new Validate();
    public static Scanner sc = new Scanner(System.in);
    public static String productFile = "Product.txt";

    public LinkedListProduct getT() {
        return this.t;
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
                    if(vld.checkExistPcode(t, pcode) || quantity < saled ){
                        continue;
                    }
                    else{
                        Product x = new Product(pcode, info[1].trim(), quantity, saled, Double.parseDouble(info[4].trim()));
                        t.addLast(x);
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
        while(true){
            try{
                System.out.print("Enter pcode: ");
                String pcode = sc.nextLine();
                if(vld.checkExistPcode(t, pcode) || vld.checkInput(pcode)) throw new Myexception("Pcode has been exist or blank input!!");
                System.out.print("Enter pro name: ");
                String pro_name = sc.nextLine();
                if(vld.checkInput(pro_name)) throw new Myexception("Blank input!! try again");
                System.out.print("Enter quantity: ");
                int quan = Integer.parseInt(sc.nextLine());
                System.out.print("Enter saled: ");
                int saled = Integer.parseInt(sc.nextLine());
                if(saled > quan)    throw new Myexception("Saled must be <= quantity");
                System.out.print("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                Product x = new Product(pcode, pro_name, quan, saled, price);
                t.addLast(x);
            } catch (Myexception ex) {
                System.out.println(ex.getMessage());
                continue;
            } catch(NumberFormatException ex){
                System.out.println("Wrong format!!");
                continue;
            }  
            System.out.println("Add successfully!");
            break;
        }
    }
    public void display(){
        System.out.println("pcode  |  pro_name  |  quantity  |  saled  |  price  |   value");
        t.traverseProduct();
    }
    public void saveProductListToFile() throws FileNotFoundException, IOException{
        File txt = new File(productFile);
        if(!txt.exists()){
            txt.createNewFile();
        }
        PrintWriter pw = new PrintWriter(txt);
        pw.write("");
        pw.close();
        FileWriter fw = new FileWriter(txt);
        Node p = t.head;
        while(p != null){
            fw.write(p.infoProduct.toString()+ "\n");
            p = p.next;
        }
        fw.close();
        System.out.println("Save successfully!");
    }
    public void searchByPcode(){
        while(true){
            try{
                System.out.print("Enter pcode to search: ");
                String pCodeSearch = sc.nextLine();
                if(vld.checkInput(pCodeSearch)) throw new Myexception("Blank input!! Try again");
                Node q = t.searchByPcode(pCodeSearch);
                if(q == null) throw new Myexception("Can not find Pcode in the list");
                else{
                    System.out.println("Pcode you search ");
                    System.out.println("pcode  |  pro_name  |  quantity  |  saled  |  price  |   value");
                    t.visitProduct(q);
                }
            }catch(Myexception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
    }
    public void deleteByPcode(){
        while(true){
            try{
                System.out.println("Enter Pcode to delete: ");
                String pCodeDelete = sc.nextLine();
                if(vld.checkInput(pCodeDelete)) throw new Myexception("Blank input!!");
                if(!vld.checkExistPcode(t, pCodeDelete)) throw new Myexception("Can not found Pcode");
                else{
                    t.deleteByPcode(pCodeDelete);
                }
            } catch(Myexception e){
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Delete successfully");
            break;
        }
    }  
    public void sortByPcode(){
        t.sortByPcode();
        System.out.println("Sort succesfully!!");
    }
    public void addAfterPosK(){
        while(true){
            try{
                System.out.print("Enter position you want to add after: ");
                int pos = Integer.parseInt(sc.nextLine());
                if(pos < 0 || pos > t.size() - 1) throw new Myexception("Pos must be in range " + 0 + "-->" + t.size());
                System.out.print("Enter pcode: ");
                String pcode = sc.nextLine();
                if(vld.checkExistPcode(t, pcode) || vld.checkInput(pcode)) throw new Myexception("Pcode has been exist or blank input!!");
                System.out.print("Enter pro name: ");
                String pro_name = sc.nextLine();
                if(vld.checkInput(pro_name)) throw new Myexception("Blank input!! try again");
                System.out.print("Enter quantity: ");
                int quan = Integer.parseInt(sc.nextLine());
                System.out.print("Enter saled: ");
                int saled = Integer.parseInt(sc.nextLine());
                if(saled > quan)    throw new Myexception("Saled must be <= quantity");
                System.out.print("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                Product x = new Product(pcode, pro_name, quan, saled, price);
                t.addAfterPosK(pos, x);
            }
            catch(Myexception e){
                System.out.println(e.getMessage());
                continue;
            }
            catch(NumberFormatException e){
                System.out.println("Wrong format!!");
                continue;
            }
            System.out.println("Add succesfully!");
            break;
        }
    }
    public void deleteNodeAfterNodehavingCode(){
        while(true){
            try{
                System.out.print("Enter code you want delete node after: ");
                String xPcode = sc.nextLine();
                if(vld.checkInput(xPcode)  ) throw new Myexception("Blank input or can not found code");
                t.deleteNodeAfterNodeHavingXcode(xPcode);
            }catch(Myexception e){
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Delete succesfully");
            break;
        }
    }
    
    
    public void f1() throws IOException{
        t.clear();
        loadData();
        t.traverseProduct();
    }
    public void f2() throws IOException{
        t.clear();
        loadData();
        t.deleteByPcode("2");
        t.traverseProduct();
    }
    public void f3() throws IOException{
        t.clear();
        loadData();
        Node q = t.searchByPcode("3");
        Product x = q.infoProduct;
        x.setQuantity(9);
        q.setInfoProduct(x);
        t.traverseProduct();
    }
    public void f4() throws IOException{
        t.clear();
        loadData();
        Product x = new Product("6","E",3,2,1);
        t.addLast(x);
        t.traverseProduct();
    }
    public void f5() throws IOException{
        t.clear();
        loadData();
        t.sortbysaled();
        t.traverseProduct();
    }
}
