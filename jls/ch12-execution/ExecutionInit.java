
/**
 *
 * <a href="http://java.sun.com/docs/books/jls/third_edition/html/execution.html">JSL 12 Execution</a>
 */
public class ExecutionInit {

	/**
	 * <pre>
	 * Unlike C++, the Java programming language 
	 * does not specify altered rules for method dispatch during the creation of a new class instance. 
	 * If methods are invoked that are overridden in subclasses in the object being initialized, 
	 * <b>then these overriding methods are used, even before the new object is completely initialized.</b> 
	 * Thus, compiling and running the example:
	 * </pre>
	 */
	public static void main(String[] args) {
		Test t = new Test();
		t.printThree();

	}

}



class Super {
	Super() {
		printThree();
	}

	void printThree() {
		System.out.println("three");
	}
}

class Test extends Super {
	int three = (int) Math.PI; // That is, 3

	void printThree() {
		System.out.println(three);
	}
}

