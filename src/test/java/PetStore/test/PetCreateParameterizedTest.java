package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class PetCreateParameterizedTest {

    private String petId;
    private String name;
    private int expectedStatusCode;

    @Steps
    private PetEndPoint petEndPoint;

    public PetCreateParameterizedTest(String petId, String name, int expectedStatusCode){
        this.petId = petId;
        this.name = name;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Test
    public void createPetTest() {
        PetModel petModel = getPetModel(petId, name);
        petEndPoint
                .createPet(petModel)
                .statusCode(expectedStatusCode);
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"1448", "Макака", 200},
                {"-1", "Terminator", 200},
                {"0", "#$%^表", 200},
                {555555555, "___", 500},
        });
    }

    private PetModel getPetModel(String petId, String name) {
        return new PetModel(
                petId,
                new CategoryModel(petId, name),
                name,
                new String[]{"животное"},
                new TagModel[]{new TagModel()},
                "available"
        );
    }

}
