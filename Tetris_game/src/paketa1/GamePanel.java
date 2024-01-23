package paketa1;
import java.awt.* ; 
import javax.swing.* ; 
import java.awt.event.* ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random ; 


public class GamePanel extends JPanel implements ActionListener {
	final int WIDTH = 300 , 
			HEIGHT = 400 ; 
	final int UNIT = 20 ; 
	int DELAY = 300 ; 
	
	Dimension dimension = new Dimension (WIDTH , HEIGHT ) ; 
	boolean matrica [][] = new boolean [HEIGHT / UNIT ][WIDTH / UNIT ] ; 
	Timer timer = new Timer (DELAY , this ) ; 
	Part part = new Part (HEIGHT / UNIT , WIDTH / UNIT ) ; 
	KeyListener keyListener ; 
	
	File file ; 
	Scanner inputFile ; 
	Random rand = new Random () ; 
	boolean check ; 
	
	
	
	GamePanel () {
		this.setPreferredSize(dimension );
		this.setBackground(Color.DARK_GRAY );
		this.setFocusable(true );
		
		setting () ; 
		settingPart () ; 
		
		
		
		
		
	}
	
	public void settingPart () {
		for (int i=0 ; i < part.matrica.length ; i ++ ) {
			for(int j=0 ; j < part.matrica[i].length ; j ++ ) {
				part.matrica[i][j] = false ; 
				
			}
		}
		
		/*
		 * try {
			file = new File ("file.txt") ; 
			inputFile = new Scanner (file ) ; 
			
		}
		catch (FileNotFoundException e ) {
			JOptionPane.showMessageDialog(null , "File nuk gjendet . ");
			
		}
		 */
		int m = rand.nextInt (part.matrica[0].length - 1) ; 
		int n = rand.nextInt(5 ) ;  
		
		
		
		switch (n ) {
		case 0 : 
			part.matrica[0][m] = true ; 
			part.matrica[1][m] = true ; 
			part.matrica[2][m] = true ; 
			part.matrica[3][m] = true ; 
			
			break ; 
		case 1 : 
			part.matrica[0][m] = true ; 
			part.matrica[1][m] = true ; 
			part.matrica[0][m+1] = true ; 
			part.matrica[1][m+1] = true ; 
			
			break ; 
		case 2 : 
			part.matrica[0][m] = true ; 
			part.matrica[1][m] = true ; 
			part.matrica[1][m+1] = true ; 
			part.matrica[2][m+1] = true ; 
			
			break  ;
			
		case 3 : 
			part.matrica[0][m+1] = true ; 
			part.matrica[1][m+1] = true ; 
			part.matrica[1][m] = true ; 
			part.matrica[2][m] = true ; 
			break ; 
			
		case 4 :
			part.matrica[0][m] = true ; 
			part.matrica[1][m] = true ; 
			part.matrica[2][m] = true ; 
			part.matrica[2][m+1] = true ; 
			
			break ; 
			
		
		}
		timer.setDelay(DELAY );
		
		
		
	}
	public void setting () {
		for(int i=0 ; i < matrica.length ; i ++ ) {
			for(int j=0 ; j < matrica[0].length ; j ++ ) {
				matrica[i][j] = false ; 
				
			}
		}
		
	}
	public void paint (Graphics g ) {
		super.paint(g );
		
		for(int i=0 ; i < matrica.length ; i ++ ) {
			for(int j=0 ; j < matrica[0].length ; j ++ ) {
				if (matrica[i][j] ) {
					g.setColor(Color.red );
					g.fillRect(j * UNIT , i * UNIT , UNIT , UNIT );
					
				}
				else if (part.matrica[i][j] ) {
					g.setColor (Color.white ) ; 
					g.fillRect(j * UNIT , i * UNIT  , UNIT , UNIT );
					
				}
			}
			
		}
		g.setColor (Color.black ) ; 
		
		for(int i=0 ; i < HEIGHT ; i += UNIT ) {
			g.drawLine (0 , i , WIDTH , i ) ; 
			
		}
		for(int i=0 ; i < WIDTH ; i += UNIT ) {
			g.drawLine (i , 0 , i , HEIGHT ) ; 
		}
		
	}
	public void move () {
		for(int i=part.matrica.length - 2 ; i >= 0 ; i -= 1 ) {
			for(int j= part.matrica[i].length - 1 ; j >= 0 ; j -= 1 ) {
				if ((matrica[i+1][j] && part.matrica[i][j])  || part.matrica[part.matrica.length - 1 ][j]   ) {
					putPart () ; 
					settingPart () ; 
					return ; 
					
					
				}
			}
		}
		//nese kalon te gjitha provat me siper do te levize per poshte 
		for(int i=part.matrica.length - 2  ; i >= 0 ; i -= 1 ) {
			for(int j=part.matrica[i].length - 1  ; j >= 0 ; j -= 1 ) {
				part.matrica[i + 1 ][j] = part.matrica[i][j] ; 
				
			}
		}
		for(int i=0 ; i < part.matrica[0].length ; i ++ ) {
			part.matrica[0][i] = false ; 
			
		}
	}
	public void putPart () {
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			for(int j=0 ; j < part.matrica[i].length ; j ++ ) {
				matrica[i][j] = part.matrica[i][j] || matrica[i][j] ; 
				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		move () ; 
		delFull () ; 
		checkFull () ; 
		moveFull () ; 
		
		
		repaint () ; 
		
	}
	public void checkFull ()  {
		for(int i=0 ; i < matrica[0].length ; i ++ ) 
			if (matrica[0][i] )
			{
				timer.stop () ; 
				JOptionPane.showMessageDialog (null , "Game Over ! " ) ;				
			}
	}
	
	public void delFull () {
		for(int i=0 ; i < matrica.length ; i ++ ) {
			if (checkRresht (i )) {
				for(int j=0 ; j < matrica[i].length ; j ++ )
					matrica[i][j] = false ; 
				
			}
		}
	}
	public boolean checkRresht (int rec) {
		for(int i=0 ; i < matrica[rec].length ; i ++ ) 
			if (!matrica[rec][i])return false ; 
		
		return true ; 
		
	}
	public void moveFull () {
		boolean bool = false ; 
		boolean [] mat = new boolean [matrica[0].length ] ; 
		for (int i=0 ; i < mat.length ; i ++ ) {
			mat[i] = false ; 
			
		}
		for (int i=matrica.length - 2 ; i >= 0 ; i -=1  ) {
			for (int j=matrica[i].length - 1 ; j >= 0 ; j -=1  ) {
				if (levitate (i , j )) {
					mat[j] = true ; 
					bool = true ; 
					
					
					
				}
				
			}
			for (int j=0 ; j < mat.length ; j ++ ) {
				if (mat[j] )fall (i , j  ) ; 
			}
		}
		
	}
	public void fall (int x , int y ) { 
		if (matrica[x][y] ) {
			matrica[x+1][y] = true ; 
			matrica[x][y] = false ; 
			
		}
		
	}
	public boolean levLeft (int x , int y ) {
		for (int i=y , m = matrica[x].length ; i < m && matrica[x][y] ; i ++ ) {
			if (matrica[x+1][i] )return false ; 
			
		}
		return true ; 
		
		
	}
	public boolean levRight (int x , int y ) {
		for (int i=y ; i >= 0 && matrica[x][y] ; i -= 1 ) {
			if (matrica[x+1][i] )return false ; 
			
		}
		return true ; 
		
	}
	public boolean levitate (int x , int y ) {
		if (matrica[x+1][y])return false ; 
		
		return matrica[x][y] ? ( (y <= matrica[x].length - 1 ? levRight (x , y) : true ) && 
				(y >= 0  ? levLeft(x , y) : true ) ) : false ;
		
		
	}
	public void up() {
		// TODO Auto-generated method stub
		int up , down , left , right ; 
		down = 0 ; 
		right = 0 ; 
		up = matrica.length ; 
		left = matrica[0].length ; 
		
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			for(int j=0 ; j < part.matrica[i].length ; j ++ ) {
				if (part.matrica[i][j] ) {
					if (left > j ) {
						left = j ; 
						
						
					}
					break ; 
				}
			}
			for(int j=part.matrica[i].length - 1 ; j >= 0 ; j -= 1 ) {
				if (part.matrica[i][j] ) {
					if (right < j ) {
						right = j ; 
						
						
					}
					break ; 
				}
			}
		}
		
		for (int j=0 ; j < part.matrica[0].length ; j ++ ) {
			for(int i=0 ; i < part.matrica.length ; i ++ ) {
				if (part.matrica[i][j] ) {
					if (up > i ) {
						up = i ; 
						
						
					}
					break ; 
				}
			}
			for(int i=part.matrica.length - 1 ; i >= 0 ; i -= 1 ) {
				if (part.matrica[i][j] ) {
					if (down < i ) {
						down = i ; 
						
						
					}
					break ; 
				}
			}
		}
		
		
		boolean [][] spin = new boolean [down - up + 1 ][right - left + 1 ] ; 
		boolean [][] spined = new boolean [spin[0].length ][spin.length ] ; 
		for(int i=0 ; i < spin.length ; i ++ ) {
			for(int j=0 ; j < spin[i].length ; j ++ ) {
				spin[i][j] = part.matrica[i + up][j + left ] ; 
				spined[spined.length - 1 - j ][i] = spin[i][j] ; 
				
			}
		}
		
		for(int i=0 ; i < part.matrica.length ; i ++ )
			for(int j=0 ; j < part.matrica[i].length ; j ++ )
				part.matrica[i][j] = false ; 
		
		for(int i=0 ; i < spined.length ; i ++ ) {
			for(int j=0 ; j < spined[i].length ; j ++ ) {
				part.matrica[i + up ][j + left ] = spined [i][j] ; 
				
			}
		}
		
		spin = null ; 
		spined = null ; 
		
	}

