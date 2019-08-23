import javax.swing.*;


public class cipher {
    private JButton encrypt;
    private JButton decrypt;
    private JPanel panelMain;

    public cipher() {
        JOptionPane.showMessageDialog(null, "No numbers and all punctuation is lost");
        encrypt.addActionListener(actionEvent -> {
            String in = JOptionPane.showInputDialog("Please enter your text here");
            String shift = JOptionPane.showInputDialog("Please Enter an encryption number here");
            int shifty = Integer.parseInt(shift);
            in = in.replaceAll("\\p{Punct}", "");
            StringBuilder encrypted = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                char ch = (char) (int) in.charAt(i);
                if (Character.isWhitespace(in.charAt(i))) {
                    ch = ' ';                 //keeps space from initial text
                    encrypted.append(ch);
                } else if (Character.isUpperCase(in.charAt(i))) {
                    ch = (char) ((ch + shifty - 65) % 26 + 65);    //keeps uppercase when cipher is complete
                    encrypted.append(ch);
                } else {
                    ch = (char) ((ch + shifty - 97) % 26 + 97);    //lowercase cipher
                    encrypted.append(ch);
                }
            }

            JOptionPane.showMessageDialog(null, encrypted.toString());
        });
        decrypt.addActionListener(actionEvent -> {
            String in = JOptionPane.showInputDialog("Please enter encrypted text here");
            String shift = JOptionPane.showInputDialog("Please Enter the decryption number here");
            int shifty = Integer.parseInt(shift);
            in = in.replaceAll("\\p{Punct}", "");
            String encrypted = in;
            StringBuilder decrypted = new StringBuilder();
            for (int i = 0; i < encrypted.length(); i++) {
                char ch = encrypted.charAt(i);

                if (!Character.isWhitespace(ch)) {
                    if (Character.isUpperCase(ch)) {
                        int temp = (ch - shifty - 65) % 26;
                        if (temp < 0) {
                            temp += 26;
                        }
                        ch = (char) (temp + 65);
                        decrypted.append(ch);
                    } else {
                        int temp = (ch - shifty - 97) % 26;
                        if (temp < 0) {
                            temp += 26;
                        }
                        ch = (char) (temp + 97);
                        decrypted.append(ch);
                    }
                } else {
                    decrypted.append(ch);
                }
            }
            JOptionPane.showMessageDialog(null, decrypted.toString());


        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("cipher");
        frame.setContentPane(new cipher().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}