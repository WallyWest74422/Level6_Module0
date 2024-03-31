package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;

//    @Mock
    String name;

    @Mock
CellPhone cp;

    @Mock
    Car car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deliveryDriver = new DeliveryDriver(name, car, cp);
    }

    @Test
    void itShouldWasteTime() {
        //given
boolean expectedWaste = true;
        //when
 when(cp.browseCatMemes()).thenReturn(true);
 boolean actualWaste = deliveryDriver.wasteTime();
        //then
        assertEquals(expectedWaste, actualWaste);
    }

    @Test
    void itShouldRefuel() {
        //given
boolean expectedFill = true;
int amount = 100;
        //when
when(car.fillTank(amount)).thenReturn(true);
boolean actualFill = deliveryDriver.refuel(amount);
        //then
        assertEquals(expectedFill, actualFill);
    }

    @Test
    void itShouldContactCustomer() {
        //given
boolean expectedCall = true;
String number = "911";
        //when
when(cp.call(number)).thenReturn(true);
boolean actualCall = deliveryDriver.contactCustomer(number);
        //then
        assertEquals(expectedCall, actualCall);
    }

}