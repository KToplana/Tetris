package paketa1;
import java.awt.* ; 
import javax.swing.* ; 
import java.awt.event.* ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random ; 


public class SettingPanel extends JPanel {
	final int MENUSIZE = 3 ; 
	
	JRadioButton [] selectMenu = new JRadioButton [MENUSIZE] ; ButtonGroup selectMenuGroup = new ButtonGroup () ; 
	String [] selectMenuString = {"Start" , "Options" , "Help"} ; 
	ActionListener listener ; 
	GridLayout gridLayout = new GridLayout (MENUSIZE , 1) ; 
	
	public SettingPanel (ActionListener listener ) {
		this.listener = listener ; 
		setting () ; 
	}
	public void setting () {
		this.setLayout (gridLayout ) ; 
		for(int i=0 ; i < selectMenu.length ; i ++ ) {
			selectMenu[i] = new JRadioButton (selectMenuString[i] ) ; 
			//selectMenu[i].addActionListener(listener );
			selectMenu[i].setFocusable(false );
			selectMenuGroup.add(selectMenu[i] );
			this.add(selectMenu[i] ) ; 
			
		}
		
		
		
	}
}

