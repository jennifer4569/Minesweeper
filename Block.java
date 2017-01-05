import java.util.*;

public class Block{
    private int x; //x-cor
    private int y; //y-cor
    private boolean bomb; //true: has bomb; false: doesn't have bomb
    private boolean revealed; //true: is revealed; false: isn't revealed
    private boolean marked; //true: is marked; false: isn't marked
    private int numMines; //number of mines around the block 

    private Random randgen;
    
    public Block(int x, int y){
	this.x = x;
	this.y = y;
	revealed = false;
	marked = false;

        randgen = new Random();
	bomb = randgen.nextBoolean();
    }
    
    //getters
    public int getX(){
	return x;
    }
    public int getY(){
	return y;
    }
    public boolean getBomb(){
	return bomb;
    }
    public boolean getRevealed(){
	return revealed;
    }
    public boolean getMarked(){
	return marked;
    }
    public int getNumMines(){
	return numMines;
    }

    //setters
    public void setX(int x){
	this.x = x;
    }
    public void setY(int y){
	this.y = y;
    }
    public void setBomb(boolean bomb){
	this.bomb = bomb;
    }
    public void setRevealed(boolean revealed){
	this.revealed = revealed;
    }
    public void setMarked(boolean marked){
	this.marked = marked;
    }
    public void setNumMines(int numMines){
	this.numMines = numMines;
    }
    
}
