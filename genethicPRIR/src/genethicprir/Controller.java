/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

import java.util.Random;

/**
 *
 * @author adrian
 */
public class Controller {
    
    
    /**
	 * randomly crosses over individuals
         * and return new population
	 */
	public Population crossingOver(Population population){
            int crossingPoint;
            int [] newGenotype;
            Individual individual1, individual2;
            Population newPopulation = new Population(0);
            
            Random r = new Random();
            
            while(population.getPopulationSize() != 0) {
                crossingPoint = r.nextInt(19) + 1;  //get random crossing point
                                                                
                individual1 = population.getRandomIndividual(); //get first individual
                individual2 = population.getRandomIndividual(); //get second individual
                
                //get new genotype as result of crossing two individual
                //and create new individual with this genotype
                newGenotype = crossIndividuals(individual1, individual2, crossingPoint);                               
                newPopulation.addIndividual(new Individual(newGenotype));
                
                //do the same as above but reoreder individuals
                newGenotype = crossIndividuals(individual2, individual1, crossingPoint);                
                newPopulation.addIndividual(new Individual(newGenotype));                
            }
            
            return newPopulation;
        }
        
                //this function crosses two individuals in the given point
        private int [] crossIndividuals(Individual s1, Individual s2, int point) {
            int [] d = new int[21];
            int [] genotype1 = s1.getGenotypeFragment(0, point);
            int [] genotype2 = s2.getGenotypeFragment(point, d.length);
            
            System.arraycopy(genotype1, 0, d, 0, point);
            System.arraycopy(genotype2, 0, d, point, d.length - point);
            
            return d;
        }

            
}
