package PetStore.test;

import PetStore.endpoint.StoreEndPoint;
import PetStore.models.StoreModel;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SerenityRunner.class)
public class StoreTest {


    private StoreModel storeModel;

    @Steps
    private StoreEndPoint storeEndPoint = new StoreEndPoint();

    @Test
    public void placeOrderTest(){
        storeModel = new StoreModel(
                11,
                1448,
                1,
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                "placed",
                false
        );
        storeEndPoint
                .placeOrder(storeModel)
                .statusCode(200);
    }

    @Test
    public void getOrderByID(){
        storeEndPoint
                .getOrderByID(11)
                .statusCode(200)
                .body("id", is (11))
                .body("petId", is (1448))
                .body("quantity", is (1))
                .body("status", is ("placed"));
    }

    @Test
    public void deleteOrderById(){
        storeEndPoint
                .deleteOrderById(11)
                .statusCode(200);
    }
}
