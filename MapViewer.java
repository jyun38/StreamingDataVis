import java.awt.*;
import javax.swing.*;

/**
 * This file keep tracks of data in MapGrid, also it sets the size of my map, including start point and end point. 
 * @author: Yue Kuang 
 * @version: 2017Spring FEB 12th. CSC212.Section2
 */

//MapViewer is JComponent
class MapViewer extends JComponent{
	//declaring fields 
	private MapGrid map;
	private int startX;
	private int startY;
	private int scale;
	public static final int WINDOWS_SIZE=800;
 
	//Constructor MapViewer has arguments map, x and y coordinates of the starting point, and also scale
	public MapViewer(MapGrid map, int x, int y, int z){
		super();
		this.map=map; 
		startX=x;
		startY=y;
		scale=z;
		setMinimumSize(new Dimension(1,1));
		setPreferredSize(new Dimension(WINDOWS_SIZE,WINDOWS_SIZE));
	}

	/** Accessor for MapGrid */
	public MapGrid getMap() {
		return map;
	}

	/** Accessor for StartX */
	public int getStartX() {
		return startX;
	}

	/** Manipulator for radius */
	public void setStartX(int x) {
		startX = x;
		repaint();
	}

	/** Accessor for StartY */
	public int getStartY() {
		return startY;
	}

	/** Manipulator for radius */
	public void setStartY(int y) {
		startY=y;
		repaint();
	}

	/** Accessor for Scale */
	public int getScale() {
		return scale;
	}

	/** Manipulator for radius */
	public void setScale(int z) {
		scale=z;
		repaint();
	}

	/**
	*  Draws the map in windows from the starting point
	*
	*  
	*/
	public void paintComponent(Graphics g) {
		for (int i=0;i<map.getArray().length;i++){
			for(int j=0;j<map.getArray()[0].length;j++){
				g.setColor(map.getArray()[i][j]);
				g.fillRect((startX+(scale*i)),(startY+(scale*j)),scale,scale);
			}
		}
	}

}