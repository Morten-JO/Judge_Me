package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import client.Connector;
import functionality.Picture;
import functionality.Profile;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class MainGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton genderMaleBtn, genderFemaleBtn, likeBtn, passBtn, browse;
	private JLabel profileName, profilePicture, judgingImg, title, back;
	private Image img = getProfilePicture();
	private Image img1 = new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
	private Image img2;
	private String currentGender;
	private int img2ratio;
	private int img2height = 500;
	private Connector connect;
	private Profile loggedInProfile;
	private Connector connector;
	private Image backimg = new ImageIcon(this.getClass().getResource("/Background.jpg")).getImage();

	/**
	 * Create the frame.
	 */
	
	public MainGui(Connector connector) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		//contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			this.connector = connector;
		initialize();
		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse button.
				updateFrame();
			}
		});
	}

	void initialize(){
		setLayout(null);
		
		loggedInProfile = new Profile("NAVN", "GENDER", "MAIL");
		
		back = new JLabel("");
		back.setIcon(new ImageIcon(backimg));
		back.setSize(back.getPreferredSize());
		add(back);
		
		img2height = this.getHeight() * 65 / 100;
		
		genderMaleBtn = new JButton("Male");
		genderMaleBtn.setFont(GuiData.getCornerFont());
		genderMaleBtn.setSize(genderMaleBtn.getPreferredSize());
		genderMaleBtn.setBackground(GuiData.getNeutralColor());
		genderMaleBtn.addActionListener(this);
		back.add(genderMaleBtn);
		
		genderFemaleBtn = new JButton("Female");
		genderFemaleBtn.setFont(GuiData.getCornerFont());
		genderFemaleBtn.setSize(genderFemaleBtn.getPreferredSize());
		genderFemaleBtn.setBackground(GuiData.getNeutralColor());
		genderFemaleBtn.addActionListener(this);
		back.add(genderFemaleBtn);
		
		title = new JLabel("Judge Me!");
		title.setFont(GuiData.getTitleFont());
		title.setSize(title.getPreferredSize());
		title.setForeground(GuiData.getTextColor());
		title.setBackground(GuiData.getNeutralColor());
		back.add(title);
		
		profilePicture = new JLabel("");
		profilePicture.setIcon(new ImageIcon(img));
		profilePicture.setSize(profilePicture.getPreferredSize());
		back.add(profilePicture);
		
		profileName = new JLabel("PROFILENAME");
		profileName.setFont(GuiData.getCornerFont());
		profileName.setSize(profileName.getPreferredSize());
		profileName.setForeground(GuiData.getTextColor());
		back.add(profileName);
		
		browse = new JButton("Browse");
		browse.setSize(browse.getPreferredSize());
		browse.addActionListener(this);
		back.add(browse);
			
		judgingImg = new JLabel("");
		judgingImg.setIcon(new ImageIcon(img1));
		judgingImg.setSize(judgingImg.getPreferredSize());
		
		img2ratio = judgingImg.getHeight()/judgingImg.getWidth();
		
		img2 = img1.getScaledInstance(img2height, img2height/img2ratio, Image.SCALE_DEFAULT);
		judgingImg.setIcon(new ImageIcon(img2));
		judgingImg.setSize(judgingImg.getPreferredSize());
		back.add(judgingImg);
			
		passBtn = new JButton("Pass");
		passBtn.setBackground(GuiData.getNeutralColor());
		back.add(passBtn);

		likeBtn = new JButton("Like");
		likeBtn.setBackground(GuiData.getNeutralColor());
		likeBtn.addActionListener(this);
		back.add(likeBtn);
		
		
	}
	
	private Image getProfilePicture() {
		Image img =  new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
		img = img.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		return img;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		updateFrame();
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
			
			Picture picy = connector.selectMale();
			img1 = picy.getImage();
			updateFrame();
		} else if(e.getSource() == likeBtn){
			if(connector.likePicture()){
				Picture picy = connector.selectMale();
				img1 = picy.getImage();
				updateFrame();
			} else if(e.getSource() == passBtn){
				Picture picy = connector.selectFemale();
				img1 = picy.getImage();
				updateFrame();
			}
		} else if(e.getSource() == browse){
			JFileChooser file = new JFileChooser();
			file.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg");
			file.addChoosableFileFilter(filter);
			int result = file.showSaveDialog(null);
			if(result == JFileChooser.APPROVE_OPTION){
				File selectedFile = file.getSelectedFile();
				String path = selectedFile.getAbsolutePath();
				System.out.println(path);
				
				String des = JOptionPane.showInputDialog(this, "What Sholud the description for your picture be?", null);
				
				if(loggedInProfile.getGender() == "Male"){
					connector.uploadPictureBoy(des, path);
				} else if(loggedInProfile.getGender() == "Female"){
					connector.uploadPictureGirl(des, path);
				}
				//connector.uploadPictureGirl(des, path);
			}
		}
		
	}

	void updateFrame(){
		
		back.setBounds(0, 0, back.getWidth(), back.getHeight());
		
		
		genderMaleBtn.setBounds(10, 10, genderMaleBtn.getWidth(), genderMaleBtn.getHeight());
		
		genderFemaleBtn.setBounds(genderMaleBtn.getX() + genderMaleBtn.getWidth(), genderMaleBtn.getY(), genderFemaleBtn.getWidth(), genderFemaleBtn.getHeight());
		
		title.setBounds((this.getWidth()-title.getWidth())/2, 10, title.getWidth(), title.getHeight());
		
		profilePicture.setBounds(this.getWidth() - profilePicture.getWidth()- 30, 10, profilePicture.getWidth(), profilePicture.getHeight());
		
		profileName.setBounds(profilePicture.getX() - profileName.getWidth() - 15, profilePicture.getY() + (profilePicture.getHeight()-profileName.getHeight())/2, profileName.getWidth(), profileName.getHeight());
		
		browse.setBounds(profilePicture.getX() + profilePicture.getWidth() - browse.getWidth(), profilePicture.getY() + profilePicture.getHeight() + 10, browse.getWidth(), browse.getHeight());
		
		img2height = (this.getHeight() - (browse.getY() + browse.getHeight())) * 75 / 100;
		img2 = img1.getScaledInstance(img2height, img2height/img2ratio, Image.SCALE_SMOOTH);
		judgingImg.setIcon(new ImageIcon(img2));
		judgingImg.setSize(judgingImg.getPreferredSize());
		judgingImg.setBounds((this.getWidth() - judgingImg.getWidth())/2, browse.getY() + browse.getHeight() + 15, judgingImg.getHeight(), judgingImg.getHeight());
		
		passBtn.setBounds(judgingImg.getX(), judgingImg.getY() + judgingImg.getHeight() + this.getHeight()*2/150, judgingImg.getWidth()/2, judgingImg.getHeight()/8);
		likeBtn.setBounds(judgingImg.getX() + judgingImg.getWidth()/2, passBtn.getY(), judgingImg.getWidth()/2, judgingImg.getHeight()/8);
	}
	
	void setLoggedInProfile(Profile profile){
		loggedInProfile = profile;
	}
	
}
