package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class base {
	
	public static Properties prop = new Properties();
	static String projectPath = System.getenv("API_HOME");
	String userProfile = System.getenv("USERPROFILE");

	public static void getPropertyData() throws IOException {

		prop = new Properties();
		FileInputStream fis=new FileInputStream(projectPath + "\\src\\main\\java\\resources\\rest.properties");
		prop.load(fis);

	}

}


