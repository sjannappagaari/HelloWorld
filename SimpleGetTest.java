package restAssuredLearning;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

/*	@Test
	public void GetWeatherDetails() {

		// Specify the base URL to the RESTful web service //
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		RestAssured.baseURI = "https://reqres.in/api/";

		// Get the RequestSpecification of the request that you want to sent to the
		// server.
		// The server is specified by the BaseURI that we have specified in the above
		// step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method
		// URL. // This will return the Response from the server. Store the response in
		// a variable.
		Response response = httpRequest.request(Method.GET, "users?page=2");

		// Now let us print the body of the message to see what response
		// we have received from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

	}

	@Test
	public void GetWeatherDetailsCondensed() {

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://reqres.in/api/";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("users?page=2");

		// Response.asString method will directly return the content of the body as
		// String.
		System.out.println("Response Body is =>  " + response.asString());
	}

	@Test
	public void GetStatusCode() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("users?page=200");

		int statusCode = response.getStatusCode();
		System.out.println("Status Code returned is " + statusCode);
		Assert.assertEquals(statusCode, 200, "Correct status code returned");

		String statusLine = response.getStatusLine();
		System.out.println("Status Line returned is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void GetHeaderInfo() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("users?page=200");

		String contentType = response.getHeader("Content-Type");
		System.out.println("Content type value is " + contentType);

		String serverName = response.header("Server");
		System.out.println("Server name is " + serverName);

		String encoding = response.header("Content-Encoding");
		System.out.println("Content Encoding is " + encoding);
		Assert.assertEquals(encoding, "gzip", "Encoding is matching");

		Headers allHeaders = response.headers();

		// Iterate over all the Headers for (Header header : allHeaders) {
		System.out.println("Key: " + header.getName() + " || Value: " + header.getValue());
	}
*/
	@Test
	public void GetResponseBodyInfo() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("users?page=200");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the body
		// into the string representation.
		System.out.println("Response Body is: " + body.asString());
		Assert.assertEquals(body.asString().toLowerCase().contains("total"), true);
	}

	@Test
	public void GetJsonPath() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("users?page=2");

		JsonPath jsonPathEvaluator = response.jsonPath();

		String total = jsonPathEvaluator.getString("total");
		System.out.println("The value of Total is: " + total);
		Assert.assertEquals(total, "12");

		String email = jsonPathEvaluator.getString("data[0].email");
		System.out.println("The value of first email is: " + email);
		Assert.assertEquals(email, "michael.lawson@reqres.in");
	}

	@Test
	public void TestQueryParameters() {
		RestAssured.baseURI = "https://samples.openweathermap.org/data/2.5/";
		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.queryParam("q", "London,uk")
				.queryParam("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8").get("weather");

		//String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		System.out.println(response.jsonPath().getString("name"));
		Assert.assertEquals(response.jsonPath().getString("name"), "London");
	}
}
