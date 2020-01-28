package resources;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

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
		myWait();
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		myWait();
		return x;
	}
	
	public static void unenroll(String companyId, int invoiceId, int enrollmentId, int customerId)
	{
myWait();
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
				.then().assertThat()
//				.body("Message", equalTo("Enrollment deleted"))
				.statusCode(200);
//				.log().body()

myWait();
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
				.then().assertThat()
				.statusCode(200)
				.body("Message", equalTo("Invoice deleted"));
//				.log().body()
				
myWait();
		Response res =	given()
			.header("accept", prop.getProperty("accept"))
			.header("X-Api-Key", prop.getProperty("X-Api-Key"))
			.header("X-CompanyId", prop.getProperty("X-CompanyId"))
			.header("X-ClubId", prop.getProperty("X-Club1Id"))
				.when()
					.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/2019-01-01/2200-01-01")
					.then()
//					.log().body()
					.extract().response();

myWait();	
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

	public static void myWait()
		{
//		System.out.println("Entering myWait");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
	}
	
	public static void deleteEnrollment(String companyId, int enrollmentId)
	{
		System.out.println("Deleting Enrollment");
		
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
				.then().assertThat()
				.body("Message", equalTo("Enrollment deleted"))
//				.log().body()
				;
		myWait();
	}
	
	public static void deleteInvoice(String companyId, int invoiceId)
	{

		System.out.println("Deleting Invoice");
		
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
		
		given()
		.header("accept", prop.getProperty("accept"))
		.header("X-Api-Key", prop.getProperty("X-Api-Key"))
		.header("X-CompanyId", prop.getProperty("X-CompanyId"))
		.header("X-ClubId", prop.getProperty("X-Club1Id"))
			.when()
				.get("/api/v3/enrollmentcapability/deleteinvoice/"+companyId+"/"+invoiceId+"")
				.then().assertThat()
				.body("Message", equalTo("Invoice deleted"))
//				.log().body()
				;
		myWait();
	}
	
	public static void checkClassCourseEnrollment(int customerId) {
			
			base.getPropertyData();
			RestAssured.useRelaxedHTTPSValidation();
			RestAssured.baseURI = prop.getProperty("baseURI");
			
			System.out.println("Checking Enrollment");
			
			Response res =	given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/2019-01-01/2200-01-01")
						.then()
//						.log().body()
						.extract().response();
				JsonPath js = ReusableMethods.rawToJson(res);
			Assert.assertEquals(js.getString("Message"), "Nothing found");
			myWait();
//				return;
			
				if(!js.getString("Message").equals("Nothing found"))
				{
					checkClassCourseEnrollment(customerId);
				}
	}
	
	
	
}
	
	