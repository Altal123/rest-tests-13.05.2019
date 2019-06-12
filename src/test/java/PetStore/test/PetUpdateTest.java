package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PetUpdateTest {

    private PetModel petModel;

    @Steps
    private PetEndPoint petEndPoint;// = new PetEndPoint(); можно закомментить т.к. @Steps уже создает єтот обьект

    @Before
    public void precondition(){
        System.out.println("!!!!!!CREATE EXISTING PET!!!!!!");
        petModel = new PetModel(
                "1448",
                new CategoryModel("1448", "млекопитающие"),
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
    public void updatePetTest(){
        System.out.println("!!!!!!UPDATE EXISTING PET!!!!!!");
        petModel.setName("Шимпанзе");
        petModel.setStatus("unavailable");

        petEndPoint
                .updatePet(petModel)
                .statusCode(200);
        petEndPoint
                .getPetById(petModel.getId())
                .statusCode(400);
    }

    @After
    public void postcondition(){
        System.out.println("!!!!!!DELETE PET!!!!!!");
        petEndPoint
                .deletePetById(petModel.getId())
                .statusCode(200);

    }
}
