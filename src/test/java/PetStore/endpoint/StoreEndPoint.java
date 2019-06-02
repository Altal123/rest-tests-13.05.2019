package PetStore.endpoint;

import PetStore.models.PetModel;
import PetStore.models.StoreModel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class StoreEndPoint {

    public ValidatableResponse placeOrder(StoreModel storeModel){
        System.out.println("!!!!!!PLACE NEW ODER!!!!!!");
        return given()
                .body(storeModel)
                .post(Config.PLACE_ORDER)
                .then();
//                .log().all();
    }

    public ValidatableResponse getOrderByID(int orderId){
        System.out.println("!!!!!!GET ODER BY ID!!!!!!");
        return given()
                .get(Config.GET_ORDER_BY_ID, orderId)
                .then()
                .log().all();
    }

    public ValidatableResponse deleteOrderById(int orderId){
        System.out.println("!!!!!!DELETE ORDER BY ID!!!!!!");
        return given()
                .delete(Config.DELETE_ORDER_BY_ID, orderId)
                .then();
//                .log().all();
    }

    private RequestSpecification given(){
        return RestAssured.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json");
//                .log().uri();
    }
}
