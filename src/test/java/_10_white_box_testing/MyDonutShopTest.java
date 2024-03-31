package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;

    @Mock
    DeliveryService ds;

    @Mock
    PaymentService ps;

    @Mock
    BakeryService bs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        myDonutShop = new MyDonutShop(ps, ds, bs);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
        myDonutShop.openForTheDay();
        myDonutShop.takeOrder(order);
        //then
        verify(myDonutShop, times(1)).addOrder(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
        myDonutShop.openForTheDay();
        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
   //     verify(myDonutShop, never()).takeOrder(order);

    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given

        //when

        //then
    }

}