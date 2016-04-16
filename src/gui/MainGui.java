package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private JLabel profileName, profilePicture;
	Image img = getProfilePicture();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
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
	
	public MainGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		initialize();
	}

	void initialize(){
		setLayout(null);
		

		profilePicture = new JLabel("");
		profilePicture.setIcon(new ImageIcon(img));
		profilePicture.setSize(profilePicture.getPreferredSize());
		profilePicture.setBounds(this.getWidth()-profilePicture.getWidth()- 30, 10, profilePicture.getWidth(), profilePicture.getHeight());
		add(profilePicture);
		
		profileName = new JLabel("PROFILENAME");
		//profileName =;
		profileName.setSize(profileName.getPreferredSize());
		profileName.setBounds(profilePicture.getX() - profileName.getWidth() - 15, 20, profileName.getWidth(), profileName.getHeight());
		add(profileName);
	}
	
	private Image getProfilePicture() {
		// TODO Auto-generated method stub
		Image img =  new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
		img = img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		return img;
	}
}
