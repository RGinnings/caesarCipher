import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class cipher {
    private JButton encrypt;
    private JButton decrypt;
    private JPanel panelMain;
    private JButton quit;
    private JTextPane message;
    private JFormattedTextField num;

    public cipher() {
        buttons();
    }

    private void testForButton() {
        boolean value = !num.getText().trim().isEmpty() && !message.getText().trim().isEmpty() && num.getText().matches("[0-9]+");
        encrypt.setEnabled(value);
        decrypt.setEnabled(value);
    }

    private void buttons() {

        encrypt.setEnabled(false);
        decrypt.setEnabled(false);
        JOptionPane.showMessageDialog(null, "All punctuation will be removed and numbers are not encrypted");

        num.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                testForButton();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                testForButton();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                testForButton();
            }
        });

        encrypt.addActionListener(actionEvent -> {
            String in = message.getText();
            int shifty = Integer.parseInt(num.getText());
            in = in.replaceAll("\\p{Punct}", "");
            StringBuilder encrypted = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                char ch = (char) (int) in.charAt(i);
                if (Character.isWhitespace(in.charAt(i))) {
                    ch = ' ';                 //keeps space from initial text
                    encrypted.append(ch);
                } else if (Character.isDigit(in.charAt(i))) {
                    ch = in.charAt(i);
                    encrypted.append(ch);
                } else if (Character.isUpperCase(in.charAt(i))) {
                    ch = (char) ((ch + shifty - 65) % 26 + 65);    //keeps uppercase when cipher is complete
                    encrypted.append(ch);
                } else {
                    ch = (char) ((ch + shifty - 97) % 26 + 97);    //lowercase cipher
                    encrypted.append(ch);
                }
            }
            message.setText("");
            message.setText(encrypted.toString());
        });

        decrypt.addActionListener(actionEvent -> {
            String in = message.getText();
            int shifty = Integer.parseInt(num.getText());
            StringBuilder decrypted = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                char ch = in.charAt(i);
                if (!Character.isWhitespace(ch)) {
                    if (Character.isDigit(ch)) {

                        decrypted.append(ch);
                    } else if (Character.isUpperCase(ch)) {
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
            message.setText("");
            message.setText(decrypted.toString());
        });
        quit.addActionListener(actionEvent -> System.exit(0));
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(cipher.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("cipher");
            frame.setContentPane(new cipher().panelMain);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
