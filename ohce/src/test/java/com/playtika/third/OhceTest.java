package com.playtika.third;

import com.playtika.third.Ohce;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OhceTest {

    @Spy
    Ohce ohce;

    @Test
    public void startOhce_givenName_methodsAreCalledInRightOrder() {
        doNothing().when(ohce).greeting("Pablo");
        doNothing().when(ohce).processWords();
        doNothing().when(ohce).adios("Pablo");
        InOrder inOrder = inOrder(ohce);

        ohce.runOhce("Pablo");

        inOrder.verify(ohce).greeting("Pablo");
        inOrder.verify(ohce).processWords();
        inOrder.verify(ohce).adios("Pablo");
    }

    @Test
    public void greeting_givenMorningAndName_returnBuenosDias() {
        doReturn(true).when(ohce).isMorning(anyInt());
        doNothing().when(ohce).writeLine(anyString());

        ohce.greeting("Pablo");

        verify(ohce).isMorning(anyInt());
        verify(ohce).writeLine("¡Buenos días " + "Pablo!");
    }

    @Test
    public void greeting_givenAfternoonAndName_returnBuenasTardes() {
        doReturn(false).when(ohce).isMorning(anyInt());
        doReturn(true).when(ohce).isAfternoon(anyInt());
        doNothing().when(ohce).writeLine(anyString());

        ohce.greeting("Pablo");

        verify(ohce).isMorning(anyInt());
        verify(ohce).isAfternoon(anyInt());
        verify(ohce).writeLine("¡Buenos tardes " + "Pablo!");
    }

    @Test
    public void greeting_givenNightAndName_returnBuenasNoches() {
        doReturn(false).when(ohce).isMorning(anyInt());
        doReturn(false).when(ohce).isAfternoon(anyInt());
        doReturn(true).when(ohce).isNight(anyInt());
        doNothing().when(ohce).writeLine(anyString());

        ohce.greeting("Pablo");

        verify(ohce).isMorning(anyInt());
        verify(ohce).isAfternoon(anyInt());
        verify(ohce).writeLine("¡Buenas noches " + "Pablo!");
    }

    @ParameterizedTest
    @ValueSource(ints = {20,21,22,23,24,0,1,2,3,4,5,6})
    public void isNight_givenHourBetween20And24OrBetween0And6_returnTrue(int hour) {
        assertTrue(ohce.isNight(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {13,14,15,16,17,18,19})
    public void isAfternoon_givenHourBetween13And19_returnTrue(int hour) {
        assertTrue(ohce.isAfternoon(hour));
    }
 
    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10,11,12})
    public void isMorning_givenHourBetween7And12OrBetween0And6_returnTrue(int hour) {
        assertTrue(ohce.isMorning(hour));
    }

}
