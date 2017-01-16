import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class How extends JFrame implements ActionListener{
    private Container pane;

    public How(){
	this.setTitle("Minesweeper");
	this.setSize(400,200);
	this.setLocation(0,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
	JLabel name = new JLabel("MINESWEEPER");
	name.setFont(new Font("FuturaBlack BT", Font.BOLD, 40));
	name.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel msg = new JLabel("How To Play:");
	msg.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel msg2 = new JLabel("<html>Click on the blocks and they will reveal the number of bombs<br /> surrounding them. To win, reveal all blocks without bombs. <br />Clicking on a block with a bomb will result in losing.</html>");
	msg2.setAlignmentX(Component.CENTER_ALIGNMENT);
	JButton close = new JButton("Close");
	close.setAlignmentX(Component.CENTER_ALIGNMENT);
	close.addActionListener(this);
	close.setActionCommand("Close");
	
	pane.add(name);
	pane.add(msg);
	pane.add(msg2);
	pane.add(close);
    }
    public void actionPerformed(ActionEvent e){
	setVisible(false);
    }
    
    
}
