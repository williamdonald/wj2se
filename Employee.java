
public class Employee {
	   private String name;
	   Employee (String name)
	   {
	      this.name = name;
	   }
	   public String toString ()
	   {
	      return name;
	   }
	   public void finalize () throws Throwable
	   {
		   System.out.println ("finalizing " + name);
		   super.finalize ();
	   }
}
