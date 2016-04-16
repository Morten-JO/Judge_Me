package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;

import client.Connector;
import server.ServerRun;
import server.ServerStart;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class Login {

	private JFrame frame;
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private static JPanel panel;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private Connector connecter;
	
	private boolean accessConfirmed;

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
		try {
			connecter = new Connector();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		panel.setBackground(SystemColor.textHighlight);
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
		
		
		textField1 = new JTextField();
		textField1.setBounds(210, 90, 200, 40);
		panel.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(textField1.getX(), textField1.getY() + GuiData.getY(), 200, 40);
		panel.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setBounds(textField1.getX(), textField2.getY() + GuiData.getY(), 200, 40);
		panel.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setBounds(textField1.getX(), textField3.getY() + GuiData.getY(), 200, 40);
		panel.add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setBounds(textField1.getX(), textField4.getY() + GuiData.getY(), 200, 40);
		panel.add(textField5);
		textField5.setColumns(10);
		
		textField6 = new JTextField();
		textField6.setBounds(textField1.getX(), textField5.getY() + GuiData.getY(), 200, 40);
		panel.add(textField6);
		textField6.setColumns(10);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Do you accept our policy?");
		chckbxNewCheckBox.setBounds(125, Age.getY() + GuiData.getY(), 200, 23);
		panel.add(chckbxNewCheckBox);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panel.setVisible(false);
				frame.setBounds(100, 100, 450, 300);
			}
		});
		backButton.setBounds(newUsername.getX(), chckbxNewCheckBox.getY() + GuiData.getY() - 10, 180, 50);
		panel.add(backButton);
		
		JButton createButton = new JButton("Create");
		createButton.setBounds(newUsername.getX() + backButton.getWidth() + 10, chckbxNewCheckBox.getY() + GuiData.getY() - 10, 180, 50);
		panel.add(createButton);
		
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
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				System.out.println(passwordTxtField.getText());
				accessConfirmed = connecter.login(usernameTxtField.getText(), passwordTxtField.getText());
				MainGui mg = new MainGui(connecter);
				
				if(accessConfirmed == true) 
				{
					frame.dispose();
					mg.setVisible(true);
				}
				else
				{
					System.out.println("Could not login");
				}
			}
		});
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
	

	
	

