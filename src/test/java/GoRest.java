import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GoRest {
    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://gorest.co.in/";
    }

    @Test
    public void getUser(){
        given().
                when().
//                get("api/users/2").then().assertThat().body("data.id", equalTo(2));
                get("public-api/users/2")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data.name",equalTo("Patricia"));
    }

    @Test
    public void createUser(){
        JSONObject request = new JSONObject();
        File jsonDataInFile = new File("src/test/java/gorest.json");

        System.out.println(request.toString());
        given().headers(
                "Authorization",
                "Bearer " + "3481e3e75778186047e7987fad6b373cf37888a2f8f2c647db13055bbbaee561",
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when()
                .body(jsonDataInFile).when().post("public-api/users")
                .then()
//                .statusCode(201)
                .log().all()
                .assertThat().body("data.name", equalTo("Sidhant Rakesh"));


    }
}
