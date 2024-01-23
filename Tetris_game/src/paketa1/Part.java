package paketa1;
import java.awt.* ; 
import javax.swing.* ; 
import java.awt.event.* ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random ; 


public class Part {
	int m ; 
	int n ; 
	boolean [][] matrica ; 
	Random rand = new Random () ; 
	
	Part (int m , int n ) {
		matrica = new boolean [m][n] ; 
		setMatrica () ; 
		
	}
	public void setMatrica () {
		for(int i=0 ; i < matrica.length ; i ++ ) {
			for(int j=0 ; j < matrica[i].length ; j ++ ) {
				matrica[i][j] = rand.nextBoolean() ; 
				
				
			}
		}
	}
	
	public void setM (int m ) {
		
		matrica = null ; 
		this.m = m ; 
		matrica = new boolean [m][n] ; 
	}
	public void setN (int n ) {
		matrica = null ; 
		this.n = n ; 
		matrica = new boolean [m][n] ; 
		
	}
	public void set (int m , int n ) {
		matrica = null ; 
		this.m = m ; 
		this.n = n ; 
		matrica = new boolean [m][n] ; 
		
	}
	public boolean [][] getPart () {
		return matrica ; 
		
	}
	
}
