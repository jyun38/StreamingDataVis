import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;        
import java.lang.reflect.*;       
import java.io.PrintWriter;

/**
 *  Class that read the image
 *
 *  @author  Yue Kuang and Ji Young Yun
 *  @version  StreamingData June 30th, 2017
 */

public class MapGUIST{
    private MapViewer view;
    private MapGrid map;
    public MapGUIST(Color[][] colors){
        map= new MapGrid(200,200,Color.yellow);
        //set color for each pixel according to the image's color
        for(int i=0;i<200;i++){
            for(int j=0;j<200;j++){
                  map.setColor(i,j,colors[i][j]);
            }
        }
        view= new MapViewer(map,0,0,4);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    public void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame1 = new JFrame("Map#1");
        try { 
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } 
        catch (Exception e) {
        }

        frame1.getContentPane().add(view);

        // Add components
        createComponents(frame1.getContentPane());
        // Display the windows:
        frame1.pack();
        frame1.setVisible(true);
    } 

    //Getting color from the image
    public static Color[][] loadPixelsFromImage(File file) throws IOException {
        int count = 0;
        int [][] rgbArray = new int [40000][5];
        BufferedImage img = ImageIO.read(file);
        Color[][] colors = new Color[img.getWidth()][img.getHeight()];
        for(int x=0; x<200;x++){
          for(int y=0;y<200;y++){
            colors[x][y] = new Color(img.getRGB(x, y));
            rgbArray[count][0] = x;
            rgbArray[count][1] = y;
            rgbArray[count][2] = colors[x][y].getRed();
            rgbArray[count][3] = colors[x][y].getBlue();
            rgbArray[count][4] = colors[x][y].getGreen();
            count +=1;
            System.out.println("Color["+x+"]["+y+"] = " + colors[x][y]);
            
          }
        }
        File outputFile = new File("outputRGB.csv");
        PrintWriter pw = new PrintWriter(outputFile);

        for (int i = 0; i < rgbArray.length; i++) {
                if (rgbArray[i] != null) {

                    String[] row = new String[rgbArray[i].length];
                    for (int j = 0; j < rgbArray[i].length; j++) {
                        row[j] = String.valueOf(rgbArray[i][j]);
                    }
                    pw.print(csvRow(row));
                    System.out.print(csvRow(row));
                }
            }

        pw.close();
        return colors;
    }
    //end of loadPixelsFromImage


    public static void main(String[] args) throws IOException {
        Color[][] colors = loadPixelsFromImage(new File("Lekagul_Roadways_1_.jpg"));
        final MapGUIST GUI = new MapGUIST(colors);
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            GUI.createAndShowGUI();
            }
        });
    }

    public static String csvRow(String[] array /*String... strings*/) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(",");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     *  Both types of app call this to set up the GUI contents.
     *
     *  @param pane  The pane of the JFrame of JApplet
     */
    public void createComponents(Container pane) {
        pane.setLayout(new FlowLayout());
        pane.add(view);
        view.addMouseListener(new MyMouseListener());
        view.addMouseWheelListener(new MyMouseListener());    
    }

    // Event Handler for mouse
    private class MyMouseListener extends MouseAdapter {
    //** Click event handler changing center of the map, making the center of the map stay the same//
        public void mouseClicked(MouseEvent e) {
            e.getPoint();
            int pointX = e.getPoint().x;
            int pointY =e.getPoint().y;
            System.out.println(pointX/4);
            System.out.println(pointY/4);
        }
    }
}