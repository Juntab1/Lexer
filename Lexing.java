import java.util.ArrayList;
import java.util.Arrays;

public class Lexing {
    public static void main(String args[]) {
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
                "main", "for", "class", "\n", "//", "/*", "*/"));
        String ans = "";
        // implementation might be wrong but the idea will be there
        for (int i = 0; i < test.length(); i++) {
            char curr = test.charAt(i);
            if (curr != ' ') {
                ans += test.charAt(i);
            }
            if (singleCharKey.contains(Character.toString(curr))) {
                ans = ans.substring(0, ans.length() - 1);
                if (!ans.equals("")) {
                    System.out.println(ans.replace("\n", "<NewLine>"));
                }
                System.out.println(curr);
                ans = "";
            } else if (singleCharKey.contains(ans)) {
                if ((i + 1) < (test.length() - 1)) {
                    ans += test.charAt(i + 1);
                    if (multiCharKey.contains(ans)) {
                        System.out.println(ans.replace("\n", "<NewLine>"));
                        ans = "";
                        i++;
                    } else {
                        ans = ans.substring(0, ans.length() - 1);
                        System.out.println(ans.replace(" \n", "<NewLine>"));
                        ans = "";
                    }
                } else {
                    System.out.println(ans);
                    ans = "";
                }
            } else if (multiCharKey.contains(ans)) {
                System.out.println(ans.replace("\n", "<NewLine>"));
                ans = "";
            } else if (!ans.isEmpty() && curr == ' ') {
                if (ans.contains("\n")){
                    System.out.println(ans.trim());
                    System.out.println("<NewLine>");
                }
                else{
                    System.out.println(ans);
                }
                ans = "";
            }

            // FIX: later need to add next line case to show as a line of command
        }
    }
}
