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

public class PlayFairWindow extends JFrame {
	
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
                    PlayFairWindow frame = new PlayFairWindow();
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
    public PlayFairWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 539, 639);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_Caesar = new JLabel("PLAYFAIR CIPHER");
        lbl_Caesar.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Caesar.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lbl_Caesar.setBounds(0, 11, 476, 53);
        contentPane.add(lbl_Caesar);

        JTextArea txt_Plain = new JTextArea();
        txt_Plain.setBounds(143, 75, 296, 105);
        contentPane.add(txt_Plain);

        JButton btn_Encrypt = new JButton("Encrypt");
        btn_Encrypt.setBounds(51, 513, 106, 43);
        contentPane.add(btn_Encrypt);

        JLabel lbl_Plain_Text = new JLabel("Plain text:");
        lbl_Plain_Text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_Plain_Text.setBounds(10, 109, 79, 14);
        contentPane.add(lbl_Plain_Text);

        JLabel lbl_ciphered_Text = new JLabel("Ciphered text:");
        lbl_ciphered_Text.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_ciphered_Text.setBounds(10, 455, 106, 19);
        contentPane.add(lbl_ciphered_Text);

        JTextArea txt_Ciphered = new JTextArea();
        txt_Ciphered.setBounds(143, 397, 296, 105);
        contentPane.add(txt_Ciphered);

        JButton btn_Decrypt = new JButton("Decrypt");
        btn_Decrypt.setBounds(208, 513, 106, 43);
        contentPane.add(btn_Decrypt);

        JButton btn_OpenFile = new JButton("Open Cipheredtext file");
        btn_OpenFile.setBounds(366, 513, 149, 43);
        contentPane.add(btn_OpenFile);

        JLabel lbl_Key = new JLabel("Key:");
        lbl_Key.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_Key.setBounds(10, 201, 79, 26);
        contentPane.add(lbl_Key);

        JTextArea txt_Key = new JTextArea();
        txt_Key.setBounds(143, 201, 296, 31);
        contentPane.add(txt_Key);
        
        JLabel lbl_Key_table = new JLabel("Key table");
        lbl_Key_table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_Key_table.setBounds(10, 300, 79, 26);
        contentPane.add(lbl_Key_table);
        
        JTextArea KeyTable = new JTextArea();
        KeyTable.setText("(none)");
        KeyTable.setFont(new Font("Courier New", Font.PLAIN, 20));
        KeyTable.setEditable(false);
        KeyTable.setBounds(143, 256, 296, 130);
        contentPane.add(KeyTable);
        
        JButton btnKeyTable = new JButton("Generate Key Table");
        
        btnKeyTable.setBounds(179, 559, 149, 31);
        contentPane.add(btnKeyTable);

        //action buttons
        //Encrypt button pressed
        btn_Encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String plaintext = txt_Plain.getText().toLowerCase();
                    String key = (txt_Key.getText()).toLowerCase();
                    String ciphered = PlayFairCipher.encrypt(plaintext, key);
                    txt_Ciphered.setText(ciphered);
                    KeyTable.setText(PlayFairCipher.returnTable(key));
                    JOptionPane.showMessageDialog(null, "Sucesfully Encrypted");
                    saveToFile(ciphered);
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null, "Invalid Key!");
                } catch (StringIndexOutOfBoundsException error) {
                	JOptionPane.showMessageDialog(null, "Please input plain message");
                }
            }
        });
        
        //decrypt button pressed
        btn_Decrypt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
                    String enText = txt_Ciphered.getText().toLowerCase();
                    String key = txt_Key.getText().toLowerCase();
                    KeyTable.setText(PlayFairCipher.returnTable(key));
                    String deciphered = PlayFairCipher.decrypt(enText, key);
                    txt_Plain.setText(deciphered);
                    JOptionPane.showMessageDialog(null, "Sucesfully Decrypted");
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null, "Invalid Key!");
                } catch (StringIndexOutOfBoundsException error) {
                	String enText = txt_Ciphered.getText();
                	String message = "Please input the encrypted message";
                	if (enText.length() % 2 != 0) {
                		message = "Encrypted message's length must be an even number!";
                	}
                	JOptionPane.showMessageDialog(null, message);
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
        
        btnKeyTable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String key = (txt_Key.getText());
        		KeyTable.setText(PlayFairCipher.returnTable(key));
        		JOptionPane.showMessageDialog(null, "Key table generated");
        	}
        });
        
    }
}
