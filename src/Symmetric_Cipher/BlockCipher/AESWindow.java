package Symmetric_Cipher.BlockCipher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AESWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextField txtUsername;
	private JPasswordField passwordField;
	private final JTextField txt_Plain;
	private final JTextField textCipher;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	
	private final String REGISTRATION_FILE = "registration.key";
	private boolean isLoggedIn = false;
	private JTextField txt_ticket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AESWindow frame = new AESWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void updateButtonState() {
		this.btnEncrypt.setEnabled(isLoggedIn);
		this.btnDecrypt.setEnabled(isLoggedIn);
	}

	/**
	 * Create the frame.
	 */
	public AESWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTITLE = new JLabel("AES CIPHER");
		lblTITLE.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblTITLE.setHorizontalAlignment(SwingConstants.CENTER);
		lblTITLE.setBounds(10, 0, 454, 43);
		contentPane.add(lblTITLE);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsername.setBounds(20, 54, 81, 24);
		contentPane.add(lblUsername);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPass.setBounds(20, 105, 81, 24);
		contentPane.add(lblPass);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(111, 54, 342, 32);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(111, 106, 342, 24);
		contentPane.add(passwordField);
		
		JLabel lblTicket = new JLabel("Ticket");
		lblTicket.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTicket.setBounds(20, 154, 81, 24);
		contentPane.add(lblTicket);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setBounds(75, 205, 89, 32);
		contentPane.add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(337, 205, 89, 32);
		contentPane.add(btnLogin);
		
		txt_Plain = new JTextField();
		txt_Plain.setBounds(111, 248, 342, 129);
		contentPane.add(txt_Plain);
		txt_Plain.setColumns(10);
		
		JLabel lblPlain = new JLabel("Plain text:");
		lblPlain.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPlain.setBounds(20, 297, 81, 24);
		contentPane.add(lblPlain);
		
		JLabel lblCipher = new JLabel("Ciphertext:");
		lblCipher.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCipher.setBounds(10, 447, 91, 24);
		contentPane.add(lblCipher);
		
		textCipher = new JTextField();
		textCipher.setColumns(10);
		textCipher.setBounds(111, 398, 342, 129);
		contentPane.add(textCipher);
		
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setBounds(111, 538, 107, 32);
		contentPane.add(btnEncrypt);
		
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setBounds(346, 538, 107, 32);
		contentPane.add(btnDecrypt);
		
		txt_ticket = new JTextField();
		txt_ticket.setEditable(false);
		txt_ticket.setBounds(111, 153, 342, 32);
		contentPane.add(txt_ticket);
		txt_ticket.setColumns(10);
		
		updateButtonState();
		
		//action buttons		
		//register
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String username = txtUsername.getText();
				String password = new String (passwordField.getPassword());
				
				if (password.length() < 24) {
					JOptionPane.showMessageDialog(null, "Password must be longer than 23 in length", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (!username.isEmpty() && !password.isEmpty()) {
					try {
						String registrationKey = AESCipher.generateRegistrationKey(username, password);
						AESCipher.SaveRegistrationKeytoFIle(registrationKey, REGISTRATION_FILE);
						JOptionPane.showMessageDialog(null, "Registration Successful. Registration Key saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException IOE) {
						JOptionPane.showMessageDialog(null, "Error saving registration key: " + IOE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//login button
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String (passwordField.getPassword());
				
				if (password.length() < 24) {
					JOptionPane.showMessageDialog(null, "Password must be longer than 23 in length", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (!username.isEmpty() && !password.isEmpty()) {
					try {
						String registrationKeyFromFile = AESCipher.readRegistrationKeyFromFile(REGISTRATION_FILE);
						String registrationKey = AESCipher.generateRegistrationKey(username, password);
						
						if (registrationKeyFromFile.equals(registrationKey)) {
							JOptionPane.showMessageDialog(null, "Login Successful with Registration key" + registrationKey, "Success", JOptionPane.INFORMATION_MESSAGE);
							txt_ticket.setText(registrationKey);
							isLoggedIn = true;
							updateButtonState();
						}
					} catch (IOException | ClassNotFoundException ice) {
						JOptionPane.showMessageDialog(null,  "Error reading registration key: " + ice.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//encrypt button
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plain = txt_Plain.getText();
				String key = new String(passwordField.getPassword());
				
				if (!plain.isEmpty() && !key.isEmpty()) {
					try {
						String encrypted = AESCipher.encrypt(plain, key);
						textCipher.setText(encrypted);
						
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error encrypting text: " + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Text and key cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//Decrypt button
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textCipher.getText();
				String key = new String(passwordField.getPassword());
				
				if (!text.isEmpty() && !key.isEmpty()) {
					try {
						String decrypted = AESCipher.decrypt(text, key);
						txt_Plain.setText(decrypted);
						
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error decrypting text: " + ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Text and key cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
