package gui;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Connector;

public class ProfilePanel extends JPanel {

	private Connector connector;
	
	/**
	 * Create the panel.
	 * @param connector 
	 * @throws IOException 
	 */
	public ProfilePanel(Connector connector) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
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

	private void initialize() {
		// TODO Auto-generated method stub
		
	}


	}

}
