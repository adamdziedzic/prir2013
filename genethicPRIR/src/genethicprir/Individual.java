/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

/**
 *
 * @author adrian
 */
public class Individual implements java.io.Serializable{
	private int[] genotype = new int [21];  //bin

	private int standPlacesNum;     //dec
	private double adaptationValue; //dec
        

    //get genotype in int[]
	public Individual(int [] genotype){
            this.genotype = genotype;            
	}

    //returns current adaptation value
	public double getAdaptationValue(){
        computeAdaptation();
        return adaptationValue;
	}

        //returns genotype
	public int[] getGenotype(){
		return genotype;
	}

	//gets genotype
	public void setGenotype(int[] genotype){
        this.genotype = genotype;
	}

        //return fragment of genotype (from genotype[start] to genotype[end])
	public int [] getGenotypeFragment(int start, int end){
        int[] tab = new int[end-start];
        for(int i=0;  i < end-start; i++)
            tab[i] = genotype[start+i];
        
        return tab;
	}
        
        //replaces genotype's fragment with value array
	public void changeGenotypeFragment(int start, int end, int[] value){
	    
	    if((end-start) != value.length)
	        throw new NullPointerException("changeGenotypeFragment(): incorrect parametrs");
	    
	    for(int i=0; i<value.length;i++)
	        genotype[i+start]=value[i];
	}

    //returns gen
    public int getGen(int number){
        return genotype[number];
	}

        //set gen
	public void setGen(int num, int value){
	    if (num<genotype.length)
	        throw new ArrayIndexOutOfBoundsException("setGen(): incorrect parametrs" );
	    genotype[num] = value;   
	}
        
    //returns gentorype length
    public int getGenotypeLength()
    {
        return genotype.length;
    }

        //returns width of bus
    public double getWidth(){
        double dec = 0;
        //change bin to dec
        for(int i=0; i<5; i++)
            dec = dec + genotype[i+8]*Math.pow(2, 1-i);
        
        return dec;
	}

	//returns length of bus
    public double getLength(){
        double dec = 0;
        
        for(int i=0; i<8; i++)
            dec = dec + genotype[i]*Math.pow(2,4-i);

        return dec;
	}

        //computes stand places (surface for one person it is 0.36 m2)
    public void computeStandPlacesNum(){
        double length = getLength();
        //(full surface of bus - surface of driver's cabin-surface of sit places)/surface of one stand place
        standPlacesNum =(int) ((getWidth()*(length-1.5) - getSitPlacesNum()*0.5*0.7)/0.36);
	}
        
        //returns number of stand places
	public int getStandPlacesNum(){
	    computeStandPlacesNum();
	    return standPlacesNum;
	}

        //returns number
	public int getSitPlacesNum(){
        int dec = 0;
        for(int i=0; i<8; i++){
            //change bin to dec system
            dec = dec + genotype[i+13]*(int)Math.pow(2, 7-i);
        }

        return dec;
	}

        //check if genotype is correct
	public boolean isCorrect(){
	    double y = getWidth();
	    double x = getLength();
	    int l = getSitPlacesNum();
	    double c = y * 1.4; //driver cabin
	    double d = 0.7; //dors
	    double corridor = 0.5*x;
	    
	    //width check
	    if ((y>4) || (y<2))
	        return false;
	    //length check
	    if ((x>19) || (x<7))
	        return false;
	    //check if number of sit places fits on bus surface
	    if ((l*0.7*0.5)>(x*y - c - 2*d - corridor) )
	        return false;
	    
	    //chceck how many sit places could be in one row
	    int sitOnWidth = (int) ((y-0.5) / 0.5);
	    //check how many sit will be in one coll
	    int sitInOneCol = l/sitOnWidth;
	    //check if col fit in bus length
	    if ((sitInOneCol)>(x-1.5/0.7) )
	        return false;
	    
	    return true;
	}

    //computes adatpation value
    private void computeAdaptation()
    {
        if (isCorrect() == true){
            double l = getSitPlacesNum();
            double h = getStandPlacesNum();
            double alpha = 0.35;
            double beta = 0.1;
            double x = this.getLength();
            double y = this.getWidth();
            adaptationValue = (l*100)/(l+h) - (alpha*l+beta*x*y);
            if (adaptationValue < 0)
                adaptationValue = 0;

        } else
            adaptationValue = 0;   
    }
}
