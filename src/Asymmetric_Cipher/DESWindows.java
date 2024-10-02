package Asymmetric_Cipher;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DESWindows extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	DESWindows frame = new DESWindows();
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
    public DESWindows() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 706, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_Caesar = new JLabel("DES CIPHER");
        lbl_Caesar.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Caesar.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lbl_Caesar.setBounds(10, 11, 672, 53);
        contentPane.add(lbl_Caesar);

        JTextArea txt_Plain = new JTextArea();
        txt_Plain.setBounds(143, 75, 484, 105);
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
        txt_Ciphered.setBounds(143, 272, 484, 105);
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
        txt_Key.setBounds(143, 201, 484, 31);
        contentPane.add(txt_Key);
        
        JButton btnSavetoFile = new JButton("Save to FIle");
        btnSavetoFile.setBounds(506, 388, 121, 43);
        contentPane.add(btnSavetoFile);
        
        JLabel lblNewLabel = new JLabel("*Key's length must be 8 (strings that are longer than 8 will be cut down to 8)");
        lblNewLabel.setForeground(Color.GRAY);
        lblNewLabel.setBounds(143, 243, 484, 14);
        contentPane.add(lblNewLabel);


        //action buttons
        //Encrypt button pressed
        btn_Encrypt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String plaintext = txt_Plain.getText();
                    String key = txt_Key.getText();
                    if (key.length() < 8) {
                    	Integer.parseInt("a");
                    }
                    String ciphered = DESCipher.encrypt(plaintext, key);
                    txt_Ciphered.setText(ciphered);
                    JOptionPane.showMessageDialog(null, "Sucesfully Encrypted");
                } catch (NumberFormatException NFE) {
                	JOptionPane.showMessageDialog(null, "Key length must be 8 characters long"); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                }
            }
        });
        
        //decrypt button pressed
        btn_Decrypt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try{
                    String plaintext = txt_Ciphered.getText();
                    String key = txt_Key.getText();
                    if (key.length() < 8) {
                    	Integer.parseInt("a");
                    }
                    String ciphered = DESCipher.decrypt(plaintext, key);
                    txt_Plain.setText(ciphered);
                    JOptionPane.showMessageDialog(null, "Sucesfully Decrypted");
                } catch (NumberFormatException NFE) {
                	JOptionPane.showMessageDialog(null, "Key length must be 8 characters long");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                } 
        		
        	}
        });
        
        //open file button
        btn_OpenFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser fileChooser = new JFileChooser();
        		fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        		fileChooser.setDialogTitle("Open file containing ciphered text");
        		
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
        
        //save to file button
        btnSavetoFile.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser fileChooser = new JFileChooser();
        		fileChooser.setDialogTitle("Save cipher text to File");
        		int userSelection = fileChooser.showSaveDialog(null);
        		if (userSelection == JFileChooser.APPROVE_OPTION) {
        			File filetosave = fileChooser.getSelectedFile();
        			String newname = filetosave.getAbsolutePath();
        			int len = newname.length();
        			if (!newname.substring(len - 4, len).equals(".txt")) {
        				newname += ".txt";
        			}
        			try (FileWriter writer = new FileWriter(newname)){
        				writer.write(txt_Ciphered.getText());
        				JOptionPane.showMessageDialog(null, "Ciphertext saved to file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        			} catch (IOException ioe) {
        				JOptionPane.showMessageDialog(null, "Error saving file: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        			}
        		}
        	}
        });
        
    }
}
