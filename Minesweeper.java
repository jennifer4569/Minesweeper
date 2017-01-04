import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame implements ActionListener{
    private Container pane;
    private JLabel j;
    private JTextField t;

    public Minesweeper(int x, int y){
	this.setTitle("Minesweeper");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new GridLayout(x,y));
	for(int i = 0; i < y; i ++){
	    for(int j = 0; j < x; j ++){
		JButton b = new JButton("" + j + "," + i);
		pane.add(b);
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
