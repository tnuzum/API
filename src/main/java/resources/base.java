package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class base {

	static String environment = "FUTURE2";

	public static Properties prop = new Properties();
	static String projectPath = System.getenv("API_HOME");

	public static void getPropertyData() {

		prop = new Properties();
		FileInputStream fis = null;

		if (environment.equals("FUTURE")) {
			try {
				fis = new FileInputStream(projectPath + "\\src\\main\\java\\resources\\future.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (environment.equals("FUTURE2")) {
			try {
				fis = new FileInputStream(projectPath + "\\src\\main\\java\\resources\\future2.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
