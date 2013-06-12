/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir.klient;

/**
 *
 * @author adam
 */
public class ReadableIndividual {
    private int length;
    private int width;
    private int sitPlaces;
    private int standPlaces;
    
    public ReadableIndividual(int length, int width, int sitPlaces, int standPlaces){
        this.length = length;
        this.width = width;
        this.sitPlaces = sitPlaces;
        this.standPlaces = standPlaces;
    }
    
    public int getLength(){
        return this.length;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getSitPlacesNum(){
        return this.sitPlaces;
    }
    
    public int getStandPlacesNum(){
        return this.standPlaces;
    }
}
