package PetStore.test;

import PetStore.endpoint.PetEndPoint;
import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@Concurrent
@RunWith(SerenityParameterizedRunner.class)
public class PetCreateCombinationsTest {

    private final String name;
    private final int statusCode;

    @Steps
    private PetEndPoint petEndPoint;

    public PetCreateCombinationsTest(String name, int statusCode) {
        this.name = name;
        this.statusCode = statusCode;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"makaka", 200},
                {"-1", 200},
                {"ewdwd", 200}
        });
    }

    @Test
    public void createPetTest() {
        PetModel petModel = new PetModel(
                "1448",
                new CategoryModel("1448", name),
                name,
                new String[]{"животное"},
                new TagModel[]{new TagModel()},
                "available"
        );
        petEndPoint
                .createPet(petModel)
                .statusCode(statusCode);
    }
}
