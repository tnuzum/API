package resources;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods extends base {
	
	static String projectPath = System.getenv("ECLIPSE_HOME");
	String userProfile = System.getenv("USERPROFILE");

	public static XmlPath rawToXML(Response r) {
		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}

	public static JsonPath rawToJson(Response r) {
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}
	
	public static void delEnrollment(String companyId, int enrollmentId)
	{

		try {
			base.getPropertyData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/deleteenrollment/"+companyId+"/"+enrollmentId+"")
				.then()
//				.log().body()
				;
		
		return;
	}
	
	public static void delInvoice(String companyId, int invoiceId)
	{
		try {
			base.getPropertyData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
				.then()
//				.log().body()
				;
		
		return;
	}
	
}
	
	