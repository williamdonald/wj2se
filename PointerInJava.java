/**
 * Java is strictly pass-by-value, exactly as in C.
 * <p>
 * When the method or constructor is invoked, the <b>values</b> of the
 * actual argument expression initialize newly created parameter variables, each of the declared Type.
 * <p>
 * From the Java Language Specification.
 * 
 * <li> pass-by-value</li>
 * <p>The actual parameter is fully evaluated and the resulting value is copied into a location being
 * used to hold the formal parameter's value during method execution.
 * <li> pass-by-reference </li>
 * <p> The formal parameter merely acts as an alias for the actual parameter.
 * Any time the method uses the formal parameter(for reading or writing), it is actually using the actual parameter.
 * 
 * <pre>
 * Foo f; //Java
 * Foo *f;//is exactly like C++'s
 * </pre>
 */
public class PointerInJava {

	static class Foo {
		Foo(String name){
			this._name = name;
		}
		String _name;

		public String getName() {
			return _name;
		}

		public void setName(String name) {
			this._name = name;
		}
		
	}
	
	public static void bla(Foo someFoo){
		someFoo.setName("YYY");//someFoo->setName("YYY");
		//a new Foo is created. let's say he's address 80 we assign the parameter someFoo to 80
		someFoo = new Foo("ZZZ");//if you can NOT allow this, please use "final" Foo someFoo.
		someFoo.setName("OOO");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//suppose the mf object resides at memory address 60,
		Foo mf = new Foo("XXX");
		//we pass 60 to the method, after fully evaluated since pass-by-value.
		bla(mf);
		System.out.println(mf.getName());//get the Foo's name from address 60.
	}

}
