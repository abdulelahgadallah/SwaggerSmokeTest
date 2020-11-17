package TestModels;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Utils.AbstractClass;

public class TestPet extends AbstractClass{
	int petId = 2;

	@Test
	public void findPet() {
		test = extentReports.startTest("Test Find Pet");
		Response respone = get("https://petstore.swagger.io/v2/pet/" + petId);
		int code = respone.getStatusCode();
		Assert.assertEquals(code, 200);
		test.log(LogStatus.INFO, "Response : " + respone.asString());
		extentReports.endTest(test);
	}

	@Test
	public void deletePet() {
		test = extentReports.startTest("Test Delete Pet");
		Response respone = delete("https://petstore.swagger.io/v2/pet/" + 0);
		int code = respone.getStatusCode();
		Assert.assertEquals(code, 200);
		test.log(LogStatus.INFO, "Response : " + respone.asString());
		extentReports.endTest(test);
	}

	@Test
	public void addPet() {
		
		test = extentReports.startTest("Test Add Pet");
		// prepare request
		RequestSpecification request = given();
		request.header("Content-Type", "application/json");
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", "2");
		// category
		JSONObject categoryObj = new JSONObject();
		categoryObj.put("id", "00");
		categoryObj.put("name", "test");
		requestObj.put("category", categoryObj);
		requestObj.put("name", "test_name");
		// photo urls array
		JSONArray photoUrls = new JSONArray();
		photoUrls.put("string");
		requestObj.put("photoUrls", photoUrls);
		// tags array
		JSONArray tags = new JSONArray();
		JSONObject tagObj = new JSONObject();
		tagObj.put("id", "0");
		tagObj.put("name", "test");
		tags.put(tagObj);
		requestObj.put("tags", tags);
		requestObj.put("status", "available");
		request.body(requestObj.toString());
		Response response= request.post("https://petstore.swagger.io/v2/pet");
		int code=response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(code, 200);
		test.log(LogStatus.INFO, "Response : " + response.asString());
		extentReports.endTest(test);

	}
	
	@Test
	public void updatePet() {
		
		test = extentReports.startTest("Test Update Pet");
		// prepare request
		RequestSpecification request = given();
		request.header("Content-Type", "application/json");
		JSONObject requestObj = new JSONObject();
		requestObj.put("id", "2");
		// category
		JSONObject categoryObj = new JSONObject();
		categoryObj.put("id", "00");
		// new name to be updated 
		categoryObj.put("name", "new test name");
		requestObj.put("category", categoryObj);
		requestObj.put("name", "test_name");
		// photo urls array
		JSONArray photoUrls = new JSONArray();
		photoUrls.put("string");
		requestObj.put("photoUrls", photoUrls);
		// tags array
		JSONArray tags = new JSONArray();
		JSONObject tagObj = new JSONObject();
		tagObj.put("id", "0");
		tagObj.put("name", "test");
		tags.put(tagObj);
		requestObj.put("tags", tags);
		requestObj.put("status", "available");
		request.body(requestObj.toString());
		Response response= request.put("https://petstore.swagger.io/v2/pet/2");
		int code=response.getStatusCode();
		Assert.assertEquals(code, 200);
		test.log(LogStatus.INFO, "Response : " + response.asString());
		extentReports.endTest(test);

	}

}
