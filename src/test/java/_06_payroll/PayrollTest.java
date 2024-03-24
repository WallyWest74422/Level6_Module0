package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
double wage = 1.5;
int hours = 10;
double expected= 15;
        //when
double actual = payroll.calculatePaycheck(wage, hours);
        //then
        assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
int miles = 1000;
double expected = 575;
        //when
double actual = payroll.calculateMileageReimbursement(miles);
        //then
        assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
String name = "Dave the Intern";
double wage = 1.0;
String expected = "Hello Dave the Intern, We are pleased to offer you an hourly wage of 1.0";
        //when
String actual = payroll.createOfferLetter(name, wage);
        //then
        assertEquals(expected, actual);
    }

}