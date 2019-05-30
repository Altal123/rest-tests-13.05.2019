package PetStore.endpoint;

import PetStore.models.PetModel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.is;

public class PetEndPoint {

    public enum Status{
        AVAILABLE,
        PENDING,
        SOLD
    }

    public ValidatableResponse getPetById(int petId) {
        return given()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all();
//                .statusCode(200);
//                .body("id", is (petId))
//                .body("name", is (name))
//                .body("status", is (status));
    }

    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .param("status", status)
                .log().uri()
                .get(Config.GET_PET_BY_STATUS)
                .then()
                .log().all();
    }

    public ValidatableResponse createPet(PetModel petModel){
        return given()
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .log().all();
    }

    public ValidatableResponse updatePet(PetModel petModel){
        return given()
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .log().all();
    }

    public ValidatableResponse deletePetById(int petId){
        return given()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then()
                .log().all();
    }

    private RequestSpecification given(){
        return RestAssured.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json")
                .log().uri();
    }
}
