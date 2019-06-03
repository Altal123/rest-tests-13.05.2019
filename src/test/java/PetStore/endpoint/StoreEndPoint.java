package PetStore.endpoint;

import PetStore.models.StoreModel;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoreEndPoint {

    @Step
    public ValidatableResponse placeOrder(StoreModel storeModel){
        System.out.println("!!!!!!PLACE NEW ODER!!!!!!");
        return given()
                .body(storeModel)
                .post(Config.PLACE_ORDER)
                .then();
//                .log().all();
    }

    @Step
    public ValidatableResponse getOrderByID(int orderId){
        System.out.println("!!!!!!GET ODER BY ID!!!!!!");
        return given()
                .get(Config.GET_ORDER_BY_ID, orderId)
                .then()
                .log().all();
    }

    @Step
    public ValidatableResponse deleteOrderById(int orderId){
        System.out.println("!!!!!!DELETE ORDER BY ID!!!!!!");
        return given()
                .delete(Config.DELETE_ORDER_BY_ID, orderId)
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
