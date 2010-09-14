import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Hashtable;


public class WeakReferenceDemo {
	   public static void main (String [] args)
	   {
	      // Create a String object that is strongly reachable from key.
	      String key = new String ("key");
	      /*
	         Note: For this program, you cannot say String key = "key";. You
	               cannot do that because (by itself), "key" is strongly 
	               referenced from an internal constant pool data structure
	               (that I will discuss in a future article). There is no
	               way for the program to nullify that strong reference. As
	               a result, that object will never be garbage collected,
	               and the polling loop will be infinite.
	      */
	      // Create a ReferenceQueue object that is strongly reachable from q.
	      ReferenceQueue q = new ReferenceQueue ();
	      // Create a WeakReference object that is strongly reachable from wr.
	      // The WeakReference object encapsulates the String object that is
	      // referenced by key (so the String object is weakly-reachable from
	      // the WeakReference object), and associates the ReferenceQueue
	      // object, referenced by q, with the WeakReference object.
	      WeakReference wr = new WeakReference (key, q);
	      // Create an Object object that is strongly reachable from value.
	      Object value = new Object ();
	      // Create a Hashtable object that is strongly reachable from ht.
	      Hashtable ht = new Hashtable ();
	      // Place the WeakReference and Object objects in the hash table.
	      ht.put (wr, value);
	      // Remove the only strong reference to the String object.
	      key = null;
	      // Poll reference queue until WeakReference object arrives.
	      Reference r;
	      while ((r = q.poll ()) == null)
	      {
	         System.out.println ("Polling reference queue");
	         // Suggest that the garbage collector should run.
	         System.gc ();
	      }
	      // Using strong reference to the Reference object, remove the entry
	      // from the Hashtable where the WeakReference object serves as that
	      // entry's key.
	      value = ht.remove (r);
	      // Remove the strong reference to the Object object, so that object
	      // is eligible for garbage collection. Although not necessary in this
	      // program, because we are about to exit, imagine a continuously-
	      // running program and that this code is in some kind of long-lasting
	      // loop.
	      value = null;
	   }

}
