import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener{
    private Container pane;
    private JTextField dim;
    
    public Main(){
	this.setTitle("Minesweeper");
	this.setSize(400,600);
	this.setLocation(0,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JButton easy = new JButton("<html>Easy<br />8x8 Puzzle</html>");
	easy.addActionListener(this);
	easy.setActionCommand("easy");
	easy.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JButton medium = new JButton("<html>Medium<br />16x16 Puzzle</html>");
	medium.addActionListener(this);
	medium.setActionCommand("medium");
	medium.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JButton hard = new JButton("<html>Hard<br />20x20 Puzzle</html>");
	hard.addActionListener(this);
	hard.setActionCommand("hard");
	hard.setAlignmentX(Component.CENTER_ALIGNMENT);

	JLabel name = new JLabel("MINESWEEPER");
	name.setFont(new Font("FuturaBlack BT", Font.BOLD, 40));
	name.setAlignmentX(Component.CENTER_ALIGNMENT);

	JLabel custom = new JLabel("Custom size:");
	custom.setAlignmentX(Component.CENTER_ALIGNMENT);

	dim = new JTextField(10);
	dim.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton start = new JButton("Start Custom Game");
	start.addActionListener(this);
	start.setActionCommand("start");
	start.setAlignmentX(Component.CENTER_ALIGNMENT);

	
	pane.add(name);
	pane.add(easy);
	pane.add(medium);
	pane.add(hard);
	pane.add(custom);
	pane.add(dim);
	pane.add(start);

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
	if(event.equals("start")){
	    String textfield = dim.getText();
	    int num = Integer.parseInt(textfield);
	    if(num < 4){
		dim.setText("4");
		num = 4;
	    }
	    if(num > 20){
		dim.setText("20");
		num = 20;
	    }
	    Minesweeper g = new Minesweeper(num,num);
	    g.setVisible(true);
	    dispose();
	}
	    
    }
    public static void main(String[] args){
    }
	
}
