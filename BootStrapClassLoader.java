/**
 * Runtime API
 * <p>
 * java -X 
 */
public class BootStrapClassLoader {
	static BootStrapClassLoader b = new BootStrapClassLoader();
	public static void main(String[] args) {
		b.showNullClassLoader();
	}
	
	  /** 
	  * <table>
	  *   <tbody>
	  *     <tr>
	  *       <th>id </th>
	  *       <th>path </th>
	  *       <th>name </th>
	  *     </tr>
	  *     <tr>
	  *       <td>bootstrap</td>
	  *       <td>JAVA_HOME/jre/lib</td>
	  *       <td><b>null</b></td>
	  *     </tr>
	  *     <tr>
	  *       <td>extension</td>
	  *       <td>JAVA_HOME/jre/ext/lib</td>
	  *       <td><b>sun.misc.Launcher$ExtClassLoader</b></td>
	  *     </tr>
	  *     <tr>
	  *       <td>application</td>
	  *       <td>CLASSPATH</td>
	  *       <td><b>sun.misc.Launcher$AppClassLoader</b></td>
	  *     </tr>     
	  *    </tbody>
	  *  </table>
	  */
	private void showNullClassLoader(){
		System.out.println(java.lang.Object.class.getClassLoader());		
	}
	
}
