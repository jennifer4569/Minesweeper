import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Image.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container pane;
    private JMenuBar menuBar;
    private JButton[][] gridButton;
    private Block[][] gridBlock;
    private int width;
    private int length;
    private int tempx;
    private int tempy;
    private String buttonXY;
    ImageIcon icon = new ImageIcon("flag.jpg");

    
    public Minesweeper(int width, int length){
	gridButton = new JButton[width][length];
	gridBlock = new Block[width][length];
	menuBar = new JMenuBar();
	this.width = width;
	this.length = length;
	
        setTitle("Minesweeper");
	setSize(600,600);
	setLocation(100,100);
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	setJMenuBar(menuBar);
	setupMenuBar();
	
	pane = getContentPane();
	pane.setLayout(new GridLayout(width,length));
	
	setupGrid(width,length);
	findAndSetNumMines(width,length);
    }

    public void setupMenuBar(){
	JMenu gameMenu = new JMenu("Game");
	menuBar.add(gameMenu);
	JMenuItem newGameAction = new JMenuItem("New Game");
	newGameAction.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    //ADD HOW TO MAKE A NEW GAME
		}
	    });
	gameMenu.add(newGameAction);
    }
    
    public void setupGrid(int w, int l){
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		gridBlock[x][y] = new Block(x, y);
		
		gridButton[x][y] = new JButton("");
		gridButton[x][y].addActionListener(this);
		
		gridButton[x][y].setActionCommand("" + x + "," + y);
	        
		pane.add(gridButton[x][y]);
		gridButton[x][y].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			    Point p = new Point(e.getXOnScreen(), e.getYOnScreen());
			    SwingUtilities.convertPointFromScreen(p,(Component)pane);
			    buttonXY = findButton(p.getX(),p.getY());
			    tempx = Integer.parseInt(buttonXY.substring(0,buttonXY.indexOf(",")));
			    tempy = Integer.parseInt(buttonXY.substring(buttonXY.indexOf(",") + 1));
			    
			    if(SwingUtilities.isRightMouseButton(e) && gridBlock[tempx][tempy].getMarked() && !gridBlock[tempx][tempy].getRevealed()){
				gridButton[tempx][tempy].setText("");
				gridButton[tempx][tempy].setIcon(null);
				gridBlock[tempx][tempy].setMarked(false);
			    }
			    else if(SwingUtilities.isRightMouseButton(e) && !gridBlock[tempx][tempy].getMarked() && !gridBlock[tempx][tempy].getRevealed()){
				gridButton[tempx][tempy].setText("");
				Image img = icon.getImage();
			        img = img.getScaledInstance((pane.getBounds().width/8),(pane.getBounds().height/8),java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				gridButton[tempx][tempy].setIcon(icon);
				gridBlock[tempx][tempy].setMarked(true);
			    }
			}
		    });
	        
	    }
	}
    }

    public String findButton(double x, double y){
	String store = "";
	for (int i = 0; i < 8; i++){
	    if (x >= i*pane.getBounds().width/8.0 && x < (i + 1)*pane.getBounds().width/8.0){
		store += "" + i;
		i = 8;
	    }
	}
	for (int i = 0; i < 8; i++){
	    if (y >= i*pane.getBounds().height/8.0 && y < (i + 1)*pane.getBounds().height/8.0){
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
	gridButton[x][y].setIcon(null);
	gridButton[x][y].setBackground(Color.WHITE);
	gridButton[x][y].setContentAreaFilled(false);
	gridButton[x][y].setOpaque(true);
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
