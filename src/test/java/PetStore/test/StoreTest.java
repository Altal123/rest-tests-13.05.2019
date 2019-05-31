package PetStore.test;

import PetStore.endpoint.StoreEndPoint;
import PetStore.models.StoreModel;
import org.junit.Test;

public class StoreTest {

    private StoreModel storeModel;
    private StoreEndPoint storeEndPoint = new StoreEndPoint();

    @Test
    public void placeOrderTest(){

//        System.out.println(new LocalDate());

        storeModel = new StoreModel(
                9,
                1448,
                1,
                "2019-05-31",
                "placed",
                false
        );

        storeEndPoint
                .placeOrder(storeModel)
                .statusCode(200);

        storeEndPoint
                .getOrderByID(9)
                .statusCode(200);
    }

    @Test
    public void deleteOrderById(){
        storeEndPoint
                .deleteOrderById(9)
                .statusCode(200);
    }
}
