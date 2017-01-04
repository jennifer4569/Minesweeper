import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container pane;
    private JLabel j;
    private JTextField t;
    private JButton[][] grid;

    public Minesweeper(int width, int length){
	this.setTitle("Minesweeper");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new GridLayout(width,length));

	grid = new JButton[width][length];
	for(int y = 0; y < length; y++){
	    for(int x = 0; x < width; x++){
	        grid[x][y] = new JButton("" + x + "," + y);
		pane.add(grid[x][y]);
	    }
	}
    }
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
    }
	
    public static void main(String[] args){
	Minesweeper g  = new Minesweeper(5,5);
	g.setVisible(true);
    }
}
