
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
public class HelloClient
{
  public static void main(String args[])
  {
    HelloInterface remoteObject;   // referencja do zdalnego obiektu
    Registry reg;                  // rejestr nazw obiektów
    String serverAddr=args[0];
    try
    {
      // pobranie referencji do rejestru nazw obiektów
      reg = LocateRegistry.getRegistry(serverAddr);
      // odszukanie zdalnego obiektu po jego nazwie
      remoteObject = (HelloInterface) reg.lookup("HelloServer");
      // wywołanie metody zdalnego obiektu
      MojTyp d= remoteObject.count(2);
	System.out.println(d.x);
    }
    catch(RemoteException e)
    {
      e.printStackTrace();
    }
    catch(NotBoundException e)
    {
      e.printStackTrace();
    }
  }
}
