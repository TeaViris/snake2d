import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthOfSnake = 3;
	private int score = 0;
	
	private Timer timer;
	private int delay = 100;
	private int moves = 0;
	
	private int [] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,650,675,700,725,750,775,800,825,850};
	private int [] enemyYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	private ImageIcon enemyImage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private ImageIcon snakeimage;
	private ImageIcon titleImage;
	
	public int hScore = 0;
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		
		if(moves == 0)
		{
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}
		
		// drawing title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//drawing the title image
		titleImage = new ImageIcon("title.jpg");
		titleImage.paintIcon(this,g,25,11);
		
		//draw border for gameplay
		
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 557);
		
		//drawing background for the gameplay
		
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//drawing scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " +score, 80,30);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: " +lengthOfSnake, 80,50);
		
		
		rightmouth = new ImageIcon("right.png");
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int a=0;a<lengthOfSnake;a++)
		{
			if(a==0 && right)
			{
				rightmouth = new ImageIcon("right.png");
				rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a==0 && left)
			{
				leftmouth = new ImageIcon("left.png");
				leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a==0 && down)
			{
				downmouth = new ImageIcon("down.png");
				downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a==0 && up)
			{
				upmouth = new ImageIcon("up.png");
				upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a!=0)
			{
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
		}
		
		enemyImage = new ImageIcon("enemy.png");
		
		//check if head collides with enemy
		if(enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0])
		{
			score++;
			lengthOfSnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		enemyImage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
		
		if(score>hScore)
		{
			hScore = score;
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("High Score: " +hScore, 780,50);
			
		
		for(int b = 1;b<lengthOfSnake;b++)
		{
			if(snakeXlength[b] == snakeXlength[0] && snakeYlength[b] == snakeYlength[0])
			{
				right = left = up = down = false;
				
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("GAME OVER NOOB", 300, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.PLAIN, 20));
				g.drawString("Space to Restart", 350, 340);
				
			}
			
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		if(right) {
			
			for(int r = lengthOfSnake-1;r>=0; r--)
			{
				snakeYlength[r+1] = snakeYlength[r];
			}
			
			for(int r =lengthOfSnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeXlength[r]=snakeXlength[r] + 25;
				}
				else {
					snakeXlength[r]=snakeXlength[r-1];
				}
				
				if(snakeXlength[r] > 850)
				{
					snakeXlength[r] = 25;
				}
			}
			
			repaint();
		}
		
		if(left) {
			for(int r = lengthOfSnake-1;r>=0; r--)
			{
				snakeYlength[r+1] = snakeYlength[r];
			}
			
			for(int r =lengthOfSnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeXlength[r]=snakeXlength[r] - 25;
				}
				else {
					snakeXlength[r]=snakeXlength[r-1];
				}
				
				if(snakeXlength[r] < 25)
				{
					snakeXlength[r] = 850;
				}
			}
			
			repaint(); 
			
		}
		if(up) {
			for(int r = lengthOfSnake-1;r>=0; r--)
			{
				snakeXlength[r+1] = snakeXlength[r];
			}
			
			for(int r =lengthOfSnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeYlength[r]=snakeYlength[r] - 25;
				}
				else {
					snakeYlength[r]=snakeYlength[r-1];
				}
				
				if(snakeYlength[r] < 75)
				{
					snakeYlength[r] = 625;
				}
			}
			
			repaint(); 
	
		}
		if(down) {
			for(int r = lengthOfSnake-1;r>=0; r--)
			{
				snakeXlength[r+1] = snakeXlength[r];
			}
			
			for(int r =lengthOfSnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeYlength[r]=snakeYlength[r] + 25;
				}
				else {
					snakeYlength[r]=snakeYlength[r-1];
				}
				
				if(snakeYlength[r] > 625)
				{
					snakeYlength[r] = 75;
				}
			}
			
			repaint(); 
	
	
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyChar() == KeyEvent.VK_SPACE)
		{
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left)
			{
				right = true;
			}
			
			else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right)
			{
				left = true;
			}
			
			else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down)
			{
				up = true;
			}
			
			else {
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up)
			{
				down = true;
			}
			
			else {
				down = false;
				up = true;
			}
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
