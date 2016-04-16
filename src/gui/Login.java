package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;

public class Login {

	private JFrame frame;
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;

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
		btnCreateAccount.setBounds(35, 182, 120, 40);
		frame.getContentPane().add(btnCreateAccount);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
