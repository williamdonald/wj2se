import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 
 *<p>
 *Even though CutDelegationChainClassLoader, the class, was loaded by the VM and executed,
 *when we ask this new URLClassLoader to load the very same class, it fails. Because the
 *AppClassLoader (which loaded the CutDelegationChainClassLoader class the first time, since it was found on the
 *CLASSPATH) isn't part of ucl's ClassLoader delegation chain, and ucl itself can't find it, then ucl gives
 *up and throws a ClassNotFoundException, as it should.
 *</p>
 *
 */
public class CutDelegationChainClassLoader {

	/**
	 * 
	 * <pre>
	 * <code>
Exception in thread "main" <b>java.lang.ClassNotFoundException</b>: CutDelegationChainClassLoader
	at java.net.URLClassLoader$1.run(URLClassLoader.java:202)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:190)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:307)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:248)
	at CutDelegationChainClassLoader.main(CutDelegationChainClassLoader.java:37)
	</code>
	</pre>
	 */
	public static void main(String[] args) throws Exception {

		// Create a URLClassLoader that uses as its parent
		// ClassLoader the bootstrap ClassLoader (indicated
		// by the "null" URLClassLoader constructor's
		// second argument value). This means that this
		// ClassLoader no longer uses the AppClassLoader and
		// ExtClassLoader as parent delegates, and should
		// not pick up code along the CLASSPATH (such as the
		// current directory).
		//
		URL[] urlArray =
		{
		new File("./nonexistent_directory").toURI().toURL()
		};
		URLClassLoader cl = new URLClassLoader(urlArray, null);
		cl.loadClass(CutDelegationChainClassLoader.class.getName());
	}

}
