/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
 
public class HelloServer extends java.rmi.server.UnicastRemoteObject
                         implements HelloInterface 
{
  Registry reg;  // rejestr nazw obiektów 

  /**
   * Metoda, implementująca funkcję Hello() interfejsu HelloInterface, którą
   * zdalnie wywołuje klient
   */
  public void Hello(String message) throws RemoteException
  {
    System.out.println(message);
  }

  public MojTyp count(int m) throws RemoteException
  {
	MojTyp x = new MojTyp();
	x.x = m;
	return x;
  }

  public HelloServer() throws RemoteException
  {
    try
    {
      // Utworzenie rejestru nazw
      reg = LocateRegistry.createRegistry(1099);
      // związanie z obiektem nazwy
      reg.rebind("HelloServer", this); 
    }
    catch(RemoteException e)
    {
      e.printStackTrace();
      throw e;
    }
  }
  
  public static void main(String args[])
  {
    // utworzenie obiektu dostępnego zdalnie
    try
    {
      HelloServer s = new HelloServer();
    } 
    catch (Exception e)
    {
       e.printStackTrace();
       System.exit(1);
    }
  }
}
