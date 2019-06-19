package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent
@RunWith(SerenityRunner.class)
public class PetUpdateTest {

    private PetModel petModel;
    private String petId = "1448";

    @Steps
    private PetEndPoint petEndPoint;// = new PetEndPoint(); можно закомментить т.к. @Steps уже создает єтот обьект

    @Before
    public void precondition(){
        petModel = new PetModel(
                petId,
                new CategoryModel(petId, "млекопитающие"),
                "Макака",
                new String[]{"string"},
                new TagModel[]{new TagModel()},
                "available"
        );
        petEndPoint
                .createPet(petModel)
                .statusCode(200);
    }

    @Test
    public void uploadImageOfPet() {
        petEndPoint
                .uploadImageOfPet(petId, "Butterly.JPG")
                .statusCode(200);
    }

    @Test
    public void updatePetTest(){
        petModel.setName("Шимпанзе");
        petModel.setStatus("unavailable");

        petEndPoint
                .updatePet(petModel)
                .statusCode(200);
    }

    @After
    public void postcondition(){
        petEndPoint
                .getPetById(petModel.getId())
                .statusCode(200);
        petEndPoint
                .deletePetById(petModel.getId())
                .statusCode(200);

    }
}
