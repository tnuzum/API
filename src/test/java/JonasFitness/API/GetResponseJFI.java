package JonasFitness.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.base;
import resources.resources;

public class GetResponseJFI extends base {


    
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
//		Properties systemProps = System.getProperties();
//	    systemProps.put("javax.net.ssl.keyStorePassword","changeit");
//	    systemProps.put("javax.net.ssl.keyStore","C:\\Users\\tnuzum\\devtest-JFI.jks");
//	    systemProps.put("javax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk-11.0.3\\lib\\security\\cacerts");
//	    systemProps.put("javax.net.ssl.trustStorePassword","changeit");
//	    System.setProperties(systemProps);
		
	}

	@Test
	public void Test1() {
		RestAssured.baseURI = ("https://compete-api-future2.test-jfisoftware.com:8252/api/v3/member/getmember/1/%25/All");
given().
//				trustStore("C:\\Program Files\\Java\\jdk-11.0.3\\lib\\security\\cacerts","changeit").
//				keyStore("C:\\Users\\tnuzum\\devtest-JFI.jks","changeit").
//					param("companyid", "101").
//					param("clubid", "1").
//					param("barcodeid", "TEST").
					when().get().
//				when().post("https://compete-api-future2.test-jfisoftware.com:8252/api/v3/member/getmember/1/%25/All").
				then();
	}

}
