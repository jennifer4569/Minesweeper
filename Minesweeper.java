import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.lang.Integer.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container pane;
    private JLabel j;
    private JTextField t;
    private JButton[][] gridButton;
    private Block[][] gridBlock;
    private int tempx;
    private int tempy;
    
    public Minesweeper(int width, int length){
	this.setTitle("Minesweeper");
	this.setSize(600,600);
	this.setLocation(100,100);
	this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new GridLayout(width,length));

	gridButton = new JButton[width][length];
	gridBlock = new Block[width][length];
	
	setupGrid(width,length);
	findAndSetNumMines(width,length);
    }

    public void setupGrid(int w, int l){
	String store;
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		gridBlock[x][y] = new Block(x, y);

		gridButton[x][y] = new JButton("" + gridBlock[x][y].getBomb());
		gridButton[x][y].addActionListener(this);
		
		gridButton[x][y].setActionCommand("" + x + "," + y);
	        
		pane.add(gridButton[x][y]);
		gridButton[x][y].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			    store = findButton(e.getX(), e.getY());
			    tempx = Integer.parseInt(store.substring(0,store.indexOf(",")));
			    tempy = Integer.parseInt(store.substring(store.indexOf(",") + 1));

			    System.out.println("" + tempx + "," + tempy);
			    /*   if(SwingUtilities.isRightMouseButton(e) && gridBlock[tempx][tempy].getMarked()){
				gridButton[tempx][tempy].setText("");
				gridBlock[tempx][tempy].setMarked(false);
			    }
			    else if(SwingUtilities.isRightMouseButton(e) && !gridBlock[tempx][tempy].getMarked()){
				gridButton[tempx][tempy].setText("FLAG");
				gridBlock[tempx][tempy].setMarked(true);
				}*/
			}
		    });
	        
	    }
	}
    }

    public String findButton(int x, int y){
	String store = "2,3";
	
	
	return store;
    }
    
    public void findAndSetNumMines(int w, int l){
	int store; //store will contain the number of mines around the block
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		store = findNumMines(x,y);
		gridBlock[x][y].setNumMines(store);
	    }
	}
    }
    public int findNumMines(int x, int y){
	int count = 0;
	try{
	    if(gridBlock[x + 1][y].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x + 1][y - 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x + 1][y + 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x][y + 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x][y - 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x - 1][y].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x - 1][y + 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[x - 1][y - 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	return count;
    }
    
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	int x;
	int y;
	System.out.println(event);
        
	x = Integer.parseInt(event.substring(0,event.indexOf(",")));
	y = Integer.parseInt(event.substring(event.indexOf(",") + 1));
	if(gridBlock[x][y].getBomb()){
	    gridButton[x][y].setText("DEAD!");
	}
	else{
	    if(gridBlock[x][y].getNumMines() == 0){
		gridButton[x][y].setText("");
	    }
	    else{
		gridButton[x][y].setText("" + gridBlock[x][y].getNumMines());
	    }
	}
	gridBlock[x][y].setRevealed(true);
    }
	
    public static void main(String[] args){
	Minesweeper g  = new Minesweeper(8,8);
	g.setVisible(true);
    }
	}
