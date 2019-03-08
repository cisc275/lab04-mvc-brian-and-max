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

	//private BufferedImage[] pics;
    private BufferedImage[][] animations;
    private Model game;
    
    private final int frameCount = 10;
    private final int directionCount = 8;
    
    private final int frameWidth = 500;
    private final int frameHeight = 300;
    private final int imgWidth = 165;
    private final int imgHeight = 165;
    
    private int picNum = 0;
    
    public static void main(String[] args) {
    	Controller c = new Controller();
    	c.start();
    }
    
    public View() {
    	JFrame frame = new JFrame();
		game = new Model(frameWidth, frameHeight, imgWidth, imgHeight);
    	frame.getContentPane().add(game);
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
    
	public void update(int x, int y, int direction) {
    		game.setXLocation(x);
    		game.setYLocation(y);
			this.repaint();
	}
	
	//Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	g.drawImage(animations[game.getDirect()][picNum], (game.getXIncr()*game.getXM()), (game.getYIncr()*game.getYM()), Color.gray, this);
    }
	
	//Read image from file and return
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
	
	public int getWidth() {
		return this.frameWidth;
	}
	
	public int getHeight() {
		return this.frameHeight;
	}
	
	public int getImageWidth() {
		return this.imgWidth;
	}
	
	public int getImageHeight() {
		return this.imgHeight;
	}
}