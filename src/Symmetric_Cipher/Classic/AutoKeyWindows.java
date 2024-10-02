package Symmetric_Cipher.Classic;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AutoKeyWindows extends JFrame {
	
	private void saveToFile(String content) {
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		filechooser.setFileFilter(filter);
		int userSelection = filechooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try (FileWriter filewriter = new FileWriter(filechooser.getSelectedFile() + ".txt")) {
				filewriter.write(content);
				JOptionPane.showMessageDialog(this, "File saved");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
			}
		}
	}

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	AutoKeyWindows frame = new AutoKeyWindows();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AutoKeyWindows() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_Caesar = new JLabel("AUTOKEY CIPHER");
        lbl_Caesar.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Caesar.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lbl_Caesar.setBounds(10, 11, 466, 53);
        contentPane.add(lbl_Caesar);

        JTextArea txt_Plain = new JTextArea();
        txt_Plain.setBounds(143, 75, 296, 105);
        contentPane.add(txt_Plain);

        JButton btn_Encrypt = new JButton("Encrypt");
        btn_Encrypt.setBounds(51, 388, 106, 43);
        contentPane.add(btn_Encrypt);

        JLabel lbl_Plain_Text = new JLabel("Plain text:");
        lbl_Plain_Text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_Plain_Text.setBounds(10, 109, 79, 14);
        contentPane.add(lbl_Plain_Text);

        JLabel lbl_ciphered_Text = new JLabel("Ciphered text:");
        lbl_ciphered_Text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_ciphered_Text.setBounds(10, 276, 106, 19);
        contentPane.add(lbl_ciphered_Text);

        JTextArea txt_Ciphered = new JTextArea();
        txt_Ciphered.setBounds(143, 246, 296, 105);
        contentPane.add(txt_Ciphered);

        JButton btn_Decrypt = new JButton("Decrypt");
        btn_Decrypt.setBounds(191, 388, 106, 43);
        contentPane.add(btn_Decrypt);

        JButton btn_OpenFile = new JButton("Open Cipheredtext file");
        btn_OpenFile.setBounds(327, 388, 149, 43);
        contentPane.add(btn_OpenFile);

        JLabel lbl_Key = new JLabel("Key:");
        lbl_Key.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_Key.setBounds(10, 201, 79, 26);
        contentPane.add(lbl_Key);

        JTextArea txt_Key = new JTextArea();
        txt_Key.setBounds(143, 201, 296, 31);
        contentPane.add(txt_Key);


        //action buttons
        //Encrypt button pressed
        btn_Encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String plaintext = txt_Plain.getText().toLowerCase();
                    String key = txt_Key.getText().toLowerCase();
                    if (key.length() == 0) {
                    	Integer.parseInt("a");
                    }
                    for (int i = 0; i < key.length(); i++) {
                    	if (key.charAt(i) < 'a' || key.charAt(i) > 'z') {
                    		Integer.parseInt("a");
                    	}
                    }
                    String ciphered = AutoKeyCipher.encrypt(plaintext, key);
                    txt_Ciphered.setText(ciphered);
                    JOptionPane.showMessageDialog(null, "Sucesfully Encrypted");
                    saveToFile(ciphered);
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null, "Invalid Key!");
                }
            }
        });
        
        //decrypt button pressed
        btn_Decrypt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
                    String enText = txt_Ciphered.getText().toLowerCase();
                    String key = txt_Key.getText().toLowerCase();
                    if (key.length() == 0) {
                    	Integer.parseInt("a");
                    }
                    for (int i = 0; i < key.length(); i++) {
                    	if (key.charAt(i) < 'a' || key.charAt(i) > 'z') {
                    		Integer.parseInt("a");
                    	}
                    }
                    String deciphered = AutoKeyCipher.decrypt(enText, key);
                    txt_Plain.setText(deciphered);
                    JOptionPane.showMessageDialog(null, "Sucesfully Decrypted");
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null, "Invalid Key!");
                }
        	}
        });
        
        //openfile button
        btn_OpenFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser fileChooser = new JFileChooser();
        		fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        		
        		int userSelection = fileChooser.showOpenDialog(null);
        		if (userSelection == JFileChooser.APPROVE_OPTION) {
        			try (BufferedReader buffer = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))){
        				JOptionPane.showMessageDialog(null, "File opened succesfully!");
        				txt_Ciphered.read(buffer, null);
        			} catch (IOException er) {
        				JOptionPane.showMessageDialog(null, "Error opening file: " + er.getMessage());
        			}
        		}
        	}
        });
        
    }
}
