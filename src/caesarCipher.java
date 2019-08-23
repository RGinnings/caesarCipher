
import java.time.LocalTime;
import java.util.Scanner;

class caesarCipher 
{ 
    public static String encrypt(String sc, int shift) 
    { 
        String encrypted= ""; 
        for (int i=0; i<sc.length(); i++) 
        { 
            char ch = (char)(int)sc.charAt(i);
            if (Character.isWhitespace(sc.charAt(i))) 
            { 
                ch = ' ';                 //keeps space from initial text
                encrypted+=ch; 
            } 
            else if (Character.isUpperCase(sc.charAt(i))) 
            { 
                ch = (char)((ch + shift - 65) % 26 + 65);    //keeps uppercase when cipher is complete
                encrypted+=ch; 
            } 
            else 
            {
                ch = (char)((ch + shift - 97) % 26 + 97);    //lowercase cipher
                encrypted+=ch; 
            }
        } 
        return encrypted; 
    }
    public static String decrypt(String encrypted, int shift) 
    {
        String decrypted = "";
        for (int i = 0; i < encrypted.length(); i++) 
        {
            char ch = encrypted.charAt(i);
            
            if (Character.isWhitespace(ch)) 
            {
                decrypted += ch;
            }
            else if (Character.isUpperCase(ch)) 
            {
                int temp = (ch - shift - 65) % 26;
                if (temp < 0) {
                    temp += 26;
                }
                ch = (char)(temp + 65);
                decrypted += ch;
            }
            else
                {
                int temp = (ch - shift - 97) % 26;
                if (temp < 0) {
                    temp += 26;
                }
                ch = (char)(temp + 97);    
                decrypted += ch;
            }
        }
        return decrypted;
        
    }
 
    public static void main(String[] args) 
    { 
        System.out.println("Enter text to cypher NO NUMBERS: ");
        Scanner input = new Scanner(System.in);
        String sc = input.nextLine();
        LocalTime time = LocalTime.now();
        int shift = time.getNano(); 
        sc = sc.replaceAll("\\p{Punct}","");
        System.out.println("Text  : " + sc); 
        System.out.println("Shift : " + shift); 
        System.out.println("Cipher: " + encrypt(sc, shift));
        System.out.println("Decrypted text: " + decrypt(encrypt(sc, shift), shift));
    } 
} 