import java.awt.Color;
import java.awt.Graphics;

/*@author venkat 
 * @author bhargav
 * @author sudheer
 * Block class contains the variables needed for blocks
 * */
public class Block {
	
	/*
	 * @param x is the position of the block which are set while creating the object
	 * @param y is the position of the block which are set while creating the object*/
	int x;
	int y;
	/*
	 * @param width is the width of the block which are set to a particular value
	 * 	 * @param height is the height of the block which are set to a particular value*/
	int width=50;
	int height=30;
	
	//@param alive is a boolean variable which is set to false when destroyed and that is not drawn on the screen
	public boolean alive = true;
	
	//constructor for block initializing it to argument values
	public Block(int i, int j){
		this.x = i;
		this.y = j;
		
	}
	
	//paintBlock is the method for drawing on the screen
	public void paintBlock(Graphics g){
		//sets the painting color to white
		g.setColor(Color.white);
		//fills the rectangle at position x and y, with given width and height
		g.fillRect(x, y, width, height);
	}
}
