package paketa1;
import java.awt.* ; 
import javax.swing.* ; 
import java.awt.event.* ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random ; 

public class NorthPanel extends JPanel {
	JButton startStop = new JButton ("Start") ; 
	ActionListener listener ; 
	NorthPanel (ActionListener listener ) {
		this .listener = listener ; 
		this.add(startStop) ; 
		startStop.addActionListener(listener );
		
		
		
	}
}