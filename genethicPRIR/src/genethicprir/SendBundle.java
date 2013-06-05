/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

/**
 *
 * @author adrian
 */
public class SendBundle implements java.io.Serializable {
    private double adaptationValue;
    public SendBundle(double aV){
        this.adaptationValue = aV;
    }
    
    public double getAdaptationValue(){
        return this.adaptationValue;
    }
        
    public void setAdaptationValue(double aV){
        this.adaptationValue = aV;
    }
}
