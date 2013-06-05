/**
 * @author Adrian Hołota&PiotrNowakowski
 */
package genethicprir;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Visualisation extends JPanel{
    
    private double length;
    private double width;
    private int sitPlaces;
    private int standPlaces;

    //constructor
    public Visualisation(double length, double width, int sitPlaces, int standPlaces){
        this.length = length * 100;
        this.width = width * 100;
        this.sitPlaces = sitPlaces;
        this.standPlaces = standPlaces;
    }

    @Override
    //in this function are drown: bus, sitplaces
    //and label on canvas
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        drawLabel(g2d);
        drawBus(g);
        drawSitPlaces(g);        
    }
    
    public void repaint(double length, double width, int sitPlaces, int standPlaces) {
    	this.length = length * 100;
        this.width = width * 100;
        this.sitPlaces = sitPlaces;
        this.standPlaces = standPlaces;
        
        repaint();
    }

    //this function sets preferred size as bus size
    //pluse 120px
    public void setPreferredSize() {
        Dimension preferredSize = new Dimension((int) length + 120, (int) width + 120);
        super.setPreferredSize(preferredSize);
    }
    
    //this function draws one sit place
    //use in drowing up to down
    private void drawSitPlace(Graphics g, int x, int y, int i, int j) {
        g.setColor(Color.black);
        g.fillRect(x - i * 70, y + j * 50, 50, 50);
        g.setColor(new Color(198, 226, 255));
        g.fillRect((x - i * 70) + 1, (y + j * 50) + 1, 48, 48);
    }
    
    //this function draws one sit place 
    //use in drowing down to up
    private void drawSitPlaceDown(Graphics g, int x, int y, int i, int j) {
        g.setColor(Color.black);
        g.fillRect(x - i * 70, y - j * 50, 50, 50);
        g.setColor(new Color(198, 226, 255));
        g.fillRect((x - i * 70) + 1, (y - j * 50) + 1, 48, 48);
    }
    
    //in this function are drown labels
    private void drawLabel(Graphics g2d) {
        int windowHeight = getHeight();
        int windowWidth = getWidth();
        
        StringBuilder lengthLabel = new StringBuilder();
        lengthLabel.append(length / 100);
        
        StringBuilder widthLabel = new StringBuilder();
        widthLabel.append(width / 100);
        
        g2d.drawString(widthLabel.toString(), 10, windowHeight / 2);
        g2d.drawString(lengthLabel.toString(), windowWidth / 2, 40);
        
        StringBuilder sizeLabel = new StringBuilder("Wymiary: ");
        sizeLabel.append(length/100);
        sizeLabel.append(" m x ");
        sizeLabel.append(width/100);
        sizeLabel.append(" m");
        
        StringBuilder placesLabel = new StringBuilder("Miejsca siedzące: ");
        placesLabel.append(sitPlaces);
        placesLabel.append(", miejsca stojące: ");
        placesLabel.append(standPlaces);
        
        g2d.drawString(sizeLabel.toString(), 10, 15);   
        g2d.drawString(placesLabel.toString(), windowWidth / 2 - 120, windowHeight - 20);
    }
    
    //function draws a bus 
    private void drawBus(Graphics g) {
        g.drawRect(60, 60, (int) length, (int) width);
        g.drawLine(30, 60, 30, (int) width + 60);
        g.drawLine(60, 40, (int) length + 60, 40);
    }
    
    //this is mainly function to draw sit places
    private void drawSitPlaces(Graphics g) {
        boolean upToDown = true;
        int sum = 150;
        int x = 0;
        int y = 0;
        int sitInRow = 0;
        int xStart = (int) (length) + 10;       //start position for x
        int yStart = 60;                        //start position for y
        int door = (int) ((length - 150) / 70); //where should be doors
        int corridor = getCorridor();           //where should be corridor
        
        for(int i = 0; i < sitPlaces; i++) {
            sum = sum + 70;
            //jump over 70 px and leave space for doors
            if((sitInRow == 1 || sitInRow == door / 2) && y < corridor - 1 && upToDown == true) {
                x++;
                sum = sum + 70;
            }
            //draws sit places in row
            if(sum < length) {
                if(upToDown == true) {
                    drawSitPlace(g, xStart, yStart, x, y);      //before corridor
                    x++;
                    sitInRow++;
                } else {
                    drawSitPlaceDown(g, xStart, yStart, x, y);  //behind corridor
                    x++;
                    sitInRow++;
                }
            }
            //go to next row
            else {
                sum = 150;
                x = 0;
                y++;
                //if there should be corridor jump over corridor size 
                //and draw sits down to up
                if(y == corridor - 1 && upToDown == true) {
                    upToDown = false;
                    y = 0;
                    yStart += (int) width - 50;
                }
                sitInRow = 0;
                i--;
            }
        }
    }
    
    //calculate where should be corridor
    private int getCorridor() {
        int corridor = sitPlaces / (int)(((length) - 150) / 70);
        
        if( corridor == 1)
            return 2;
        else
            return corridor;
    }
    
}//end Visualisation