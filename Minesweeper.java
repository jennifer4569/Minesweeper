import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Image.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container grid;
    private JMenuBar menuBar;
    private JButton[][] gridButton;
    private Block[][] gridBlock;
    private int width;
    private int length;
    private int tempx;
    private int tempy;
    private String buttonXY;

    private int currentFlags;
    private int maxFlags;
    private JLabel flagCount;
    ImageIcon icon = new ImageIcon("flag.jpg");

    
    public Minesweeper(int width, int length){
	gridButton = new JButton[length][width];
	gridBlock = new Block[length][width];
	menuBar = new JMenuBar();
	this.width = width;
	this.length = length;
	
        setTitle("Minesweeper");
	// if(width > length){
	//     setSize(length * 50, width * 50); //CHECK TO SEE IF THE SIZE IS BIG ENOUGH FOR THE SCREEN (LATER)
	// }
	// else{
	//     setSize(width * 50, length * 50);
	// }
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	if(screenSize.getWidth() < screenSize.getHeight()){
	    setSize((int)screenSize.getHeight() / 2, (int)screenSize.getHeight() / 2);
	}
	else{
	    setSize((int)screenSize.getWidth() / 2 , (int)screenSize.getWidth() / 2);
	}
	setLocation(0,0);
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
        grid = getContentPane();
        grid.setLayout(new GridLayout(width,length));
	
	setupGrid(width,length);
	findAndSetNumMines(width,length);


	setJMenuBar(menuBar);
	this.setupMenuBar();
    }

    public void setupMenuBar(){
	JMenu newGameButton = new JMenu("New Game");
	JMenuItem beginnerButton = new JMenuItem("Beginner (8x8)");
	beginnerButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Minesweeper a  = new Minesweeper(8,8);
		    a.setVisible(true);
		    dispose();
		}
	    });
	JMenuItem mediumButton = new JMenuItem("Medium (16x16)");
        mediumButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Minesweeper a  = new Minesweeper(16,16);
		    a.setVisible(true);
		    dispose();
		}
	    });
	JMenuItem hardButton = new JMenuItem("Hard (20x20)");
        hardButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    Minesweeper a  = new Minesweeper(20,20);
		    a.setVisible(true);
		    dispose();
		}
	    });
	//ADD CUSTOM GAME BUTTON HERE

	JMenuItem playSameBoardButton = new JMenuItem("Play Same Board");
	playSameBoardButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    //ADD FUNCTION TO UNREVEAL ALL REVEALED SPACES
		}
	    });
	
	newGameButton.add(beginnerButton);
	newGameButton.add(mediumButton);
	newGameButton.add(hardButton);
	newGameButton.add(playSameBoardButton);

	JButton optionButton = new JButton("Options");
        optionButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    //ADD FUNCTION TO HAVE A POP UP WINDOW SHOWING OPTIONS
		}
	    });

	JButton howToPlayButton = new JButton("How to Play");
        howToPlayButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    //ADD FUNCTION TO HAVE A POP UP SHOWING HOW TO PLAY INSTRUCTIONS
		}
	    });

        currentFlags = 0;
        maxFlags = findMaxFlags(gridBlock); //CHANGE LATER THIS ISN'T CORRECT
        flagCount = new JLabel("Flag Count: " + currentFlags + "/ " + maxFlags);

	menuBar.add(newGameButton);
	menuBar.add(optionButton);
	menuBar.add(howToPlayButton);
	menuBar.add(flagCount);
    }
    
    public int findMaxFlags(Block[][] blockAry){
	int count = 0;
	for(int r = 0; r < blockAry.length; r++){
	    for (int c = 0; c < blockAry[r].length; c++){
		if (blockAry[r][c].getBomb()){
		    count++;
		}
	    }
	}
	return count;
    }
    public void setupGrid(int w, int l){
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		gridBlock[y][x] = new Block(x, y);
		
		gridButton[y][x] = new JButton("");
		gridButton[y][x].addActionListener(this);


		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Font font = new Font("Arial", Font.PLAIN, (int)screenSize.getWidth() / (8 * w));
		gridButton[y][x].setFont(font);

		gridButton[y][x].setActionCommand("" + x + "," + y);
	        
		grid.add(gridButton[y][x]);
		gridButton[y][x].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			    Point p = new Point(e.getXOnScreen(), e.getYOnScreen());
			    SwingUtilities.convertPointFromScreen(p,(Component)grid);
			    buttonXY = findButton(p.getX(),p.getY());
			    tempx = Integer.parseInt(buttonXY.substring(0,buttonXY.indexOf(",")));
			    tempy = Integer.parseInt(buttonXY.substring(buttonXY.indexOf(",") + 1));
			    
			    if(SwingUtilities.isRightMouseButton(e) && gridBlock[tempy][tempx].getMarked() && !gridBlock[tempy][tempx].getRevealed()){
				gridButton[tempy][tempx].setText("");
				gridButton[tempy][tempx].setIcon(null);
				gridBlock[tempy][tempx].setMarked(false);

			        currentFlags -= 1;
				flagCount.setText("Flag Count: " + currentFlags + "/ " + maxFlags);
			    }
			    else if(SwingUtilities.isRightMouseButton(e) && !gridBlock[tempy][tempx].getMarked() && !gridBlock[tempy][tempx].getRevealed()){
				gridButton[tempy][tempx].setText("");
				Image img = icon.getImage();
			        img = img.getScaledInstance((grid.getBounds().width/width),(grid.getBounds().height/length),java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				gridButton[tempy][tempx].setIcon(icon);
				gridBlock[tempy][tempx].setMarked(true);

			        currentFlags += 1;
				flagCount.setText("Flag Count: " + currentFlags + "/ " + maxFlags);

			    }
			}
		    });
	        
	    }
	}
    }
    

    public String findButton(double x, double y){
	String store = "";
	for (int i = 0; i < width; i++){
	    if (x >= i*grid.getBounds().width/width && x < (i + 1)*grid.getBounds().width/width){
		store += "" + i;
		i = width;
	    }
	}
	for (int i = 0; i < length; i++){
	    if (y >= i*grid.getBounds().height/length && y < (i + 1)*grid.getBounds().height/length){
		store += "," + i;
		i = length;
	    }
	}
	
	return store;
    }
    
    public void findAndSetNumMines(int w, int l){
	int store; //store will contain the number of mines around the block
	for(int y = 0; y < l; y++){
	    for(int x = 0; x < w; x++){
		store = findNumMines(x,y);
		gridBlock[y][x].setNumMines(store);
	    }
	}
    }
    public int findNumMines(int x, int y){
	int count = 0;
	try{
	    if(gridBlock[y + 1][x].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y + 1][x - 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y + 1][x + 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y][x + 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y][x - 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y - 1][x].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y - 1][x + 1].getBomb()){
		count++;
	    }
	}
	catch(IndexOutOfBoundsException e){
	}
	try{
	    if(gridBlock[y - 1][x - 1].getBomb()){
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
	gridButton[y][x].setIcon(null);
	gridButton[y][x].setBackground(Color.WHITE);
	gridButton[y][x].setContentAreaFilled(false);
	gridButton[y][x].setOpaque(true);
	if(gridBlock[y][x].getBomb()){
	    gridButton[y][x].setText("DEAD!");
	}
	else{
	    if(gridBlock[y][x].getNumMines() == 0){
		gridButton[y][x].setText("");
	    }
	    else{
		gridButton[y][x].setText("" + gridBlock[y][x].getNumMines());
	    }
	}
	gridBlock[y][x].setRevealed(true);
    }
    
    public static void main(String[] args){
	Minesweeper g  = new Minesweeper(8,8);
	g.setVisible(true);
    }
}
