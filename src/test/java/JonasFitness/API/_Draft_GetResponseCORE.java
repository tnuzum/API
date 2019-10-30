package JonasFitness.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resources.base;
import resources.resources;

public class _Draft_GetResponseCORE extends base {

	static String projectPath = System.getenv("ECLIPSE_HOME");
    
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		
	}
	@Test
	public void Test1() throws IOException {
		String postData = GenerateStringFromResource(projectPath + "\\API\\src\\main\\java\\resources\\getCustomerInfo.xml");
//		RestAssured.baseURI = "http://compete-ws.test-jfisoftware.net:4412/Info/CustomerInfo.svc";
		RestAssured.baseURI = "http://compete-ws.test-jfisoftware.net:4412/";
//		
		Response res =
	
				given().
					header("Content-Type","text/html; charset=utf-8").
					body(postData).

				when().get("/Info/CustomerInfo.svc?wsdl").
				then().assertThat().statusCode(200).
				//and().//validate response is successful
		       	//contentType(ContentType.XML).and().//validate content type

				extract().response();

		// ** Used to get entire response **
		String responseString = res.asString();
		System.out.println(responseString);
//		System.out.println(res);

		// ** Used to get specific element from response **
//		JsonPath js = ReusableMethods.rawToJson(res);
//		String name = js.get("results[0].name");
//		System.out.println(name);
	}
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
