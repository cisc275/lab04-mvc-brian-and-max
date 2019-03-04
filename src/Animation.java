import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Animation extends JPanel {

    final int frameCount = 10;
    final int directionCount = 8;
    int picNum = 0;
    int picIndex = 0;
    BufferedImage[] pics;
    BufferedImage[][] animations;
    int xM = 1;
    int yM = 1;
    int xloc = 0;
    int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    final static int NORTHEAST = 3;
    final static int NORTHWEST = 4;
    final static int SOUTHEAST = 0;
    final static int SOUTHWEST = 6;

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	g.drawImage(animations[picIndex][picNum], xloc+=(xIncr*xM), yloc+=(yIncr*yM), Color.gray, this);
    	
		//Be sure that animation picture direction matches what is happening on screen.
    	if (xloc + imgWidth >= frameWidth) {
			xM = -1;
			
		} else if (xloc + (frameHeight / 10) <= 0) {
			xM = 1;
		}
    	
    	if (yloc + imgHeight >= frameHeight) {
			yM = -1;
		} else if (yloc + (frameHeight / 10) <= 0) {
			yM = 1;
		}
    	
    	if (xM == 1 && yM == 1) {
    		picIndex = SOUTHEAST;
    	}
    	if (xM == -1 && yM == -1) {
    		picIndex = NORTHWEST;
    	}
    	if (xM == 1 && yM == -1) {
    		picIndex = NORTHEAST;
    	}
    	if (xM == -1 && yM == 1) {
    		picIndex = SOUTHWEST;
    	}
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++) {
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    //Constructor: get image, segment and store in array
    public Animation() {
    	
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
}