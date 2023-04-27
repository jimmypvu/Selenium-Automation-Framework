package jpvu.tests.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ListUsersTests {
    private static final String BASE_URL = "https://reqres.in/api";

    @Test(groups = {"api"})
    public void getTest01(){
        baseURI = BASE_URL;

        when()
                .get("/users?page=2")
        .then()
                .statusCode(200)
        .and()
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Rachel"))
                .body("data.last_name", hasItems("Funke", "Edwards"))
                .log().all();
    }

}
