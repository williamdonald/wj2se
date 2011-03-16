import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {
	private String m_defsrc;
	private String m_projectName;
	private String m_src;
	private String m_target;
	private String m_workingDir;
	
	public Launcher(String defsrc, String projectName, String src, String target, String workingDir) {
		this.m_defsrc = defsrc;
		this.m_projectName = projectName;
		this.m_src = src;
		this.m_target = target;
		this.m_workingDir = workingDir;
	}
	

	private static final String className = "com.xxx.codegen.build.MConfigBuilder";
	private String[] getOptions(){
		String[] options ={
				"-action",
				"java",
				"-defsrc",
				m_defsrc,
				"-project",
				m_projectName,
				"-src",
				m_src,
				"-target",
				m_target,
				"-releaseid",
				"e000",
				"-locale",
				"core",
				"-buildid",
				"-1",
				"-eclipse"};
		return options;
	}
	String[] classpaths = {
			  "C:\\work\\ws\\runtime-New_configuration\\BDefs\\bin",
			  "C:\\work\\ws\\runtime-New_configuration\\B\\bin",
			  "C:\\modules\\javassist-3.4.GA.jar",
	};
	
	public static String javaPath() {
		String javaHome = System.getProperty("java.home");
		String extension="";
		if(System.getProperty("os.name").indexOf("Windows")>-1){
			extension=".exe";
		}
		String winJava = javaHome + "/bin/java"+extension;
		if (new File(winJava).exists()) {
			return winJava;
		}
		return javaHome + "/bin/javaw"+extension;
	}
	
	private String[] contact(){
		ArrayList<String> cmd= new ArrayList<String>();
		cmd.add(javaPath());
//		String[] ss = getSecurityPolicy();
//		for (String security : ss) {
//			cmd.add(security);
//		}
		cmd.add("-classpath");
		StringBuilder sb = new StringBuilder();
		//win or unix??
		for (String path : classpaths) {
			sb.append(path+";");
		}
		cmd.add(sb.toString());
		cmd.add(className);
		String[] options = getOptions();
		for (String option : options) {
			cmd.add(option);
		}
		for (String string : cmd) {
			System.out.println(string);
		}
		return cmd.toArray(new String[cmd.size()]);
	}
	
	public void codegen(){
		ProcessBuilder  builder=new ProcessBuilder(contact());
		builder.directory(new File(m_workingDir));
		builder.environment().put("JAVA_HOME", System.getProperty("java.home"));
		try {
			Process javacodegen=builder.start();
			new Thread(new ThreadInputStreamHandler(javacodegen.getErrorStream())).start();
			new Thread(new ThreadInputStreamHandler(javacodegen.getInputStream())).start();
		} catch (IOException e) {   
			e.printStackTrace();
		}
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Launcher l = new Launcher(
				"C:\\work\\ws\\runtime-New_configuration\\Bxxx\\src",
				"B",
				"C:\\work\\ws\\runtime-New_configuration\\B\\src",
				"C:\\work\\ws\\runtime-New_configuration\\B\\gen-src",
				"C:\\work\\ws\\runtime-New_configuration\\B");
		
		l.codegen();
	}

}
