/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir.klient;

import javax.swing.JFrame;

/**
 *
 * @author adrian
 */
public class GenethicPRIR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
	//make gui        
    JFrame frame = new JFrame("Optymalizacja 1.0");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.add( new MainPanel() );
		
	frame.pack();
	frame.setVisible( true );
    }
}
