package PetStore.endpoint;

import PetStore.models.PetModel;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import java.util.logging.Logger;

public class PetEndPoint {
//    Logger logger = Logger.getLogger(this.getClass().getName());      //java.util.logging.Logger;
    Logger logger = LoggerFactory.getLogger(this.getClass().getName()); //org.slf4j.Logger;

    public enum Status{
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Step
    public ValidatableResponse getPetById(String petId) {
        logger.info("!!!!!!GET PET BY ID!!!!!!");
        return given()
                .get(Config.GET_PET_BY_ID, petId)
                .then();
//                .log().all();
//                .statusCode(200);
//                .body("id", is (petId))
//                .body("name", is (name))
//                .body("status", is (status));
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .param("status", status)
//                .log().uri()
                .get(Config.GET_PET_BY_STATUS)
                .then();
//                .log().all();
    }

    @Step
    public ValidatableResponse createPet(PetModel petModel){
        logger.info("!!!!!!CREATE PET: " + petModel.getName() + "!!!!!!");
        return given()
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .log().all();
    }

    @Step
    public ValidatableResponse updatePet(PetModel petModel){
        logger.info("!!!!!!UPDATE PET !!!!!!");
        return given()
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .log().all();
    }

    @Step
    public ValidatableResponse deletePetById(String petId){
        logger.info("!!!!!!DELETE PET BY ID!!!!!!");
        return given()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then();
//                .log().all();
    }

    private RequestSpecification given(){
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json");
//                .log().uri();
    }
}
