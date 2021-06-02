import java.util.*;
/**
 *
 * @author Vo Thanh Dat 
 */
public class Main {
    public static Scanner sc = new Scanner(System.in);
    
    public boolean checkParentheses(String str){
        Stack<Character> st = new Stack<>();
        List<Character> list = new ArrayList<>();
        list.add('(');
        list.add(')');
        list.add('{');
        list.add('}');
        list.add('[');
        list.add(']');
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(list.contains(c)){
                if(c == '('){
                    st.push(c);
                }
                else if(c == '{'){
                    st.push(c);
                }
                else if(c == '['){
                    st.push(c);
                }
                else if(c == ']'){
                    if(st.empty()){
                        return false;
                    }
                    else if(st.peek() == '['){
                        st.pop();
                    }
                    else
                        return false;
                }
                else if(c == '}'){
                    if(st.empty()){
                        return false;
                    }
                    else if(st.peek() == '{'){
                        st.pop();
                    }
                    else{
                      return false;
                    }
                }
                else if(c == ')'){
                    if(st.empty()){
                        return false;
                    }
                    else if(st.peek() == '('){
                        st.pop();
                    }
                    else
                        return false;
                }
            }
        }   
        return st.empty();
    }
    public static void main(String[] args) {
        Main m = new Main();
        System.out.print("Enter String: ");
        String str = sc.nextLine();
        System.out.println(m.checkParentheses(str) ? "Valid expression" : "Invalid expression");
        
    }

}
