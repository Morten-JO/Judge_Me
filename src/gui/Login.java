package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class Login {

	private JFrame frame;
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private static JPanel panel;
	private JTextField textField1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 600);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		
		JLabel headline = new JLabel("Create new account");
		headline.setFont(GuiData.getHeadlineFont());
		headline.setSize(headline.getPreferredSize());
		headline.setBounds((panel.getWidth() - headline.getWidth())/2, 30, headline.getWidth(), headline.getHeight());
		panel.add(headline);
		
		JLabel newUsername = new JLabel("Username:");
		newUsername.setFont(GuiData.getUserPassFont());
		newUsername.setSize(newUsername.getPreferredSize());
		newUsername.setBounds(40, 100, newUsername.getWidth(), newUsername.getHeight());
		panel.add(newUsername);
		
		JLabel newPassword = new JLabel("Password:");
		newPassword.setFont(GuiData.getUserPassFont());
		newPassword.setSize(newPassword.getPreferredSize());
		newPassword.setBounds(newUsername.getX(), newUsername.getY() + GuiData.getY(), newPassword.getWidth(), newPassword.getHeight());
		panel.add(newPassword);
		
		JLabel newPassword2 = new JLabel("Confirm Password:");		
		newPassword2.setFont(GuiData.getUserPassFont());
		newPassword2.setSize(newPassword2.getPreferredSize());
		newPassword2.setBounds(newUsername.getX(), newPassword.getY() + GuiData.getY(), newPassword2.getWidth(), newPassword2.getHeight());
		panel.add(newPassword2);
		
		JLabel email = new JLabel("Email:");
		email.setFont(GuiData.getUserPassFont());
		email.setSize(email.getPreferredSize());
		email.setBounds(newUsername.getX(), newPassword2.getY() + GuiData.getY(), email.getWidth(), email.getHeight());
		panel.add(email);
		
		JLabel confirmEmail = new JLabel("Confirm Email:");
		confirmEmail.setFont(GuiData.getUserPassFont());
		confirmEmail.setSize(confirmEmail.getPreferredSize());
		confirmEmail.setBounds(newUsername.getX(), email.getY() + GuiData.getY(), confirmEmail.getWidth(), confirmEmail.getHeight());
		panel.add(confirmEmail);
		
		JLabel Age = new JLabel("Age:");
		Age.setFont(GuiData.getUserPassFont());
		Age.setSize(Age.getPreferredSize());
		Age.setBounds(newUsername.getX(), confirmEmail.getY() + GuiData.getY(), Age.getWidth(), Age.getHeight());
		panel.add(Age);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Do you accept our policy?");
		chckbxNewCheckBox.setBounds(newUsername.getX(), Age.getY() + 40, 204, 23);
		panel.add(chckbxNewCheckBox);
		
		textField1 = new JTextField();
		textField1.setBounds(210, 95, 204, 30);
		panel.add(textField1);
		textField1.setColumns(10);
		
		

		panel.setVisible(false);
		
		
		
		
		
		
		
		
		
		
//		THIS IS THE FIRST GUI
		JLabel username = new JLabel("Username: ");
		username.setFont(GuiData.getUserPassFont());
		username.setSize(username.getPreferredSize());
		username.setBounds(60,70, username.getWidth(), username.getHeight());
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password: ");
		password.setFont(GuiData.getUserPassFont());
		password.setSize(password.getPreferredSize());
		password.setBounds(username.getX(), username.getY() + username.getHeight() + 30, password.getWidth(), password.getHeight());
		frame.getContentPane().add(password);
		
		usernameTxtField = new JTextField();
		usernameTxtField.setBounds(username.getX() + username.getWidth() + 20, username.getY() - 10, 200, 40);
		frame.getContentPane().add(usernameTxtField);
		usernameTxtField.setColumns(10);
		
		passwordTxtField = new JPasswordField();
		passwordTxtField.setBounds(password.getX() + username.getWidth() + 20, password.getY() - 10, 200, 40);
		frame.getContentPane().add(passwordTxtField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(165, 182, 200, 40);
		frame.getContentPane().add(loginButton);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setBounds(100, 100, 450, 600);
				panel.setVisible(true);
			}
		});
		btnCreateAccount.setBounds(35, 182, 120, 40);
		frame.getContentPane().add(btnCreateAccount);
	
	}
}
	

	
	

