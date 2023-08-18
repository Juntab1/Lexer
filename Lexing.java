public class Lexing{
    public static void main(String args[]){
        String test = "Hello World";
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < test.length(); i++){
            char curr = test.charAt(i);
            if (curr == ' '){
                System.out.println(ans.toString());
                ans.delete(0, ans.length());
            }
            else{
                ans.append(curr);
            }
        }
        System.out.println(ans.toString());
    }
}   
