/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Model extends JPanel{

	private int xloc = 0;
    private int yloc = 0;
    private int xM = 1;
    private int yM = 1;
    private final int xIncr = 8;
    private final int yIncr = 2;
    
    private int picIndex = 0;
    
    private int fWidth;
    private int fHeight;
    private int aImgWidth;
    private int aImgHeight;
    
    final static int NORTHEAST = 3;
    final static int NORTHWEST = 4;
    final static int SOUTHEAST = 0;
    final static int SOUTHWEST = 6;
    
    public Model(int fWidth, int fHeight, int aImgWidth, int aImgHeight) {
    	this.fWidth = fWidth;
    	this.fHeight = fHeight;
    	this.aImgWidth = aImgWidth;
    	this.aImgHeight = aImgHeight;
    }
	
	public void updateLocationAndDirection() {
		xloc += (xIncr*xM);
		yloc += (yIncr*yM);
		
		//Be sure that animation picture direction matches what is happening on screen.
    	if (xloc + aImgWidth >= fWidth) {
			xM = -1;
			
		} else if (xloc + (fHeight / 10) <= 0) {
			xM = 1;
		}
    	
    	if (yloc + aImgHeight >= fHeight) {
			yM = -1;
		} else if (yloc + (fHeight / 10) <= 0) {
			yM = 1;
		}
	}
	
	public int getX() {
		return this.xloc;
	}
	
	public int getY() {
		return this.yloc;
	}
	
	public int getDirect() {
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
    	
    	return this.picIndex;
	}
	
	public int getXIncr() {
		return this.xIncr;
	}
	
	public int getYIncr() {
		return this.yIncr;
	}
	
	public int getXM() {
		return this.xM;
	}
	
	public int getYM() {
		return this.yM;
	}
	
	public void setXLocation(int x) {
		xloc = x;
	}
	
	public void setYLocation(int y) {
		yloc = y;
	}
}