	public void down() {
		// TODO Auto-generated method stub
		timer.setDelay(20 );
	}

	public void right() {
		// TODO Auto-generated method stub
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			if (part.matrica[i][part.matrica[0].length - 1 ] ) {
				return ; 
				
			}
		}
		
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			for(int j=0 ; j < part.matrica[i].length - 1 ; j ++ )
			{
				
				if (part.matrica[i][j] && matrica[i ][j + 1 ] ) {
					
					return ; 
					
				}
			}
		}
		for(int i=0 ; i < part.matrica.length; i ++ ) {
			for(int j=matrica[i].length - 2 ; j >= 0 ; j -= 1 ) {
				part.matrica[i ][j + 1 ] = part.matrica[i][j] ; 
				
			}
		}
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			part.matrica[i][0] = false ; 
			
		}
		repaint () ; 
		
	}

	public void left() {
		// TODO Auto-generated method stub
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			if (part.matrica[i][0] ) {
				return ; 
				
			}
		}
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			for(int j=1 ; j < part.matrica[i].length ; j ++ )
			{
				
				if (part.matrica[i][j] && matrica[i ][j - 1 ] ) {
					return ; 
					
				}
			}
		}
		for(int i=0 ; i < part.matrica.length; i ++ ) {
			for(int j=1 ; j < part.matrica[i].length ; j += 1 ) {
				part.matrica[i ][j - 1 ] = part.matrica[i][j] ; 
				
				
			}
		}
		for(int i=0 ; i < part.matrica.length ; i ++ ) {
			part.matrica[i][part.matrica[0].length - 1 ] = false ; 
			
		}
		repaint () ; 
		
	}
	
}