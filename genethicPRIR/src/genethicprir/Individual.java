/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

/**
 *
 * @author adrian
 */
public class Individual {
    private int[] genotype = new int [21]; //bin
    private double adaptationValue; //dec
        
    //get genotype in int[]
    public Individual(int [] genotype){
        this.genotype = genotype;            
    }
    
    
    //returns genotype
    public int[] getGenotype(){
	return genotype;
    }

    //gets genotype
    public void setGenotype(int[] genotype){
        this.genotype = genotype;
    }
    
    //returns gen
    public int getGen(int number){
        return genotype[number];
    }
}
