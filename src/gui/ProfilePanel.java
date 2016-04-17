package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Connector;
import functionality.Profile;

public class ProfilePanel extends JPanel {

	private Connector connector;
	private JPanel contentPane;
	private JLabel hej;

	private JLabel profileName, profilePicture, judgingImg, title, back;
	private Image img1 = new ImageIcon(this.getClass().getResource("/man.jpg")).getImage();
	private int img2ratio;
	private int img2height = 500;
	private Connector connect;
	private Profile loggedInProfile;
	private Image backimg = new ImageIcon(this.getClass().getResource("/Background.jpg")).getImage();
	/**
	 * Create the panel.
	 * @param connector 
	 * @throws IOException 
	 */
	public ProfilePanel(Connector connector) throws IOException {
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		//contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
			this.connector = connector;
		initialize();
	}

	private void initialize() {
setLayout(null);
		
		loggedInProfile = new Profile("NAVN", "GENDER", "MAIL");
		
		back = new JLabel("");
		back.setIcon(new ImageIcon(backimg));
		back.setSize(back.getPreferredSize());
		add(back);
		
		img2height = this.getHeight() * 65 / 100;

		
		title = new JLabel("Judge Me!");
		title.setFont(GuiData.getTitleFont());
		title.setSize(title.getPreferredSize());
		title.setForeground(GuiData.getTextColor());
		title.setBackground(GuiData.getNeutralColor());
		back.add(title);
		
		profilePicture = new JLabel("");
		profilePicture.setIcon(new ImageIcon(img1));
		profilePicture.setSize(profilePicture.getPreferredSize());
		back.add(profilePicture);
		
		profileName = new JLabel("PROFILENAME");
		profileName.setFont(GuiData.getCornerFont());
		profileName.setSize(profileName.getPreferredSize());
		profileName.setForeground(GuiData.getTextColor());
		back.add(profileName);

			
//		judgingImg = new JLabel("");
//		judgingImg.setIcon(new ImageIcon(img1));
//		judgingImg.setSize(judgingImg.getPreferredSize());
//		
//		img2ratio = judgingImg.getHeight()/judgingImg.getWidth();
//		
//		img2 = img1.getScaledInstance(img2height, img2height/img2ratio, Image.SCALE_DEFAULT);
//		judgingImg.setIcon(new ImageIcon(img2));
//		judgingImg.setSize(judgingImg.getPreferredSize());
//		back.add(judgingImg);
			

	}

}
