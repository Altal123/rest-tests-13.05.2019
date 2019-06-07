package PetStore.endpoint;

import PetStore.models.StoreModel;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreEndPoint {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName()); //org.slf4j.Logger;

    @Step
    public ValidatableResponse placeOrder(StoreModel storeModel){
        logger.info("!!!!!!PLACE NEW ODER!!!!!!");
        return given()
                .body(storeModel)
                .post(Config.PLACE_ORDER)
                .then();
//                .log().all();
    }

    @Step
    public ValidatableResponse getOrderByID(int orderId){
        logger.info("!!!!!!GET ODER BY ID!!!!!!");
        return given()
                .get(Config.GET_ORDER_BY_ID, orderId)
                .then()
                .log().all();
    }

    @Step
    public ValidatableResponse deleteOrderById(int orderId){
        logger.info("!!!!!!DELETE ORDER BY ID!!!!!!");
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
