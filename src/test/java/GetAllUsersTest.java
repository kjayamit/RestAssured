import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetAllUsersTest {

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void getUsers(){
        given().
                when().
                get("api/users").
                then().log().all();
    }

    @Test
    public void validateStatusCode(){
        given().
                when().
                get("api/users").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validateUserId(){
        given().
                when().
                get("api/users").then().assertThat().body("data[0].id", equalTo(1));
    }


    @Test
    public void validateResponseTime(){
        // given().when().get("api/users/2").then().assertThat().statusCode(200);
        Response response = get("api/users");
        ValidatableResponse valRes =response.then();
        System.out.println(response.getTime());
        valRes.time(Matchers.lessThan(2000L));
    }

    @Test
    public void validateAuthorization(){
        given().auth().basic("krishna", "qa").when().get("api/users/2").then().statusCode(200);
    }
}
