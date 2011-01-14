/**
 * work with our customized  {@link Stack}
 * Two options to make it work.
 * <li>
 * put hacked Stack into your system evn directory: "C:\Users\yuguo\MyProgramFiles\JavaSun\jre\classes"
 * <li>
 * use -Xbootclasspath to designate your boot strap path<br>
 * something like: java -Xbootclasspath:blabla HackStackTest
 */

public class HackStackTest {


	/**
	<pre>
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\lib\resources.jar;
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\lib\rt.jar;
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\lib\sunrsasign.jar;
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\lib\jsse.jar;
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\lib\jce.jar;
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\lib\charsets.jar;
C:\Users\yuguo\MyProgramFiles\JavaSun\jre\classes
<br>
<b>Hacked Stack.</b>	
	</pre>
	 */
	public static void main(String[] args) {
		//put your hack code into 
		System.out.println(System.getProperty("sun.boot.class.path"));
		new Stack<String>();
	}

}
