package paketa1;
import java.awt.* ; 
import javax.swing.* ; 
import java.awt.event.* ; 

public class Frame extends JFrame implements ActionListener {
	GamePanel game = new GamePanel () ; 
	JPanel northPanel = new JPanel () ; 
	
	JButton startStop = new JButton ("Start") ; 
	boolean paused ; 
	
	Frame () { 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		this.getContentPane() .add(game , BorderLayout.CENTER ) ;  
		this.getContentPane() .add (northPanel , BorderLayout.NORTH ) ; 
		northPanel.add(startStop ) ; 
		startStop.addActionListener(this );
		
		this.pack () ;  
		this.setResizable(false );
		this.setVisible(true );
		
	}
	
	public void actionPerformed (ActionEvent event ) {
		if (event.getSource () == startStop ) {
			if (paused ) {
				startStop.setText("Stop");
				game.timer.start () ; 
				paused = false ; 
				
			}
			else {
				startStop.setText("Start");
				game.timer.stop () ;
				paused = true ; 
				
			}
		}
	}
}
