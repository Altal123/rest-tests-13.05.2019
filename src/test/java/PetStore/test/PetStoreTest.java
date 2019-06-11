package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import net.thucydides.junit.runners.ThucydidesParameterizedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static PetStore.endpoint.PetEndPoint.*;

@RunWith(ThucydidesParameterizedRunner.class)
public class PetStoreTest {

    private int petId;
    private String name;
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
        PetModel petModel = getPetModel();
        petEndPoint
                .createPet(petModel);

        petEndPoint
                .getPetById(1448)
                .statusCode(200);
    }

    @Test
    public void updatePetTest(){
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

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {1448, "Макака"},
                {-1, "Terminator"},
                {???, "___"},
        });
    }

}
