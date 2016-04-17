package gui;

import java.awt.*;
import java.util.List;
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
import java.util.ArrayList;

public class MainGui extends JFrame implements ActionListener{

	private Picture[] pictureIDs;
	private List<JButton> pbtn = new ArrayList<JButton>();
	private JPanel contentPane;
	private JButton genderMaleBtn, genderFemaleBtn, likeBtn, passBtn, profileBtn, browse, backBtnp;
	private JLabel profileName, profilePicture, judgingImg, title, back, backp, profileNamep, profilePicturep, imgpLabel;
	private Image img = getProfilePicture();
	private Image img1 = new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
	private Image img2, imgp, noimg;
	private Image pimg = new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
	private String currentGender;
	private int img2ratio, imgpRatio;
	private int img2height = 500;
	private Connector connect;
	private Profile loggedInProfile;
	private Connector connector;
	private Image backimg = new ImageIcon(this.getClass().getResource("/Background.jpg")).getImage();
	private JPanel scrollPanel;
	private JScrollPane scroll;

	/**
	 * Create the frame.
	 */
	
	public MainGui(Connector connector) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("Judge Me");
		contentPane = new JPanel();
		//contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			this.connector = connector;
		initializeMain();
		initializeProfile();
		this.getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse button.
				updateFrame();
			}
		});
	}

	void initializeMain(){
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
		
		profileBtn = new JButton("Profile");
		profileBtn.setSize(profileBtn.getPreferredSize());
		profileBtn.setBackground(GuiData.getNeutralColor());
		profileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				updateMain(false);
				updateProfile(true);
			}
		});
		back.add(profileBtn);
		
		browse = new JButton("Upload Picture");
		browse.setSize(browse.getPreferredSize());
		browse.setBackground(GuiData.getNeutralColor());
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
		passBtn.addActionListener(this);
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
			if(connector.selectMale()){				
				Picture picy = connector.getPicture();
				img1 = picy.getImage();
			}
			updateFrame();
			
		} else if(e.getSource() == genderFemaleBtn){
			
			genderFemaleBtn.setBackground(GuiData.getFemaleColor());
			genderMaleBtn.setBackground(GuiData.getNeutralColor());
			currentGender = "Female";
			if(connector.selectFemale()){
				Picture picy = connector.getPicture();
				img1 = picy.getImage();
			}
			updateFrame();
			
		} else if(e.getSource() == likeBtn){
		
			if(connector.likePicture()){
				connector.likePicture();
				checkAndSelect();
			} else if(e.getSource() == passBtn){
				connector.dislikePicture();
				checkAndSelect();
			}
			
		} else if(e.getSource() == browse){
			
			JFileChooser file = new JFileChooser();
			file.setCurrentDirectory(new File(System.getProperty("user.home")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images","jpg");
			file.addChoosableFileFilter(filter);
			file.setAcceptAllFileFilterUsed(false);
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
		} else if(e.getSource() == backBtnp){
			updateProfile(false);
			updateMain(true);
			
			
		}
		
	}

	void updateFrame(){
		
		
		back.setBounds(0, 0, back.getWidth(), back.getHeight());
		backp.setBounds(0, 0, back.getWidth(), back.getHeight());
		
		genderMaleBtn.setBounds(10, 10, genderMaleBtn.getWidth(), genderMaleBtn.getHeight());
		
		genderFemaleBtn.setBounds(genderMaleBtn.getX() + genderMaleBtn.getWidth(), genderMaleBtn.getY(), genderFemaleBtn.getWidth(), genderFemaleBtn.getHeight());
		
		title.setBounds((this.getWidth()-title.getWidth())/2, 10, title.getWidth(), title.getHeight());
		
		profilePicture.setBounds(this.getWidth() - profilePicture.getWidth()- 30, 10, profilePicture.getWidth(), profilePicture.getHeight());
		profilePicturep.setBounds(this.getWidth() - profilePicture.getWidth()- 30, 10, profilePicture.getWidth(), profilePicture.getHeight());
		
		profileName.setBounds(profilePicture.getX() - profileName.getWidth() - 15, profilePicture.getY() + (profilePicture.getHeight()-profileName.getHeight())/2, profileName.getWidth(), profileName.getHeight());
		profileNamep.setBounds(profilePicture.getX() - profileName.getWidth() - 15, profilePicture.getY() + (profilePicture.getHeight()-profileName.getHeight())/2, profileName.getWidth(), profileName.getHeight());
		
		profileBtn.setBounds(profilePicture.getX() + profilePicture.getWidth() - profileBtn.getWidth(), profilePicture.getY() + profilePicture.getHeight() + 10, profileBtn.getWidth(), profileBtn.getHeight());
		
		browse.setBounds(genderMaleBtn.getX(), profileBtn.getY(), browse.getWidth(), browse.getHeight());
		
		img2height = (this.getHeight() - (profileBtn.getY() + profileBtn.getHeight())) * 75 / 100;
		img2 = img1.getScaledInstance(img2height, img2height/img2ratio, Image.SCALE_SMOOTH);
		judgingImg.setIcon(new ImageIcon(imgp));
		judgingImg.setSize(judgingImg.getPreferredSize());
		judgingImg.setBounds((this.getWidth() - judgingImg.getWidth())/2, profileBtn.getY() + profileBtn.getHeight() + 15, judgingImg.getHeight(), judgingImg.getHeight());
		
		passBtn.setBounds(judgingImg.getX(), judgingImg.getY() + judgingImg.getHeight() + this.getHeight()*2/150, judgingImg.getWidth()/2, judgingImg.getHeight()/8);
		likeBtn.setBounds(judgingImg.getX() + judgingImg.getWidth()/2, passBtn.getY(), judgingImg.getWidth()/2, judgingImg.getHeight()/8);
		
		//Profile
		backBtnp.setBounds(10, 10, backBtnp.getWidth(), backBtnp.getHeight());

		imgp = pimg.getScaledInstance(img2height, img2height/imgpRatio, Image.SCALE_SMOOTH);
		imgpLabel.setIcon(new ImageIcon(imgp));
		imgpLabel.setSize(imgpLabel.getPreferredSize());
		imgpLabel.setBounds((this.getWidth() - imgpLabel.getWidth())/4, profileBtn.getY() + profileBtn.getHeight() + 15, imgpLabel.getHeight(), imgpLabel.getHeight());
		
		scroll.setBounds(imgpLabel.getX() + imgpLabel.getWidth() + 20, imgpLabel.getY(), imgpLabel.getWidth()/2, imgpLabel.getHeight());
	}
	
	void setLoggedInProfile(Profile profile){
		loggedInProfile = profile;
	}
	
	void checkAndSelectMale(){
		if(connector.selectMale()){
			Picture picy = connector.getPicture();
			img1 = picy.getImage();
		}
	}
	
	void checkAndSelectFemale(){
		if(connector.selectFemale()){
			Picture picy = connector.getPicture();
			img1 = picy.getImage();
		}
	}
	
	void checkAndSelect(){
		if(currentGender == "Male"){
			checkAndSelectFemale();
		}else if(currentGender == "Female"){
			checkAndSelectFemale();
		}
	}
	
	void initializeProfile(){
		backp = new JLabel("");
		backp.setIcon(new ImageIcon(backimg));
		backp.setSize(backp.getPreferredSize());
		add(backp);
		backp.setVisible(false);
		
		profilePicturep = new JLabel("");
		profilePicturep.setIcon(new ImageIcon(img));
		profilePicturep.setSize(profilePicturep.getPreferredSize());
		backp.add(profilePicturep);
		
		profileNamep = new JLabel("PROFILENAME");
		profileNamep.setFont(GuiData.getCornerFont());
		profileNamep.setSize(profileNamep.getPreferredSize());
		profileNamep.setForeground(GuiData.getTextColor());
		backp.add(profileNamep);
		
		backBtnp = new JButton("Back");
		backBtnp.setSize(backBtnp.getPreferredSize());
		backBtnp.addActionListener(this);
		backp.add(backBtnp);
		
		imgpLabel = new JLabel("");
		imgpLabel.setIcon(new ImageIcon(pimg));
		imgpLabel.setSize(imgpLabel.getPreferredSize());
		imgpRatio = imgpLabel.getHeight()/imgpLabel.getWidth();
		
		imgp = pimg.getScaledInstance(img2height, img2height/imgpRatio, Image.SCALE_DEFAULT);
		imgpLabel.setIcon(new ImageIcon(imgp));
		imgpLabel.setSize(imgpLabel.getPreferredSize());
		backp.add(imgpLabel);
		
		scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(0,1));
		int counter = 0;
		
		pictureIDs = connector.PicturesIds();
		for(Picture picture : pictureIDs){
			pbtn.add(new JButton());
			noimg = picture.getImage();
			pbtn.get(counter).setIcon(new ImageIcon(noimg));
			pbtn.get(counter).setSize(scrollPanel.getWidth() - 10, scrollPanel.getWidth());
			scrollPanel.add(pbtn.get(counter));
			counter++;
		}
		
		scroll = new JScrollPane(scrollPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		backp.add(scroll);
	}
	
	void updateMain(boolean b){
		back.setVisible(b);
		if(!b){
			this.setSize(1000, 700);
		} else{
			this.setSize(700, 700);
		}
	}
	
	void updateProfile(boolean b){
		backp.setVisible(b);
	}
	
}
