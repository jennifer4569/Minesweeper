import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame implements ActionListener{
    public Minesweeper(){
	this.setTitle("Minesweeper!");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	makeGrid(5,5);
    }
    
    public void actionPerformed(ActionEvent e){
	
    }
    JButton[][] grid;
    public void makeGrid(int width, int length){
	this.setLayout(new GridLayout(width, length));
	grid = new JButton[width][length];
	for(int c = 0; c < length; c++){
	    for (int r = 0; r < width; r++){
		grid[r][c] = new JButton("(" + r + "," + c + ")");
		this.add(grid[r][c]);
	    }
	}
    }
    public static void main(String[] args){
	Minesweeper m = new Minesweeper();
	m.setVisible(true);
    }

}
