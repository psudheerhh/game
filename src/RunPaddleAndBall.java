import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


/*@author venkat 
 * @author bhargav
 * @author sudheer
 * RunPaddleAndBall class 
 * */

public class RunPaddleAndBall extends Applet implements Runnable, KeyListener,ActionListener {
	
	//@param count counts the number of blocks destroyed
	int count =1;
	Thread th = new Thread(this);
	//@param p is the paddle object
	Paddle p = new Paddle();
	
	Ball b = new Ball();
	//Ball b2 = new Ball(10, 10);
	private ArrayList<Block> blocks = new ArrayList<Block>();
	int max = 11;
	int my = 12;
	//Initial variables
	boolean going = true;//running the program condition
	Image dbImage;       // used for double buffering
	Graphics dbGraphics;
	
	boolean suspended = false;
	boolean display = false;
	boolean stopped ;
	Button c= new Button ("Start");
	Button d= new Button ("Pause");
	Button e = new Button ("Resume");
	Button f = new Button ("Stop");
	
	
	
	//this method initializes the applet
	@Override
	public void init(){
		
		this.setSize(800, 600);
		this.setBackground(Color.black);
		this.addKeyListener(this);
	    
		add(c);
		add(d);
		add(e);
		add(f);
		
		c.addActionListener(this);
		d.addActionListener(this);
		e.addActionListener(this);
		f.addActionListener(this);
		setFocusable(true);
		c.setFocusable(false);
		d.setFocusable(false);
		e.setFocusable(false);
		f.setFocusable(false);
		
		//blocks.add(new Block(25,100));
		blocks.add(new Block(75,100));
		blocks.add(new Block(150,100));
		blocks.add(new Block(225,100));
		blocks.add(new Block(300,100));
		blocks.add(new Block(375,100));
		blocks.add(new Block(450, 100));
		blocks.add(new Block(525, 100));
		blocks.add(new Block(600, 100));
		blocks.add(new Block(675, 100));
		//blocks.add(new Block(575, 100));
		
		blocks.add(new Block(75, 200));
		blocks.add(new Block(150, 200));
		blocks.add(new Block(225, 200));
		blocks.add(new Block(300, 200));
		blocks.add(new Block(375, 200));
		blocks.add(new Block(450, 200));
		blocks.add(new Block(525, 200));
		blocks.add(new Block(600, 200));
		blocks.add(new Block(675, 200));
		
		
		
	}
	
	@Override
	public void start(){
		//th.start();
		
	}
	
	@Override
	public void stop(){
		going = false;
		
	}
	
	@Override
	public void destroy(){
		going = false;
		
		
	}
	
	@Override
	public void run() {
		
		while(going){
			//b2.RealBall(this);
			//b.FakeBall(this,p);
			b.FakeBall(this, p);
			
			p.MovePaddle(this,b);
			//p1.x=163;
			//p1.y=276;
			//p1.MovePaddle(this, b);
			
			for(int i=0;i<(blocks.size()); i++){
				//b.CollideWithBlock(blocks.get(i));
				b.CollideWithBlock(blocks.get(i));
				
				if(!blocks.get(i).alive){
					blocks.remove(i);
				}
			}
			
			repaint();
			try{
							
				Thread.sleep(25);//50 frames per second
			} 
			catch(InterruptedException ie){
				
			}
			
		}
		synchronized(this){
			while(suspended)
				try{
					wait();
					if(stopped){
						break;}
				}
			catch(InterruptedException e){
				e.printStackTrace();
					}
				}
		}
		
	
	
	public void update(Graphics g){
		if(dbImage == null){
			dbImage = createImage(this.getSize().width,this.getSize().height);
			dbGraphics = dbImage.getGraphics();
		}
		dbGraphics.setColor(this.getBackground());
		dbGraphics.fillRect(0, 0,  this.getSize().width, this.getSize().height);
		
		dbGraphics.setColor(this.getForeground());
		paint(dbGraphics);
		
		g.drawImage(dbImage, 0, 0, this);
	}
	
	@Override
   public void paint(Graphics g){
	
		if(display == true){
	  p.paint_paddle(g);
	  b.paint_fake(g);
	  b.paint_fake(g);
	  
	  g.drawString("BREAK OUT GAME SCORE", 400, 50);
	  
	  StringBuilder sb = new StringBuilder();
	  sb.append("");
	  sb.append(Ball.score);
	  String strI = sb.toString();
	  
	  g.drawString(strI, 570, 50);
	  
	  //g.drawString("GAME OVER", 300, 300);
	  
	  if(blocks.size() == 0 ){
		  g.setColor(Color.RED);
		  System.out.println("Game over");
		 // music.play();
		  g.setColor(Color.BLACK);
		  
		  
		  g.drawString("GAME OVER \n YOU Won", 300, 300);
		  //g.drawString("Game over", 100, 100);
		  this.setBackground(Color.green);  
		  th.stop();
		  
	  }
	  
	  if(Ball.game_over == 1){
		 // g.setColor(Color.GREEN);
		  this.setBackground(Color.red);
		  g.drawString("GAME OVER YOU LOOSE", 300, 300);
		  th.stop();
	  }
	  
	 for(Block b:blocks){
		 b.paintBlock(g);
	 }}
	  //b2.paint_real(g);
	
	 //connection method used here
	 //connect();
	
	}
	
	
	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			p.goingleft=true;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			p.goingright=true;
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			p.goingleft=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			p.goingright=false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
		
	public synchronized void quit(){
		stopped =true;
		notify();
	}
	
	synchronized void suspend(){
		suspended =true;
	}
	synchronized void resume(){
		notify();
	}	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==c){
			th.start();
			display=true;
		}
		if(e.getSource()==d){
			th.suspend();
		}
		if(e.getSource()==this.e){
			th.resume();
		}
		if(e.getSource()==f){
			th.stop();
		}
	}
}

