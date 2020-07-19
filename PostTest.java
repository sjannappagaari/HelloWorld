package restAssuredLearning;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostTest {

	@Test
	public void PostMethodQuery() {
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification request = RestAssured.given();

		// JSONObject is a class that represents a Simple JSON.
		// We can add Key - Value pairs using the put method

		JSONObject reqParams = new JSONObject();

		reqParams.put("name", "John");
		reqParams.put("job", "Manager");

		request.header("Content-Type", "application/json");
		request.body(reqParams.toJSONString());
		Response response = request.post("/api/users");
		request.body(reqParams);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "201");
		//String successCode = response.jsonPath().get("SuccessCode");
		//Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");

	}
}
