package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import client.Connector;
import functionality.Picture;
import functionality.Profile;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class MainGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton genderMaleBtn, genderFemaleBtn, likeBtn, passBtn;
	private JLabel profileName, profilePicture, judgingImg;
	private Image img = getProfilePicture();
	private Image img1 = new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
	private Image img2;
	private String currentGender;
	private int img2ratio;
	private int img2height = 500;
	private Connector connect;
	private Profile loggedInProfile;
	private Connector connector;

	/**
	 * Create the frame.
	 */
	
	public MainGui(Connector connector) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			this.connector = connector;
		initialize();
		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse button.
				contentPane.repaint();
				updateFrame();
			}
		});
	}

	void initialize(){
		setLayout(null);
		

		
		img2height = this.getHeight() * 65 / 100;
		
		genderMaleBtn = new JButton("Male");
		genderMaleBtn.setFont(GuiData.getCornerFont());
		genderMaleBtn.setSize(genderMaleBtn.getPreferredSize());
		genderMaleBtn.setBackground(GuiData.getNeutralColor());
		genderMaleBtn.addActionListener(this);
		add(genderMaleBtn);
		
		genderFemaleBtn = new JButton("Female");
		genderFemaleBtn.setFont(GuiData.getCornerFont());
		genderFemaleBtn.setSize(genderFemaleBtn.getPreferredSize());
		genderFemaleBtn.setBackground(GuiData.getNeutralColor());
		genderFemaleBtn.addActionListener(this);
		add(genderFemaleBtn);
		
		profilePicture = new JLabel("");
		profilePicture.setIcon(new ImageIcon(img));
		profilePicture.setSize(profilePicture.getPreferredSize());
		add(profilePicture);
		
		profileName = new JLabel("PROFILENAME");
		profileName.setFont(GuiData.getCornerFont());
		profileName.setSize(profileName.getPreferredSize());
		add(profileName);
		
		
		judgingImg = new JLabel("");
		judgingImg.setIcon(new ImageIcon(img1));
		judgingImg.setSize(judgingImg.getPreferredSize());
		
		img2ratio = judgingImg.getHeight()/judgingImg.getWidth();
		
		img2 = img1.getScaledInstance(img2height, img2height/img2ratio, Image.SCALE_DEFAULT);
		judgingImg.setIcon(new ImageIcon(img2));
		judgingImg.setSize(judgingImg.getPreferredSize());
		add(judgingImg);
			
		passBtn = new JButton("Pass");
		passBtn.setBackground(GuiData.getNeutralColor());
		add(passBtn);

		likeBtn = new JButton("Like");
		likeBtn.setBackground(GuiData.getNeutralColor());
		likeBtn.addActionListener(this);
		add(likeBtn);
		
		
	}
	
	private Image getProfilePicture() {
		Image img =  new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
		img = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		return img;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == genderMaleBtn){
			genderFemaleBtn.setBackground(GuiData.getNeutralColor());
			genderMaleBtn.setBackground(GuiData.getMaleColor());
			currentGender = "Male";
			
			Picture picy = connector.selectMale();
			img1 = picy.getImage();
			updateFrame();
		} else if(e.getSource() == genderFemaleBtn){
			genderFemaleBtn.setBackground(GuiData.getFemaleColor());
			genderMaleBtn.setBackground(GuiData.getNeutralColor());
			currentGender = "Female";
		} else if(e.getSource() == likeBtn){
			if(connector.likePicture()){
				Picture picy = connector.selectMale();
				img1 = picy.getImage();
				updateFrame();
			}
		}
		
	}
	
	void updateFrame(){
		img2height = this.getHeight() * 65 / 100;
		
		genderMaleBtn.setBounds(10, 10, genderMaleBtn.getWidth(), genderMaleBtn.getHeight());
		
		genderFemaleBtn.setBounds(genderMaleBtn.getX() + genderMaleBtn.getWidth(), genderMaleBtn.getY(), genderFemaleBtn.getWidth(), genderFemaleBtn.getHeight());
		
		profilePicture.setBounds(this.getWidth() - profilePicture.getWidth()- 30, 10, profilePicture.getWidth(), profilePicture.getHeight());
		
		profileName.setBounds(profilePicture.getX() - profileName.getWidth() - 15, profilePicture.getY() + (profilePicture.getHeight()-profileName.getHeight())/2, profileName.getWidth(), profileName.getHeight());
		
		img2 = img1.getScaledInstance(img2height, img2height/img2ratio, Image.SCALE_SMOOTH);
		judgingImg.setIcon(new ImageIcon(img2));
		judgingImg.setSize(judgingImg.getPreferredSize());
		judgingImg.setBounds((this.getWidth() - judgingImg.getWidth())/2, (this.getHeight() - judgingImg.getHeight())/10*4, judgingImg.getHeight(), judgingImg.getHeight());
		
		passBtn.setBounds(judgingImg.getX(), judgingImg.getY() + judgingImg.getHeight() + 15, judgingImg.getWidth()/2, judgingImg.getHeight()/8);
		likeBtn.setBounds(judgingImg.getX() + judgingImg.getWidth()/2, judgingImg.getY() + judgingImg.getHeight() + 15, judgingImg.getWidth()/2, judgingImg.getHeight()/8);
	}
	
	void setLoggedInProfile(Profile profile){
		loggedInProfile = profile;
	}
	
}
