import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class PetStoreTest {

    private enum Status{
        AVAILABLE,
        PENDING,
        SOLD
    }

    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    @Test
    public void getPetByIdTest(){
        int petId = 2;

        ValidatableResponse response = RestAssured.given()
//              .basePath()
                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatus() {
        int petId = 2;

        for (Status status : Status.values()) {
            ValidatableResponse response = RestAssured.given()
//                .basePath()
                    .param("status", status)
                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)
                    .then()
                    .log().all()
                    .statusCode(200);
        }
    }
}
