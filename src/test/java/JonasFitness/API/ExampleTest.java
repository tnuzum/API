package JonasFitness.API;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.ReusableMethods;
import resources.resources;

public class ExampleTest{
	String projectPath = System.getenv("ECLIPSE_HOME");
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				projectPath + "\\API\\src\\main\\java\\resources\\rest.properties");
		prop.load(fis);
	}

	@Test
	public void Test1() {
		RestAssured.baseURI = prop.getProperty("hostG");
		System.out.println(prop.getProperty("hostG"));
		Response res = given().param("location", "40.128354, -83.017916").param("radius", "500")
				.param("key", prop.getProperty("keyG")).param("type", "bar").

				when().get(resources.getnearbyDataJSON()).

				then().assertThat().statusCode(200).and().// validate response is successful
				contentType(ContentType.JSON).and().// validate content type
				header("Server", "scaffolding on HTTPServer2").// validate header objects
				extract().response();

		JsonPath js = ReusableMethods.rawToJson(res);
		String name = js.get("results[0].name");
		System.out.println(name);
		
		Assert.assertEquals(name, "The Wine Bistro");
	}

}
