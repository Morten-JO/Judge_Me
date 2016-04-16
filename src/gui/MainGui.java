package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton genderMaleBtn, genderFemaleBtn;
	private JLabel profileName, profilePicture;
	private Image img = getProfilePicture();
	private Image judgingImg;
	

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
		
		genderMaleBtn = new JButton("Male");
		genderMaleBtn.setFont(GuiData.getCornerFont());
		genderMaleBtn.setSize(genderMaleBtn.getPreferredSize());
		genderMaleBtn.setBounds(10, 10, genderMaleBtn.getWidth(), genderMaleBtn.getHeight());
		genderMaleBtn.addActionListener(this);
		add(genderMaleBtn);
		
		genderFemaleBtn = new JButton("Female");
		genderFemaleBtn.setFont(GuiData.getCornerFont());
		genderFemaleBtn.setSize(genderFemaleBtn.getPreferredSize());
		genderFemaleBtn.setBounds(genderMaleBtn.getX() + genderMaleBtn.getWidth(), genderMaleBtn.getY(), genderFemaleBtn.getWidth(), genderFemaleBtn.getHeight());
		genderFemaleBtn.addActionListener(this);
		add(genderFemaleBtn);
		
		profilePicture = new JLabel("");
		profilePicture.setIcon(new ImageIcon(img));
		profilePicture.setSize(profilePicture.getPreferredSize());
		profilePicture.setBounds(this.getWidth()-profilePicture.getWidth()- 30, 10, profilePicture.getWidth(), profilePicture.getHeight());
		add(profilePicture);
		
		profileName = new JLabel("PROFILENAME");
		profileName.setFont(GuiData.getCornerFont());
		profileName.setSize(profileName.getPreferredSize());
		profileName.setBounds(profilePicture.getX() - profileName.getWidth() - 15, profilePicture.getY() + (profilePicture.getHeight()-profileName.getHeight())/2, profileName.getWidth(), profileName.getHeight());
		add(profileName);
	}
	
	private Image getProfilePicture() {
		// TODO Auto-generated method stub
		Image img =  new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
		img = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		return img;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == genderMaleBtn){
			genderFemaleBtn.setBackground(GuiData.getNeutralColor());
			genderMaleBtn.setBackground(GuiData.getMaleColor());
		} else if(e.getSource() == genderFemaleBtn){
			genderFemaleBtn.setBackground(GuiData.getNeutralColor());
			genderMaleBtn.setBackground(GuiData.getMaleColor());
		}
		
	}
}
