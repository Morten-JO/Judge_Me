package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class GuiData {
	
//This is a data class witch has all kind of data like fx. different fonts, sizes and stuff like that
	
//	The font for our login screen
	public static Font getHeadlineFont() {
		return new Font("Myanmar Sangam MN", Font.BOLD, 22);
	}
	
	public static Font getUserPassFont() {
		return new Font("Myanmar Sangam MN", Font.BOLD, 18);
	}
	
//	The font for the "corner profilepicture text" 
	public static Font getCornerFont() {
		return new Font("Myanmar Sangam MN", Font.PLAIN, 14);
	}
	
	

//	These 3 methods sets the collor of women, men and neutral for the corner button
	public static Color getNeutralColor() {
		return new Color(192,192,192);
	}
	
	public static Color getMaleColor() {
		return new Color(135,206,250);
	}
	
	public static Color getFemaleColor() {
		return new Color(255,182,193);
	}
	
	
	
//	Method used to set the space between labels in our login gui
	public static int getY() {
		return 60;
	}
}
