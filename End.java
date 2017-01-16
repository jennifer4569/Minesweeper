import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class End extends JFrame implements ActionListener{
    private Container pane;

    public End(){
	this.setTitle("Minesweeper");
	this.setSize(400,200);
	this.setLocation(0,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	JButton newgame = new JButton("New Game");
	newgame.addActionListener(this);
	newgame.setActionCommand("new");
	newgame.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel name = new JLabel("MINESWEEPER");
	name.setFont(new Font("FuturaBlack BT", Font.BOLD, 40));
	name.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel msg = new JLabel("You Lost!");
	msg.setAlignmentX(Component.CENTER_ALIGNMENT);

	pane.add(name);
	pane.add(msg);
	pane.add(newgame);
    }
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	Main g = new Main();
	g.setVisible(true);
	dispose();
    }
    
    
}
