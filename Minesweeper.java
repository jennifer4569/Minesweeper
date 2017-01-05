import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container pane;
    private JLabel j;
    private JTextField t;
    private JButton[][] gridButton;
    private Block[][] gridBlock;
    
    public Minesweeper(int width, int length){
	this.setTitle("Minesweeper");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new GridLayout(width,length));

	gridButton = new JButton[width][length];
	gridBlock = new Block[width][length];
	
	setupGrid(width,length);
	findAndSetNumMines(width,length);
    }

    public void setupGrid(int w, int l){
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		gridBlock[x][y] = new Block(x, y);

		gridButton[x][y] = new JButton("" + gridBlock[x][y].getBomb());
		gridButton[x][y].addActionListener(this);
		//gridButton[x][y].setActionCommand("" + x + "," + y);
		
		pane.add(gridButton[x][y]);
	    }
	}
    }
    
    public void findAndSetNumMines(int w, int l){
	int store; //store will contain the number of mines around the block
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		store = findNumMines(x,y);
		gridBlock[x][y].setNumMines(store);
		gridButton[x][y].setActionCommand("" + store);
	    }
	}
    }
    public int findNumMines(int x, int y){
	int count = 0;
	//...BODY HERE!
	return count;
    }


    
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	System.out.println(event);
	//gridButton[0][0].setText("HI!");
    }
	
    public static void main(String[] args){
	Minesweeper g  = new Minesweeper(5,5);
	g.setVisible(true);
    }
}
