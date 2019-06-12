package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static PetStore.endpoint.PetEndPoint.*;

@RunWith(SerenityRunner.class)
public class PetStoreTest {

    private String petId = "1448";
    private String name = "Мавпенятко";

    @Steps
    private PetEndPoint petEndPoint;// = new PetEndPoint();

    @Test
    public void getPetByIdTest(){
        petEndPoint
                .getPetById("1448")
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
    }


    @Test
    public void updatePetTest(){
        name = "Шимпанзе";
        PetModel petModel = getPetModel();

        petEndPoint
                .updatePet(petModel)
                .statusCode(200);
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
                "available"
        );
    }
}
