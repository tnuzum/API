package JonasFitness.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.base;
import resources.resources;

public class GetResponseCORE extends base {


    
	@BeforeTest
	public void getData() throws IOException {
		base.getPropertyData();
		
	}

	@Test
	public void Test1() {
		RestAssured.baseURI = "http://compete-ws.test-jfisoftware.net:4412/Info/CustomerInfo.svc";
		Response res = 
				
				given().
					param("barcodeId", "4890").

				when().get(resources.getnearbyDataXML()).
				then().

				extract().response();

		// ** Used to get entire response **
		String responseString = res.asString();
		System.out.println(responseString);

		// ** Used to get specific element from response **
//		JsonPath js = ReusableMethods.rawToJson(res);
//		String name = js.get("results[0].name");
//		System.out.println(name);
	}

}
