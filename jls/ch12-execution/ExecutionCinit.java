/**
 * 
 * <a
 * href="http://java.sun.com/docs/books/jls/third_edition/html/execution.html"
 * >JSL 12 Execution</a>
 */

public class ExecutionCinit {
	static int out(String s, int i) {
        System.out.println(s + "=" + i);
        return i;
}
	static class Super {
		static {
			System.out.print("Super ");
		}
	}
	
	static class One {
		static {
			System.out.print("One ");
		}
	}
	
	static class Two extends Super {
		static {
			System.out.print("Two ");
		}
	}

	//print Super Two false
	/**
	 * The class One is never initialized, 
	 * because it not used actively and therefore is never linked to. 
	 * The class Two is initialized 
	 * only after its superclass Super has been initialized.
	 * @param args
	 */
	public static void main(String[] args) {
		One o = null;
		Two t = new Two();
		System.out.println((Object) o == (Object) t);
		
		//prints only:1729
		//because the class Sub is never initialized; 
		//the reference to Sub.taxi is a reference to a field actually declared in class Super 
		//and does not trigger initialization of the class Sub.
		System.out.println(SSub.taxi);
		
		//produces the output:
		//	1
		//	j=3
		//	jj=4
		//	3
		//The reference to J.i is to a field that is a compile-time constant; 
		//therefore, it does not cause I to be initialized. 
		//The reference to K.j is a reference to a field actually declared in interface J that is not a compile-time constant; 
		//this causes initialization of the fields of interface J, 
		//but not those of its superinterface I, 
		//nor those of interface K. 
		//Despite the fact that the name K is used to refer to field j of interface J, 
		//interface K is not initialized.
		System.out.println(J.i);
        System.out.println(K.j);
	}
}

class SSuper { static int taxi = 1729; }
class SSub extends SSuper {
        static { System.out.print("Sub "); }
}

interface I {
    int i = 1, ii = ExecutionCinit.out("ii", 2);
}
interface J extends I {
    int j = ExecutionCinit.out("j", 3), jj = ExecutionCinit.out("jj", 4);
}
interface K extends J {
    int k = ExecutionCinit.out("k", 5);
}

