public class Lexing{
    public static void main(String args[]){
        String test = "Hello World";
        StringBuilder ans = new StringBuilder();
        int index = 1;
        for(char c : test.toCharArray()){
            ans.append("Char " + index + ": " + c + "\n");
        }
        System.out.println(ans.toString());
    }
}   
