/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

import java.io.Serializable;

/**
 *
 * @author adam
 */
public class ReadableIndividual implements Serializable {
	
	private static final long serialVersionUID = 992239615066582538L;
	private double length;
    private double width;
    private int sitPlaces;
    private int standPlaces;
    private double adaptationValue;
    
    public ReadableIndividual(double length, double width, int sitPlaces, int standPlaces, double adaptationValue){
        this.length = length;
        this.width = width;
        this.sitPlaces = sitPlaces;
        this.standPlaces = standPlaces;
        this.adaptationValue = adaptationValue;
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
    
    public double getAdaptationValue() {
		return adaptationValue;
	}
}
