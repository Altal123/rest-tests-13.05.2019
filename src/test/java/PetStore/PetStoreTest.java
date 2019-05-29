package PetStore;

import PetStore.models.CategoryModel;
import PetStore.models.PetModel;
import PetStore.models.TagModel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PetStoreTest {

    private enum Status{
        AVAILABLE,
        PENDING,
        SOLD
    }

    private int petId = 1448;
    private String name = "Макака";
    private String status = "available";

    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    @Test
    public void getPetByIdTest(){
        System.out.println("!!!!!!GET PET BY ID!!!!!!");
        ValidatableResponse response = RestAssured.given()
//              .basePath()
//                .log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", is (petId))
                .body("name", is (name))
                .body("status", is (status));
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

    @Before
    public void createPetTest() {
        System.out.println("!!!!!!CREATE NEW PET!!!!!!");
        PetModel petModel = getPetModel();

        ValidatableResponse response = RestAssured.given()
//                .basePath()
//                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
//                .contentType("application/json")
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
//                .log().all()
                .statusCode(200);

        getPetByIdTest();
    }


    @Test
    public void updatePetTest(){
        System.out.println("!!!!!!UPDATE EXISTING PET!!!!!!");

        name = "Шимпанзе";
        status = "unavailable";
        PetModel petModel = getPetModel();

        ValidatableResponse response = RestAssured.given()
//                .basePath()
//                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
//                .contentType("application/json")
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
//                .log().all()
                .statusCode(200);

        getPetByIdTest();

    }

    @After
    public void deletePetByIdTest() {
        System.out.println("!!!!!!DELETE PET!!!!!!");
        ValidatableResponse response = RestAssured.given()
//              .basePath()
//                .log().uri()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then()
//                .log().all()
                .statusCode(200);
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
