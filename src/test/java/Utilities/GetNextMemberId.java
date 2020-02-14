package Utilities;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import resources.base;

public class GetNextMemberId extends base {
	
	/*
	 * see CreateMember test for a scenario that confirms the Id is
	 * incremented after a new member is created
	 */
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Member Id Found",description="PBI:150230")
	public void MemberIdFound() { 
		
		int companyId = 101;
		int clubId = 1;

			given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", companyId)
				.header("X-ClubId", clubId)
					.when()
						.get("/api/v3/member/getnextmemberid")
						.then()
						.log().body();	
	}

}
