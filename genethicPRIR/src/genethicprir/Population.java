/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author adrian
 */
public class Population implements java.io.Serializable{
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
            int [] vec = new int[21];
            Random rand = new Random();
            
            //range 8-18
            int lengthDec = 7 + rand.nextInt(12);
            String lengthBin = Integer.toBinaryString(lengthDec);
            
            if(lengthBin.length()<5)
                vec[0] = 0;
            
            //save to 
            for(int i = 0; i<lengthBin.length(); i++)     
                vec[i+5-lengthBin.length()] = Integer.parseInt(Character.toString(lengthBin.charAt(i)));
            
            //rand fraction (length) 
            for(int i = 0; i<3; i++)
                vec[i+5] = rand.nextInt(2);
        
            //rand width (Int part): 2-3
            int widthDec = 2 + rand.nextInt(2);
            String widthBin = Integer.toBinaryString(widthDec);
            
            //save length to array (bin)        
            for(int i = 0; i<widthBin.length(); i++) 
                vec[i+8] = Integer.parseInt(Character.toString(widthBin.charAt(i)));
             //rand fraction part od length 
            for(int i = 0; i<3; i++)
                vec[i+10] = rand.nextInt(2);
            
            
            //rand sitplaces
            int sitMax = (int) (lengthDec*widthDec/(0.7*0.5)-1.5*widthDec-lengthDec*0.5-3*0.24);
            int sitDec = rand.nextInt(sitMax);
            String sitBin = Integer.toBinaryString(sitDec);
            
            //if num of sit places < 8, fill with 0
            for(int i = 0; i<(8-sitBin.length()); i++)
                vec[13+i] = 0;
                    
            for(int i = 0; i<sitBin.length(); i++)
                vec[i+21-sitBin.length()] = Integer.parseInt(Character.toString(sitBin.charAt(i)));
            
            
            return vec;
        }
        
        //returns individual form population
	public Individual getIndividual(int i){
            return individuals.get(i);
	}

        //add individual to population
	public void addIndividual(Individual o){
            individuals.add(o);
	}

        //returns the best individual (with the highest value of adaptation function)
	public Individual getBestIndividual(){
            Individual max = null;
            double maxVal = 0;
            for(int i=0; i<individuals.size(); i++){
                double tmp = individuals.get(i).getAdaptationValue(); 
                if(tmp > maxVal){    
                    maxVal = tmp;
                    max = individuals.get(i);
                }
            }
            return max;
	}

        //returns population size
	public int getPopulationSize(){
            return individuals.size();
	}
        
        //prints all individuals' genotypes 
        public void printPopulation(){
            for(int i=0; i<individuals.size(); i++){
                int[] gen = individuals.get(i).getGenotype();
                for(int j = 0; j<21; j++)
                    System.out.print(gen[j]);
                System.out.println();
            }
        }
        
        //removes individual
        public void removeIndividual(int i) {
            individuals.remove(i);
        }
        
        //returns random individual
        public Individual getRandomIndividual() {
            Random r = new Random();
            int i;
            Individual randomIndividual;
            
            i = r.nextInt(individuals.size());
            randomIndividual = getIndividual(i);
            removeIndividual(i);
            
            return randomIndividual;
        }
        
}
