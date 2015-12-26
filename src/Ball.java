import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.io.*;

import javax.swing.JFileChooser;

import sun.audio.*;

/*@author venkat 
 * @author bhargav
 * @author sudheer
 * Ball class contains the variables needed for ball position
 * */
public class Ball {

	
	//Initialization for Fake ball
	/*
	 * @param x is the initial X position of the ball
	 * @param y is the initial Y position of the ball
	 * */
	private int x = 700;
	private int y = 100;
	/*
	 * @param radius is the radius of the ball object
	 * */
	private int radius = 15;
	/*
	 * @param dx is the amount of change in position X in each iteration of the loop
	 * @param dy is the amount of change in position Y in each iteration of the loop
	 * */
	public int dx = 5;
	public int dy = 5;
	/*
	 * @param game_over is a static integer variable*/
	public static int game_over=0;
	
	
	/*
	 * @param score is the counter which increments its value  by one whenever a block is destroyed*/
	public static int score=0;
	
	//Constructors which initializes the ball object with above parameters
	public Ball(){
		
	}
	
	//constructor which initializes the dx, dy with given arguments
	public Ball(int i, int j){  // initializing starting point

		/*
		 * @param dx,dy are the change in X, Y position in each iteration*/
		dx=i;
		dy=j;
	}
	
	
	//method which moves the position of the Ball and checks for intersection with wall
	public void FakeBall(RunPaddleAndBall R1, Paddle p){
		
		y = y + dy;
		x = x + dx;
		
		/*if(x == R1.getWidth() - radius - 1){
			dx*=xfriction;
		}*/
		
		if((x)>R1.getWidth()-radius-1){
			x = R1.getWidth()-radius-1;
			//dx*=0.05
			dx = -(dx);
		}
		else if( (x)<radius){
			x = radius;
			dx = -(dx);
		}
		else if( (y)<radius){
			y = radius;
			dy = -(dy);
		}
		
		/*else if((y+dy)>R1.getHeight()-radius-1){
			y = R1.getHeight()-radius-1;
			dy = -(dy);
		}*/
		
	}
	
	//setter method for setting x to new value
	public void setx(int nx){
		x=nx;
	}
	
	//setter method for setting y to new value
	public void sety(int ny){
		y=ny;
	}
	
	//setter method for setting radius to new value
	public void setradius(int nr){
		radius = nr;
	}
	
	//setter method for setting dy to new value
	public void setdy(int ndy){
		dy = ndy;
	}
	
	//setter method for setting dy to new value
	public void setdx(int ndx){
		dy = ndx;
	}
	
	
	//getter method for getting x position of the ball
	public int getx(){
		return x;
	}
	
	//getter method for getting y position of the ball
	public int gety(){
		return y;
	}
	
	//getter method for getting radius of the ball
	public int getradius(){
		return radius;
	}
	
	//getter method for getting dy of the ball
	public int getdy(){
		return dy;
	}
	
	//getter method for getting dx of the ball
	public int getdx(){
		return dx;
	}

	//this method checks collision of ball with the block by passing the block object
	public void CollideWithBlock(Block b){
	Rectangle2D.Double own = new Rectangle2D.Double(x-radius, y-radius, radius*2, radius*2);
	Rectangle2D.Double block = new Rectangle2D.Double(b.x, b.y, b.width, b.height);
	//new sound(this);
	
	//condition for ball missing the paddle and going down, which makes a static variable to "1" which will stop the thread
	if(y>650){
		//System.out.println("Working game over");
		game_over = 1;
	}
	
	//condition of ball hitting the block
	if(own.intersects(block)){
		//System.out.println("The X value is " + x);
		//System.out.println("The Y value is " + y);
		

		//setting block boolean to false
		b.alive=false;
		score++;
		//System.out.println("The new score is " + score);
		
	}
	
}
	
	
	public void paint_fake(Graphics g){
		   g.setColor(Color.white);
		   g.fillOval(x-radius, y-radius, radius*2,radius*2);
	   }
	
	public void paint_real(Graphics g){
		g.setColor(Color.white);
		//g.fillOval(x2-radius2, y2-radius2, radius2*2,radius2*2);
	}
	
}
