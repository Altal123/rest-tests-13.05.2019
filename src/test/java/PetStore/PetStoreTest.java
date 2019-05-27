package PetStore;

import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
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
        int petId = 1448;

        ValidatableResponse response = RestAssured.given()
//              .basePath()
                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {
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

    @Test
    public void deletePetByIdTest() {
        int petId = 1448;

        ValidatableResponse response = RestAssured.given()
//              .basePath()
                .log().uri()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void createPetTest() {

        PetModel petModel = new PetModel(
                1448,
                new CategoryModel(1448, "зверь"),
                "Обезьяна",
                new String[]{"string"},
                new TagModel[]{new TagModel()},
                "available"
        );

        ValidatableResponse response = RestAssured.given()
//                .basePath()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/xml")
//                .contentType("application/json")
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .log().all()
                .statusCode(200);
    }


}
