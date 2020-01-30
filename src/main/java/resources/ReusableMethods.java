package resources;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods extends base {

	public static XmlPath rawToXML(Response r) {
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	public static JsonPath rawToJson(Response r) {

		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
			myWait(1200);
		return x;
	}
	
	public static void unenroll(String companyId, int invoiceId, int enrollmentId, int customerId)
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
//				.then().log().body()
				;

			myWait(1000);
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
//				.then().log().body()
				;
		
			myWait(1500);
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
					System.out.println("-----------------");
					System.out.println("Retrying Unenroll");
					System.out.println("customerId: "+customerId);
					System.out.println("enrollmentId: "+enrollmentId);
					System.out.println("invoiceId: "+invoiceId);
					System.out.println("-----------------");
					ReusableMethods.unenroll(companyId, invoiceId, enrollmentId, customerId);
				}
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