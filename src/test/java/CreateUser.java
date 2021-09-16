import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateUser {

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void createSingleUser(){
        JSONObject request = new JSONObject();
        request.put("name", "kc");
        request.put("job", "QA");
        System.out.println(request.toString());
        given().header("Content-Type", "application/json").
                body(request.toJSONString()).when()
                .post("api/users").then().statusCode(201)
                .log().all().assertThat().body("name", equalTo("kc"));
    }

    @Test
    public void createSingleUserUsingFile(){
        JSONObject request = new JSONObject();
        File jsonDataInFile = new File("src/test/java/data.json");

        System.out.println(request.toString());
        given().header("Content-Type", "application/json").
                body(jsonDataInFile).when().post("api/users").then().statusCode(201).log().all().assertThat().body("name", equalTo("kc"));
    }

}
