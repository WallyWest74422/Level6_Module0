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
        myDonutShop.openForTheDay();
        when(bs.getDonutsRemaining()).thenReturn(1);
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        when(ps.charge(order)).thenReturn(true);
        //when
        myDonutShop.takeOrder(order);
        //then
        verify(ds, times(1)).scheduleDelivery(order);
        verify(ps, times(1)).charge(order);
        verify(bs, times(1)).removeDonuts(1);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
        myDonutShop.openForTheDay();
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                bs.getDonutsRemaining()+1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
        myDonutShop.closeForTheDay();
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        //then
        assertEquals(exceptionThrown.getMessage(), "Sorry we're currently closed");
        assertEquals(0, bs.getDonutsRemaining());
        verify(bs, times(1)).throwAwayLeftovers();
    }

}