import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    private Container pane;
    
    public Main(){
	this.setTitle("Minesweeper");
	this.setSize(400,600);
	this.setLocation(0,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton easy = new JButton("Easy\n8x8 Puzzle");
	easy.addActionListener(this);
	easy.setActionCommand("easy");
	
	JButton medium = new JButton("Medium\n16x16 Puzzle");
	medium.addActionListener(this);
	medium.setActionCommand("medium");
	
	JButton hard = new JButton("Hard\n20x20 Puzzle");
	hard.addActionListener(this);
	hard.setActionCommand("hard");
	
	pane.add(easy);
	pane.add(medium);
	pane.add(hard);
    }
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("easy")){
	    Minesweeper g = new Minesweeper(8,8);
	    g.setVisible(true);
	    dispose();
	}
	if(event.equals("medium")){
	    Minesweeper g = new Minesweeper(16,16);
	    g.setVisible(true);
	    dispose();
	}
	if(event.equals("hard")){
	    Minesweeper g = new Minesweeper(20,20);
	    g.setVisible(true);
	    dispose();
	}
    }
    public static void main(String[] args){
    }
	
}
