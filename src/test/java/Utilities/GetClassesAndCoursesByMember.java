package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.base;

public class GetClassesAndCoursesByMember extends base {

	@BeforeClass
	public void getData(){
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
//	@Test (testName="ClassesCoursesFound",description="PBI:124953",dataProvider="getDataProvider")
//	public void ClassesCoursesFound(String customerId) {
	@Test (testName="ClassesCoursesFound",description="PBI:124953")
	public void ClassesCoursesFound() {
		
		
		int customerId = 227;
		String sDateTimeNoOffset = "2019-01-01";
		String eDateTimeNoOffset = "2200-01-01";
		
				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/classcourse/getclassesandcoursesbymember/"+customerId+"/"+sDateTimeNoOffset+"/"+eDateTimeNoOffset)
						.then()
						.log().body();
	}
	
	@DataProvider
	public Object[]getDataProvider(){
		Object[]data=new Object[1];
				data[0]=227;
				data[1]=248;
				data[2]=247;
//				data[0]=prop.getProperty("availableId");
//				data[1]=prop.getProperty("collectionsId");
//				data[2]=prop.getProperty("noFOPId");
				return data;
	}
}
