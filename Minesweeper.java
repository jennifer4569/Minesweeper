import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.lang.Integer.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container pane;
    // private int width;
    //  private int length;
    private JButton[][] gridButton;
    private Block[][] gridBlock;
    private int tempx;
    private int tempy;

    private String buttonXY;
    
    public Minesweeper(int width, int length){
	this.setTitle("Minesweeper");
	this.setSize(600,600);
	this.setLocation(100,100);
	this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	//this.width = width;
	//this.length = length;
	
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
		
		gridButton[x][y].setActionCommand("" + x + "," + y);
	        
		pane.add(gridButton[x][y]);
		gridButton[x][y].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			    buttonXY = findButton(e.getX(), e.getY());
			    tempx = Integer.parseInt(buttonXY.substring(0,buttonXY.indexOf(",")));
			    tempy = Integer.parseInt(buttonXY.substring(buttonXY.indexOf(",") + 1));
			    System.out.println("" + pane.getBounds().height + "," + pane.getBounds().width);
			    System.out.println("Expected: " + e.getX() + "," + e.getY() + " Results: "+ tempx + "," + tempy);
			    if(SwingUtilities.isRightMouseButton(e) && gridBlock[tempx][tempy].getMarked()){
				gridButton[tempx][tempy].setText("");
				gridBlock[tempx][tempy].setMarked(false);
			    }
			    else if(SwingUtilities.isRightMouseButton(e) && !gridBlock[tempx][tempy].getMarked()){
				gridButton[tempx][tempy].setText("FLAG");
				gridBlock[tempx][tempy].setMarked(true);
			    }
			}
		    });
	        
	    }
	}
    }

    public String findButton(int x, int y){
	String store = "";
	for (int i = 0; i < 8; i++){
	    if (x > i*pane.getBounds().height/8 && x < (i + 1)*pane.getBounds().height/8){
		store += "" + i;
		i = 8;
	    }
	}
	for (int i = 0; i < 8; i++){
	    if (y > i*pane.getBounds().height/8 && y < (i + 1)*pane.getBounds().height/8){
		store += "," + i;
		i = 8;
	    }
	}
	
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

    //getters
    /*    public void setHeight(int height){
	this.height = height;
    }
    public void setWidth(int width){
        this.width = width;
	}*/
    
    public static void main(String[] args){
	Minesweeper g  = new Minesweeper(8,8);
	g.setVisible(true);
    }
	}
