/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

import java.util.ArrayList;

/**
 *
 * @author adrian
 */
public class Population {
    	private ArrayList<Individual> individuals = new ArrayList<Individual>();

        //params: number of random generate individuals in population
	public Population(int populationSize){
            generatePopulation(populationSize);
	}

        //random population generate 
        private void generatePopulation(int n){
            for(int i = 0; i<n; i++)
                individuals.add(new Individual(generateGenotype()));
	}
        
        private int[] generateGenotype(){   
            //TODO
            return null;
        }
        
}
