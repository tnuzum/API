package unitTests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;


import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import resources.base;

public class GetEmployees extends base {
	
	@BeforeClass
	public void getData() {
		base.getPropertyData();
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = prop.getProperty("baseURI");
	}
	
	@Test (testName="Employees Found - Active Only - No Optional Parameters",description="PBI:150855")
	public void employeesFoundActiveOnlyNoOptionalParameters() { 

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].Id", equalTo(0))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[1].Id", equalTo(1))
						.body("Result[1].IsActive", equalTo(true))
						.body("Result[1].Address1", not(nullValue()))
						.body("Result[1]", hasKey("Address2"))
						.body("Result[1].City", not(nullValue()))
						.body("Result[1].State", not(nullValue()))
						.body("Result[1].PostalCode", not(nullValue()))
						.body("Result[1]", hasKey("Username"))
						.body("Result[1]", hasKey("Email"))
						.body("Result[1].FirstName", not(nullValue()))
						.body("Result[1].LastName", not(nullValue()))
						.body("Result[1]", hasKey("MiddleInitial"))
						.body("Result[1]", hasKey("PreferredName"))
						.body("Result[1]", hasKey("HomePhone"))
						.body("Result[1]", hasKey("MobilePhone"));
	}
	
	@Test (testName="Employees Found - Active Only - Employee Club Id",description="PBI:150855")
	public void employeesFound_ActiveOnly_EmployeeClubId() { 
		
				String clubId = prop.getProperty("club1Id");

				given()
//						.log().all()
				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&EmployeeClubId="+clubId+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()))
						.body("Result[1].IsActive", equalTo(true))
						.body("Result[1].Address1", not(nullValue()))
						.body("Result[1]", hasKey("Address2"))
						.body("Result[1].City", not(nullValue()))
						.body("Result[1].State", not(nullValue()))
						.body("Result[1].PostalCode", not(nullValue()))
						.body("Result[1]", hasKey("Username"))
						.body("Result[1]", hasKey("Email"))
						.body("Result[1].FirstName", not(nullValue()))
						.body("Result[1].LastName", not(nullValue()))
						.body("Result[1]", hasKey("MiddleInitial"))
						.body("Result[1]", hasKey("PreferredName"))
						.body("Result[1]", hasKey("HomePhone"))
						.body("Result[1]", hasKey("MobilePhone"));
	}
	
	@Test (testName="Employees Found - Active Only - Last Name",description="PBI:150855")
	public void employeesFound_ActiveOnly_LastName() { 
		
			String lName = "Aponte";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&LastName="+lName+"")
						.then()
	//					.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", equalTo(lName))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Partial Last Name",description="PBI:150855")
	public void employeesFound_ActiveOnly_PartialLastName() { 
		
			String lName = "Apon";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&LastName="+lName+"")
						.then()
	//					.log().body()
						.assertThat().statusCode(200)
						.time(lessThan(60L),TimeUnit.SECONDS)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - First Name",description="PBI:150855")
	public void employeesFound_ActiveOnly_FirstName() { 
		
			String fName = "Marcelo";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&FirstName="+fName+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", equalTo(fName))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Partial First Name",description="PBI:150855")
	public void employeesFound_ActiveOnly_PartialFirstName() { 
		
			String fName = "Marce";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&FirstName="+fName+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Address1",description="PBI:150855")
	public void employeesFound_ActiveOnly_Address1() { 
		
			String address1 = "64 Youth Street";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Address="+address1+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", equalTo(address1))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Partial Address1",description="PBI:150855")
	public void employeesFound_ActiveOnly_PartialAddress1() { 
		
			String address1 = "64 Youth St";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Address="+address1+"")
						.then()
	//					.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0]", hasKey("Username"))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Address2",description="PBI:150855")
	public void employeesFound_ActiveOnly_Address2() { 
		
			String address1 = "9 Canterwood Close";
			// sending employee's address line 2 to confirm employee not found by address line 2
				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Address="+address1+"")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("No employee record(s) found"));
	}
	
	@Test (testName="Employees Found - Active Only - Username",description="PBI:150855")
	public void employeesFound_ActiveOnly_Username() { 
		
			String username = "JonasSupport";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Username="+username+"")
						.then()
		//				.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0].Username", equalTo(username))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Partial Username",description="PBI:150855")
	public void employeesFound_ActiveOnly_PartialUsername() { 
		
			String username = "JonasSupp";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Username="+username+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0].Username", not(nullValue()))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Department",description="PBI:150855")
	public void employeesFound_ActiveOnly_Department() { 
		
			String department = "Personal Trainer";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Department="+department+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0].Username", not(nullValue()))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employees Found - Active Only - Home Phone",description="PBI:150855")
	public void employeesFound_ActiveOnly_HomePhone() { 
		
			String hPhone = "6149001000";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&HomePhone="+hPhone+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(true))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0].PostalCode", not(nullValue()))
						.body("Result[0].Username", not(nullValue()))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0].HomePhone", equalTo(hPhone))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()))
						;
	}
	
	@Test (testName="Employees Found - Active Only - Partial Home Phone",description="PBI:150855")
	public void employeesFound_ActiveOnly_PartialHomePhone() { 
		
			String hPhone = "9001000";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&HomePhone="+hPhone+"")
						.then()
	//					.log().body()
						.assertThat().statusCode(400)
						.body("Message", equalTo("Parsing HomePhone failed, please make sure you submit 10 digits"))
						;
	}
	
	@Test (testName="Employee Not Found",description="PBI:150855")
	public void employeeNotFound() { 
		
			String username = "NOTONFILE";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Username="+username+"")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("No employee record(s) found"));
	}
	
	@Test (testName="Employee Inactive - ActiveTrue",description="PBI:150855")
	public void employeeInactiveActiveTrue() { 
		
				String username = prop.getProperty("inactiveEmployeeName");

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=true&Username="+username+"")
						.then()
//						.log().body()
						.assertThat().statusCode(404)
						.body("Message", equalTo("No employee record(s) found"));
	}
	
	@Test (testName="Employee Inactive - ActiveFalse",description="PBI:150855")
	public void employeeInactiveActiveFalse() { 
		
				String username = prop.getProperty("inactiveEmployeeName");

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", prop.getProperty("X-CompanyId"))
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=false&Username="+username+"")
						.then()
//						.log().body()
						.assertThat().statusCode(200)
						.body("Result[0].Id", not(nullValue()))
						.body("Result[0].IsActive", equalTo(false))
						.body("Result[0].Address1", not(nullValue()))
						.body("Result[0]", hasKey("Address2"))
						.body("Result[0].City", not(nullValue()))
						.body("Result[0].State", not(nullValue()))
						.body("Result[0]", hasKey("PostalCode"))
						.body("Result[0].Username", equalTo(username))
						.body("Result[0]", hasKey("Email"))
						.body("Result[0].FirstName", not(nullValue()))
						.body("Result[0].LastName", not(nullValue()))
						.body("Result[0]", hasKey("MiddleInitial"))
						.body("Result[0]", hasKey("PreferredName"))
						.body("Result[0]", hasKey("HomePhone"))
						.body("Result[0]", hasKey("MobilePhone"))
						.body("Result[0].Id", not(nullValue()));
	}
	
	@Test (testName="Employee Not Found",description="PBI:150855", enabled = false)
	public void employee90daysInactive() { 
			// this employee can't login to BO due to 90 days inactivity, but in this call they are still shown as IsActive=True - Researching correct behavior
		
			String username = "scampbell";

				given()

				.header("accept", prop.getProperty("accept"))
				.header("X-Api-Key", prop.getProperty("X-Api-Key"))
				.header("X-CompanyId", 101)
				.header("X-ClubId", prop.getProperty("X-Club1Id"))
					.when()
						.get("/api/v3/employee/getemployees?ActiveOnly=false&Username="+username+"")
						.then()
						.log().body();
	}
}
