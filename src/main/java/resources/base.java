package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class base {

	static String environment = "FUTURE2";

	public static Properties prop = new Properties();
	static String projectPath = System.getProperty("user.dir");

	public static void getPropertyData() {

		prop = new Properties();
		FileInputStream fis = null;
		
		if (environment.equals("AWS-QA")) {
			try {
				fis = new FileInputStream(projectPath + "/src/main/java/resources/AWS-QA.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		if (environment.equals("AWS-QA-future")) {
			try {
				fis = new FileInputStream(projectPath + "/src/main/java/resources/AWS-QA-future.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		if (environment.equals("FUTURE")) {
			try {
				fis = new FileInputStream(projectPath + "/src/main/java/resources/future.properties");
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
				fis = new FileInputStream(projectPath + "/src/main/java/resources/future2.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (environment.equals("PRODCURRENT")) {
			try {
				fis = new FileInputStream(projectPath + "/src/main/java/resources/prodCurrent.properties");
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
