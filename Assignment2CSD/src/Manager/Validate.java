package Manager;

import BinarySearchTree.BSTreeProduct;
import Entity.Node;
import static Manager.ProductMenu.sc;
import java.util.regex.Pattern;

public class Validate {
    public boolean checkQuantityinOrdering(BSTreeProduct t, String pcode, int qua){
        Node q = t.searchByPcode(t.root, pcode);
        if(q.infoProduct.getSaled() == qua){
            return true;
        }
        return false;
    }
    public boolean checkQuantityInput(BSTreeProduct t, String pcode, int qua){
        Node q = t.searchByPcode(t.root, pcode);
        if(qua <= (q.infoProduct.getQuantity() - q.infoProduct.getSaled())){
            return true;
        }
        return false;
    }
    public String checkInput(){
        String str;
        while(true){
            str = sc.nextLine();
            Pattern p = Pattern.compile("^(([A-Za-z0-9][ ]?|[a-z0-9])+)$");
            if(!p.matcher(str).find()){
                System.out.print("Invalid!!" +"\nRe-input:");
                continue;
            }
            break;
        }
        return str;
    }
    public boolean checkPhone(String str){
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
    public String checkPhoneInput(){
        String str;
        while(true){
            try{
                str = sc.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    if(!Character.isDigit(str.charAt(i))) throw new MyException("Must be digit only");
                }
            }
            catch(MyException e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        return str;
    }
    public int checkNumber(){
        int number;
        while(true){
            try{
                number = Integer.parseInt(sc.nextLine());
                if(number < 0) throw new MyException("Must greater than 0");
            }
            catch(NumberFormatException e){
                System.out.println("Wrong format!!");
                continue;
            }
            catch(MyException e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        return number;
    }
    public String checkEgency(){
        String choice;
        while(true){
            choice = sc.nextLine().toUpperCase().trim();
            if(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No")){
                break;
            }
        }
        return choice;
    }
}
