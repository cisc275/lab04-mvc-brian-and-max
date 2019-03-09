import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
@SuppressWarnings("serial")
public class View extends JPanel {
	JFrame frame = new JFrame();
	
	private int frameWidth = 500;
    private int frameHeight = 300;
    private int imgWidth = 165;
    private int imgHeight = 165;
    
    BufferedImage[] pics;
    BufferedImage[][] animations;
    
    final int frameCount = 10;
    final int directionCount = 8;
    int picNum = 0;
    
    private int x;
    private int y;
    private int xM;
    private int yM;
    private int dir;
    
    public int getWidth() {
    	return frameWidth;
    }
    
    public int getHeight() {
    	return frameHeight;
    }
    
    public int getImageWidth() {
    	return imgWidth;
    }
    
    public int getImageHeight() {
    	return imgHeight;
    }
    
    public int getFrameWidth() {
		return frameWidth;
	}
    
    public int getFrameHeight() {
    	return frameHeight;
    }
    
    public View() {
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	animations = new BufferedImage[directionCount][frameCount];
    	BufferedImage[] indexArray = new BufferedImage[directionCount];
    	indexArray[0] = createImage("src/orc_animation/orc_forward_southeast.png");
    	indexArray[1] = createImage("src/orc_animation/orc_forward_east.png");
    	indexArray[2] = createImage("src/orc_animation/orc_forward_north.png");
    	indexArray[3] = createImage("src/orc_animation/orc_forward_northeast.png");
    	indexArray[4] = createImage("src/orc_animation/orc_forward_northwest.png");
    	indexArray[5] = createImage("src/orc_animation/orc_forward_south.png");
    	indexArray[6] = createImage("src/orc_animation/orc_forward_southwest.png");
    	indexArray[7] = createImage("src/orc_animation/orc_forward_west.png");
    	
    	for (int i = 0; i < directionCount; i++) {
    		for (int j = 0; j < frameCount; j++) {
				animations[i][j] = indexArray[i].getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
			}
    	}
    }
    
    
    private BufferedImage createImage(String name) {
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(name));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}
    
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	g.drawImage(animations[this.dir][picNum], this.x, this.y, Color.gray, this);
    }
    
    public void update(int x, int y, int d) {
    	try {
	    	this.x = x;
	    	this.y = y;
	    	this.dir = d;
	    	this.repaint();
	    	frame.getContentPane().add(this);
	    	frame.setBackground(Color.gray);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(frameWidth, frameHeight);
	    	frame.setVisible(true);
	    	Thread.sleep(100);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}