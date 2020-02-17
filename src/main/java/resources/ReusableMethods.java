package resources;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods extends base {
	
	public static int loopCount = 0;

	public static XmlPath rawToXML(Response r) {
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	public static JsonPath rawToJson(Response r) {

		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
			myWait(500);
		return x;
	}
	
	public static void unenroll(String companyId, int invoiceId, int enrollmentId, int customerId)
	{
		
		if(loopCount<5) // Counting loops so test will fail it unenroll fails 5 times
		{
		
			base.getPropertyData();
			RestAssured.useRelaxedHTTPSValidation();
			RestAssured.baseURI = prop.getProperty("baseURI");
			
			given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/enrollmentcapability/deleteenrollment/"+companyId+"/"+enrollmentId+"")
//					.then().log().body()
					;
	
				myWait(1000);
			given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
//					.then().log().body()
					;
			
				myWait(2500);
			Response res =	given()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/2019-01-01/2200-01-01")
						.then()
						.extract().response();
	
					if(res.statusCode() != 404)	// Message: "Nothing found"
					{
						loopCount++;
						System.out.println("-----------------");
						System.out.println("[INFO]: Retrying Unenroll");
						System.out.println("[INFO]: customerId: "+customerId);
						System.out.println("[INFO]: enrollmentId: "+enrollmentId);
						System.out.println("[INFO]: invoiceId: "+invoiceId);
						System.out.println("[INFO]: loopCount: "+loopCount);
						System.out.println("-----------------");
						ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
					}
			
			}
			else
	 		{
				Assert.assertTrue(false); //failing test because loopCount exceeded 5
			}
			loopCount = 0;
			return;
	}

	public static void myWait(int duration)
		{
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
}