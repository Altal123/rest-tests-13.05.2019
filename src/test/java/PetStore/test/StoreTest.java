package PetStore.test;

import PetStore.endpoint.StoreEndPoint;
import PetStore.models.StoreModel;
import org.testng.annotations.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

public class StoreTest {

    private StoreModel storeModel;
    private StoreEndPoint storeEndPoint = new StoreEndPoint();

    @BeforeTest
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

    @AfterTest
    public void deleteOrderById(){
        storeEndPoint
                .deleteOrderById(11)
                .statusCode(200);
    }
}
