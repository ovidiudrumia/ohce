package com.playtika.second;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OhceTest {

    @Spy
    @InjectMocks
    private Ohce ohce;

    @Mock
    private ConsoleService consoleService;
    @Mock
    private TimeService timeService;

    @Test
    public void startOhce_givenName_methodsAreCalledInProperOrder() {
        doNothing().when(ohce).processWords();
        ohce.startOhce("name");

        verify(ohce, times(1)).greeting("name");
        verify(ohce, times(1)).processWords();
        verify(ohce, times(1)).adios("name");
    }

    @Test
    public void greeting_givenMorningAndName_consoleTimeServiceIsMorningAndServiceWriteLineIsCalled() {
        doReturn(true).when(timeService).isMorning();

        ohce.greeting("name");

        verify(consoleService, times(1)).writeLine("¡Buenos días name!");
        verify(timeService, times(1)).isMorning();
    }

    @Test
    public void greeting_givenAfternoonAndName_consoleTimeServiceIsAfternoonAndServiceWriteLineIsCalled() {
        doReturn(false).when(timeService).isMorning();
        doReturn(true).when(timeService).isAfternoon();

        ohce.greeting("name");

        verify(consoleService, times(1)).writeLine("¡Buenos tardes name!");
        verify(timeService, times(1)).isMorning();
        verify(timeService, times(1)).isAfternoon();
    }

    @Test
    public void greeting_givenNightAndName_consoleTimeServiceIsNightAndServiceWriteLineIsCalled() {
        doReturn(false).when(timeService).isMorning();
        doReturn(false).when(timeService).isAfternoon();
        doReturn(true).when(timeService).isNight();

        ohce.greeting("name");

        verify(consoleService, times(1)).writeLine("¡Buenos noches name!");
        verify(timeService, times(1)).isMorning();
        verify(timeService, times(1)).isAfternoon();
        verify(timeService, times(1)).isNight();
    }

    @Test
    public void processWords_givenWord_consoleServiceReadLineAndReverseIsCalled() {
        doReturn("reverse").when(ohce).reverse(anyString());
        doReturn("input").doReturn("Stop!").when(consoleService).readLine();

        ohce.processWords();

        verify(consoleService, times(2)).readLine();
        verify(ohce, times(2)).reverse("input");
        verify(consoleService).writeLine("reverse");
    }

    @Test
    public void processWords_givenPalindrome_reverseOutputAndBonitaPalabraReturned() {
        doReturn("reverse").when(ohce).reverse(anyString());
        doReturn("reverse").doReturn("Stop!").when(consoleService).readLine();

        ohce.processWords();

        verify(consoleService).writeLine("reverse\r\n¡Bonita palabra!");
    }

    @ParameterizedTest
    @CsvSource({"a,a","aba,aba","ana,ana","channel,lennahc","great,taerg","hello,olleh"})
    public void reverse_givenWord_returnReverse(String word, String reverse) {
        assertThat(ohce.reverse(word)).isEqualTo(reverse);
    }

    @Test
    public void processWords_givenMultipleWords_consoleWriteLineCalledMultipleTimes() {
        doReturn("reverse").when(ohce).reverse(anyString());
        when(consoleService.readLine()).thenReturn("word").thenReturn("word").thenReturn("Stop!");

        ohce.processWords();

        verify(consoleService, times(2)).writeLine("reverse");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pablo", "Ovidiu", "Geta"})
    public void adios_givenName_consoleWriteLineIsCalledWithGoodBye(String name) {
        ohce.adios(name);

        verify(consoleService).writeLine("Adios " + name);
    }
}
