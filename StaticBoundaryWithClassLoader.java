import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * What is static in java?
 * <p>
 * Do you really know singleton in java?
 * 
 * <p>
 * This sample is based on SUN JVM.
 */
public class StaticBoundaryWithClassLoader {
	private static int instanceCount = 0;

	public StaticBoundaryWithClassLoader() {
		System.out.println("This is instance #" + ++instanceCount);
	}


	/**
	 * 
	 * NOTE:<br> 
	 * it may NOT work in your IDE, if your IDE doesn't integrate "user.dir" into it.<br> 
	 * otherwise you can hardcode StaticBoundaryWithClassLoader class path to make it work.
<pre>
<code>
C:\Users\yuguo\Crater\PluginWs\src>java StaticBoundaryWithClassLoader
This is instance #1
This is instance #1
</code>
</pre>
	 */
	public static void main(String args[]) throws Exception {
		new StaticBoundaryWithClassLoader();
		// Create a URLClassLoader that uses as its parent
		// ClassLoader the bootstrap ClassLoader (indicated
		// by the "null" URLClassLoader constructor's
		// second argument value). This means that this
		// ClassLoader no longer uses the AppClassLoader and
		// ExtClassLoader as parent delegates, and should
		// not pick up code along the CLASSPATH (such as the
		// current directory).
		//
		URL[] urlArray = { new File(System.getProperty("user.dir")).toURI().toURL() };
		URLClassLoader cl = new URLClassLoader(urlArray, null);
		cl.loadClass("StaticBoundaryWithClassLoader").newInstance();
	}
	
	class Helper{
		/**
		 * helper to show SYSTEM Environment<br>
		 * two properties concern us here.<br>
		 * <li>
		 * user.dir: process current working directory
		 * <li>
		 * sun.boot.class.path: bootstrap path
		 */
		public void showEnvProperties(){
			System.getProperties().list(System.out);
		}
	}
}
