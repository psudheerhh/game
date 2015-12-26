import java.awt.Color;
import java.awt.Graphics;

/*@author venkat 
 * @author bhargav
 * @author sudheer
 * Paddle class contains the variables needed for paddle object
 * */
public class Paddle {

	//Initialization
	/*
	 * @param x is the initial position of the paddle
	 * @param y is the initial position of the paddle
	 * @param width is the width of the paddle
	 * @param height is the height of the paddle
	 * @param dx is the amount of change in X position of the paddle in each iteration*/
	
	int x = 363;
    int	y = 576;
	int width = 75;
	int height = 18;
	
	int dx = 8;
	/*
	 * @param goingright and goingleft is the boolean variable which is set to true using KeyListener */
	boolean goingright = false;
	boolean goingleft = false;
	
	//default constructor for paddle which initializes the paddle with above parameters
	public Paddle(){
		
	}
	
	//constructor of the paddle with given arguments
	public Paddle(int i, int j){
		this.x = i;
		this.y = j;
	}
	
	
	public void MoveLeft(){
		//if(dx - 1 > 20)
			//dx -= 1;
	}
	
	public void MoveRight(){
		//if(dx + 1 < 20)
			//dx += 1;
	}
	
	/*MovePaddle is the method through which control passes in each iteration and when key is pressed 
	boolean variables are set to true and x positions are changed
	*/
	public void MovePaddle(RunPaddleAndBall p1, Ball b){
		
		
		if(goingleft){
			x-=dx;
			
		}
		
		if(goingright){
			
			x+=dx;
		}
		
		//condition for setting x to no more decrease after reaching the edge
		if(x < 0){
			x=0;
		}
		if(x > p1.getWidth()-1-width){
			x = p1.getWidth()-1-width;
		}
		
		//checks for collision between paddle and ball
		PaddleCollision(b); 
		
	}
	
	
	private void PaddleCollision(Ball b){
		int bx = b.getx();
		int by = b.gety();
		int br = b.getradius();
		//this condition reflects the ball when ball hits the paddle 	
		if( (by+br>y) && (by+br<y+height) ){
			if((bx>x) &&(bx<x+width)){
				int newdy = b.getdy()*-1;
				b.sety(y-br);
				b.setdx(newdy);
				
				//b.dx+=5;
				//b.dy+=5;
			}
			
			//b.dx+=5;
			//b.dy+=5;
		}
	}
	
	//getX method returns the X position of the paddle
	/*
	 * @return returns the x position of the paddle*/
	public int getX(){
		return x;
	}
	
	/*
	 * @return returns the y position of the paddle*/
	public int getY(){
		return y;
	}
	
	/*
	 * @return returns the width of the paddle*/
	public int getWidth(){
		return width;
		
	}
	
	
	/*
	 * @return returns the height of the paddle*/
	public int getHeight(){
		return height;
	}
	
	
	//paints the paddle with white color and at a position x,y with given width and height
	public void paint_paddle(Graphics g){
		g.setColor(Color.white);
	    g.fillRect(x, y, width, height);
		
	}
	
}
