import java.awt.Color;

/**
 * This file organize and keep track of the data related to the map. It represents a 2D array of Color.
 *
 * @author: Yue Kuang 
 * @version: 2017Spring FEB 12th. CSC212.Section2
 */

public class MapGrid{
	// fields for height,width, and an array for color
	private int height;
	private int width;
	private Color color;
	private Color[][] dimension;

	//Constructor for Height and Width of the object
	public MapGrid(int h, int w, Color c){
		height=h;
		width=w;
		color=c;
		dimension=new Color[width][height];
		for(int i=0;i<dimension.length;i++){
			for(int j=0;j<dimension[0].length;j++){
				dimension[i][j]= c; //setting default color
			}
		}
	}

	/** Accessor for height */
	public int getHeight() {
	 	return height;}

	 // Manipulater for height

	public void setHeight(int h){
		height=h;
	}

	// Accessor for width/
	public int getWidth(){
		return width;
	}

	//Manipulator for width
	public void setWidth(int w){
		width=w;
	}

	//Accesor for array
	public Color[][] getArray(){
		return dimension;
	}

	// method to alter the map
	public void setColor(int x, int y, Color c){
		dimension[x][y]=c;
	}
}