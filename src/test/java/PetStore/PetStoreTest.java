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

    private int petId = 1448;

    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    @Test
    public void getPetByIdTest(){
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
                petId,
                new CategoryModel(petId, "зверь"),
                "Обезьяна",
                new String[]{"string"},
                new TagModel[]{new TagModel()},
                "available"
        );

        ValidatableResponse response = RestAssured.given()
//                .basePath()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
//                .contentType("application/json")
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void updatePetTest(){

        createPetTest();

        PetModel petModel = new PetModel(
                petId,
                new CategoryModel(petId, "хищник"),
                "Горилла",
                new String[]{"path_to_photo"},
                new TagModel[]{new TagModel()},
                "unavailable"
        );

        ValidatableResponse response = RestAssured.given()
//                .basePath()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
//                .contentType("application/json")
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .log().all()
                .statusCode(200);

        getPetByIdTest();
        deletePetByIdTest();
    }


}
