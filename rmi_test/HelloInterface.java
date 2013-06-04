import java.rmi.*;


public interface HelloInterface extends Remote
{
   void Hello(String message) throws RemoteException;
   MojTyp count (int m) throws RemoteException;
}
