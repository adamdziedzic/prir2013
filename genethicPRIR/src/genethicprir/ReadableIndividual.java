/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

/**
 *
 * @author adam
 */
public class ReadableIndividual {
    private double length;
    private double width;
    private int sitPlaces;
    private int standPlaces;
    
    public ReadableIndividual(double length, double width, int sitPlaces, int standPlaces){
        this.length = length;
        this.width = width;
        this.sitPlaces = sitPlaces;
        this.standPlaces = standPlaces;
    }
    
    public double getLength(){
        return this.length;
    }
    
    public double getWidth(){
        return this.width;
    }
    
    public int getSitPlacesNum(){
        return this.sitPlaces;
    }
    
    public int getStandPlacesNum(){
        return this.standPlaces;
    }
}
