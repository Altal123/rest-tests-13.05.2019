package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import static PetStore.endpoint.PetEndPoint.*;

@RunWith(SerenityRunner.class)
public class PetStoreTest {

    private int petId = 1448;
    private String name = "Макака";
    private String status = "available";

    @Steps
    private PetEndPoint petEndPoint;// = new PetEndPoint();

    @Test
    public void getPetByIdTest(){
        petEndPoint
                .getPetById(1448)
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {
        for (Status status : Status.values()) {
            petEndPoint
                    .getPetByStatus(status)
                    .statusCode(200);
        }
    }

    @Test
    public void createPetTest() {
        System.out.println("!!!!!!CREATE NEW PET!!!!!!");
        PetModel petModel = getPetModel();
        petEndPoint
                .createPet(petModel)
                .statusCode(200);
    }

    @Test
    public void updatePetTest(){
        System.out.println("!!!!!!UPDATE EXISTING PET!!!!!!");
        name = "Шимпанзе";
        status = "unavailable";
        PetModel petModel = getPetModel();

        petEndPoint
                .updatePet(petModel)
                .statusCode(200);

       /* ValidatableResponse response = RestAssured.given()
//                .basePath()
//                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
//                .contentType("application/json")
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
//                .log().all()
                .statusCode(200);*/

//        getPetByIdTest();
    }

    @Test
    public void deletePetByIdTest() {
        System.out.println("!!!!!!DELETE PET!!!!!!");
        petEndPoint
                .deletePetById(petId)
                .statusCode(200);
  /*      ValidatableResponse response = RestAssured.given()
//              .basePath()
//                .log().uri()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then()
//                .log().all()
                .statusCode(200);*/
    }

    private PetModel getPetModel() {
        return new PetModel(
                petId,
                new CategoryModel(petId, "млекопитающие"),
                name,
                new String[]{"string"},
                new TagModel[]{new TagModel()},
                status
        );
    }

}
