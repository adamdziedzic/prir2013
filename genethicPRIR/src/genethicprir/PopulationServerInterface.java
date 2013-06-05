/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir;

import java.rmi.RemoteException;

/**
 *
 * @author adrian
 */
public interface PopulationServerInterface extends Remote{
   public SendBundle computeAdaptation(Individual ind) throws RemoteException;
	public Population getNextPopulation( Population oldPopulation ) throws RemoteException;
}
