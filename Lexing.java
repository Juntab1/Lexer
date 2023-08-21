import java.util.ArrayList;
import java.util.Arrays;

public class Lexing {
    public static void main(String args[]) {
        String oneLine = "Hello";
        String test = """
                    public class Test {
                        // main function
                        public static void main(String args[]) {
                           int [] numbers = {10, 20, 30, 40, 50};

                           for(int x : numbers ) {
                              System.out.print( x );
                              System.out.print(",");
                           }
                           /* printing new line
                           */
                           System.out.print("\n");
                           String [] names = {"James", "Larry", "Tom", "Lacy"};

                           for( String name : names ) {
                              System.out.print( name );
                              System.out.print(",");
                           }
                        }
                     }
                """;

        // single-char keywords: [{, (, ), {, }, ;, ., ", *, \, ,]
        // multi-char keywords: [String, int, public, static, void, main, for, class]
        // %did not consider "System" because it is an user defined name%
        // whitespace rule stays as because source code is technically pieces of strings
        ArrayList<String> singleCharKey = new ArrayList<>(
                Arrays.asList("(", ")", "{", "}", ";", ".", "\"", ";", "*", "\\", ",", ":", "[", "]"));
        ArrayList<String> multiCharKey = new ArrayList<>(Arrays.asList("String", "int", "public", "static", "void",
                "main", "for", "class", "//", "/*", "*/"));
        String ans = "";
        int currLength = oneLine.length();
        String currString = oneLine;

        for (int i = 0; i < currLength; i++) {
            char curr = currString.charAt(i);
            if (curr != ' ') {
                ans += curr;
            }
            // special case for checking if "*" is considered a single char or a multi also covers comments single/multi line
            if ((ans.equals("*")) || (ans.equals("/")) && (i + 1) < (currLength - 1)) {
                if (currString.charAt(i+1) == '/' || currString.charAt(i+1) == '*'){
                    ans += currString.charAt(i+1);
                    System.out.println("Comment: " + ans.replace("\n", "<New Line>"));
                    ans = "";
                    i++;
                }
            }
            else if (singleCharKey.contains(Character.toString(curr))) {
                ans = ans.substring(0, ans.length() - 1);
                if (!ans.equals("")) {
                    if (ans.contains("\n")){
                        System.out.println("String: " + ans.trim());
                        System.out.println("<New Line>");
                    }
                    else{
                        System.out.println("String: " + ans);
                    }
                }
                System.out.println("Char: " + curr);
                ans = "";
            } else if (singleCharKey.contains(ans)) {
                if ((i + 1) < (currLength - 1)) {
                    ans += currString.charAt(i + 1);
                    if (multiCharKey.contains(ans)) {
                        if (ans.contains("\n")){
                            System.out.println("String: " + ans.trim());
                            System.out.println("<New Line>");
                        }
                        else{
                            System.out.println("String: " + ans);
                        }
                        i++;
                    } else {
                        ans = ans.substring(0, ans.length() - 1);
                        System.out.println("Char: " + ans.replace("\n", "<New Line>"));
                    }
                } 
                else {
                    System.out.println("Char: " + ans);
                }
                ans = "";
            } else if (multiCharKey.contains(ans)) {
                System.out.println("String: " + ans.replace("\n", "<New Line>"));
                ans = "";
            } else if ((!ans.isEmpty() && curr == ' ') || ans.length() == currLength) {
                // first if case needed becuasee \n was being counted as a string 
                if (ans.equals("\n")){
                    System.out.println("<New Line>");
                }
                else if (ans.contains("\n")){
                    System.out.println("String: " + ans.trim());
                    System.out.println("<New Line>");
                }
                else{
                    System.out.println("String: " + ans);
                }
                ans = "";
            }
        }
    }
}
