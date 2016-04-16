package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

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
		username.setBounds(40, 80, username.getWidth(), username.getHeight());
		frame.getContentPane().add(username);
		
		JLabel password = new JLabel("Password: ");
		password.setFont(GuiData.getUserPassFont());
		password.setSize(password.getPreferredSize());
		password.setBounds(username.getX(), username.getY() + username.getHeight() + 20, password.getWidth(), password.getHeight());
		frame.getContentPane().add(password);
		
		textField = new JTextField();
		textField.setBounds(194, 78, 177, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(232, 136, 96, 28);
		frame.getContentPane().add(passwordField);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
