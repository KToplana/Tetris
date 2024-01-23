package paketa1;
import java.awt.* ; 
import javax.swing.* ; 
import java.awt.event.* ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random ; 

public class GameFrame extends JFrame implements ActionListener {
	GamePanel gamePanel = new GamePanel () ; 
	SettingPanel settingPanel = new SettingPanel (this ) ; 
	NorthPanel northPanel = new NorthPanel (this ) ; 
	boolean [] move = new boolean [4] ; 
	Timer timer = new Timer (100 , this ) ; 
	
	Action upAction , 
	
			downAction , 
			leftAction , 
			rightAction ; 
	
	GameFrame () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(settingPanel , BorderLayout.CENTER ) ; 
		this.getContentPane().add(northPanel , BorderLayout.NORTH ) ; 
		northPanel.startStop.setFocusable(false );
		
		upAction = new UpAction () ; 
		downAction = new DownAction () ; 
		leftAction = new LeftAction () ; 
		rightAction = new RightAction () ; 
		
		
		InputMap im = gamePanel.getInputMap(gamePanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = gamePanel.getActionMap();
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upAction");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downAction");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "leftAction");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "rightAction");
        
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "upActionReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "downActionReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "leftActionReleased");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "rightActionReleased");
        
        gamePanel.getActionMap () .put("leftActionReleased", new AbstractAction () {public void actionPerformed (ActionEvent event ) { 
        	move[2] = false ; 
        	timer.stop () ; 
        	
        } });
        gamePanel.getActionMap () .put("rightActionReleased", new AbstractAction () {public void actionPerformed (ActionEvent event ) { 
        	move[3] = false ; 
        	timer.stop () ; 
        	
        } });
        
        
		//gamePanel.getInputMap() .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0) , "upAction");
		gamePanel.getActionMap().put("upAction", upAction );
		
		//gamePanel.getInputMap() .put(KeyStroke.getKeyStroke("DOWN"), "downAction");
		gamePanel.getActionMap().put("downAction", downAction );
		
		//gamePanel.getInputMap() .put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
		gamePanel.getActionMap().put("leftAction", leftAction ) ; 
		
		//gamePanel.getInputMap() .put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
		gamePanel.getActionMap().put("rightAction", rightAction );
		
		
		this.pack () ; 
		this.setVisible(true );
		
	}
	public void actionPerformed (ActionEvent event ) {
		if (event.getSource () == northPanel.startStop ) {
			//supozohet te filloje loja 
			if (northPanel.startStop.getText().equals("<- GoBack")) 
			{
				this.getContentPane().remove(gamePanel);
				this.getContentPane().add(settingPanel , BorderLayout.CENTER ) ;
				northPanel.startStop.setText("Start");
				gamePanel.timer.stop () ; 
				
				this.pack () ; 
				
				
			}
			else {
				if (settingPanel.selectMenu[0] .isSelected () ) {
					northPanel.startStop.setText("<- GoBack");
					this.getContentPane().remove (settingPanel ) ; 
					this.getContentPane().add(gamePanel , BorderLayout.CENTER ) ;
					gamePanel.timer.start () ; 
					gamePanel.setFocusable(true );
					
					
					this.setFocusable(true );
					
					this.pack () ; 
				}
				else if (settingPanel.selectMenu[1].isSelected() ) { 
					
					
				}
				else if (settingPanel.selectMenu[2].isSelected () ){
					
				}
				
				
			}
			
			
		}
		if (event.getSource () == timer ) {
			if (move[2] ) {
				gamePanel.left () ; 
				
			}
			else if (move [3] ) {
				gamePanel.right () ; 
				
			}
		}
	}
	
	public class UpAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gamePanel.up() ; 
			
		}
		
	}
	public class DownAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gamePanel.down() ; 
			
		}
		
	}
	public class LeftAction extends AbstractAction {

		public void actionPerformed (ActionEvent event ) {
			gamePanel.left () ; 
			move[2] = true ; 
			
		}
		
		
	}
	public class RightAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gamePanel.right () ; 
			move[3] = true ; 
			
			
		}
		
	}
}
