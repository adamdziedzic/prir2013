/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;


import java.util.Random;

import javax.swing.JFrame;

/**
 *
 * @author adrian
 */
public class Controller {
    
    
        //this function mutate individuals
	public void mutation(Population population){
            int size = population.getPopulationSize();
            Individual individual;
            int [] genotype;
            
            Random r = new Random();
            
            for(int i = 0; i < size; i++) {
                individual = population.getIndividual(i);
                genotype = individual.getGenotype();    //get genotype
                
                //go over all gens in genotype and change value on opposite
                for(int j = 0; j < genotype.length; j++) {
                    if(r.nextDouble() < 0.1) {        //probability of mutation
                        if(genotype[j] == 0)            //is very low
                            genotype[j] = 1;
                        else
                            genotype[j] = 0;
                        //System.out.println("Zaszla mutacja");
                    }
                }
                
                individual.setGenotype(genotype);
                
            }
            //return population;
	}

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
				
		/**
	 * this function selects bests individuals
	 */
	public Population selection(Population population){
            int size = population.getPopulationSize();
            double sum = 0.0;
            double probability;
            double fitness;
            Population newPopulation = new Population(0);
            
            Random r = new Random();
            
            double [] probabilities = new double[size+1];   //array of sum of probabilities
            
            //calculate sum of adaptation values
            for(int i = 0; i < population.getPopulationSize(); i++) {
                sum += population.getIndividual(i).getAdaptationValue();
            }
            
            //get probability of individual and insert to array
            for(int i = 0; i < size; i++) {
                fitness = population.getIndividual(i).getAdaptationValue();
                
                if(i == 0) {
                    probabilities[i+1] = (fitness/sum);     //first individual
                } else {                                    //others individuals
                    probability = probabilities[i] + (fitness/sum);
                    probabilities[i+1] = probability;
                }
            }
            //first value in array is 0, last is 1
            probabilities[0] = 0;
            
            double random;
            
            //roulette wheel method
            for(int i = 0; i < size; i++) {
                random = r.nextDouble();        //get random value form 0.0 - 1.0
                    
                for(int j = 1; j <= size; j++) {
                    //check for which individual the result is correct
                    //and insert this individual into a new population
                    if((random > probabilities[j-1]) && (random < probabilities[j])) {
                        newPopulation.addIndividual(population.getIndividual(j-1));
                        break;
                    }
                }
            }
        
            return newPopulation;
	}

        //TODO move visualisate to MainPanel
        
		   //this function draws visualisation of the bus
		public void visualizate(Individual best, Visualisation visualisation){
            
            //get size and numbers of sit and astand places
            double length = best.getLength();
            double width = best.getWidth();
            int sitPlaces = best.getSitPlacesNum();
            int standPlaces = best.getStandPlacesNum();
                        
            visualisation.repaint(length, width, sitPlaces, standPlaces);
            visualisation.setPreferredSize();
		}
                
                //this function draws visualisation of the bus
		public void visualizate(ReadableIndividual best, Visualisation visualisation){
            
            //get size and numbers of sit and astand places
            double length = best.getLength();
            double width = best.getWidth();
            int sitPlaces = best.getSitPlacesNum();
            int standPlaces = best.getStandPlacesNum();
                        
            visualisation.repaint(length, width, sitPlaces, standPlaces);
            visualisation.setPreferredSize();
		}

            
}
