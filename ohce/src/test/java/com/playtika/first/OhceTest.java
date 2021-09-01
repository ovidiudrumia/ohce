package com.playtika.first;

import com.playtika.first.ConsoleService;
import com.playtika.first.Ohce;
import com.playtika.first.TimeService;
import com.playtika.first.WordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OhceTest {

    @Spy
    @InjectMocks
    private Ohce ohce;

    @Mock
    private WordService wordService;

    @Mock
    private TimeService timeService;

    @Mock
    private ConsoleService consoleService;

    @Test
    public void startOhce_givenName_methodsCalledInProperOrder() {
        doNothing().when(ohce).greeting(anyString());
        doNothing().when(ohce).processWords();
        doNothing().when(ohce).adios(anyString());
        InOrder inOrder = inOrder(ohce);

        ohce.startOhce("Pedro");

        inOrder.verify(ohce).greeting("Pedro");
        inOrder.verify(ohce).processWords();
        inOrder.verify(ohce).adios("Pedro");
    }

    @Test
    public void greeting_givenNameAndIsMorning_correctGreetingIsWrittenInConsole() {
        doReturn(true).when(timeService).isMorning();
        doReturn("dias").when(ohce).buenasDias("Pedro");
        doNothing().when(consoleService).writeLine(anyString());

        ohce.greeting("Pedro");

        verify(ohce, times(1)).buenasDias("Pedro");
        verify(consoleService, times(1)).writeLine("dias");
    }

    @Test
    public void greeting_givenNameAndIsAfternoon_correctGreetingIsWrittenInConsole() {
        doReturn(false).when(timeService).isMorning();
        doReturn(true).when(timeService).isAfternoon();
        doReturn("tardes").when(ohce).buenasTardes("Pedro");
        doNothing().when(consoleService).writeLine(anyString());

        ohce.greeting("Pedro");

        verify(ohce, times(1)).buenasTardes("Pedro");
        verify(consoleService, times(1)).writeLine("tardes");
    }

    @Test
    public void greeting_givenNameAndIsANight_correctGreetingIsWrittenInConsole() {
        doReturn(false).when(timeService).isMorning();
        doReturn(false).when(timeService).isAfternoon();
        doReturn(true).when(timeService).isNight();
        doReturn("noches").when(ohce).buenasNoches("Pedro");
        doNothing().when(consoleService).writeLine(anyString());

        ohce.greeting("Pedro");

        verify(ohce, times(1)).buenasNoches("Pedro");
        verify(consoleService, times(1)).writeLine("noches");
    }

    @Test
    public void adios_givenName_returnAdiosName() {
        doNothing().when(consoleService).writeLine(anyString());

        ohce.adios("Pedro");

        verify(consoleService, times(1)).writeLine("Adios Pedro");
    }

    @Test
    public void buenasNoches_givenName_returnBuenasNochesName() {
        assertThat(ohce.buenasNoches("Pablo")).isEqualTo("¡Buenas noches Pablo!");
    }

    @Test
    public void buenasTardes_givenName_returnBuenasTardesName() {
        assertThat(ohce.buenasTardes("Pablo")).isEqualTo("¡Buenas tardes Pablo!");
    }

    @Test
    public void buenasDias_givenName_returnBuenasDiasName() {
        assertThat(ohce.buenasDias("Pablo")).isEqualTo("¡Buenas días Pablo!");
    }

    @Test
    public void getReverseWordResponse_givenWord_wordServiceReverseCalled() {
        doReturn("a").when(wordService).reverseWord(anyString());

        ohce.getReverseWordResponse("a");

        verify(wordService, times(1)).reverseWord("a");
    }

    @Test
    public void processWords_givenNothing_consoleServiceReadLineIsCalled() {
        doReturn("Stop!").when(consoleService).readLine();

        ohce.processWords();

        verify(consoleService, times(1)).readLine();
    }

    @Test
    public void processWords_givenWordFromConsole_getReverseWordResponseIsCalled() {
        when(consoleService.readLine()).thenReturn("word", "Stop!");

        ohce.processWords();

        verify(ohce, times(1)).getReverseWordResponse("word");
    }

    @Test
    public void processWords_givenGivenReverseWordResponse_getConsoleWriteLineIsCalledUntilStopCommand() {
        when(consoleService.readLine()).thenReturn("word", "word", "word", "Stop!");
        doReturn("reverseWord").when(ohce).getReverseWordResponse("word");

        ohce.processWords();

        verify(consoleService, times(3)).writeLine("reverseWord");
    }

    @Test
    public void processWords_givenStopCommand_getReverseWordAndConsoleWriteLineAreNotCalled() {
        doReturn("word1", "word2", "Stop!").when(consoleService).readLine();

        ohce.processWords();

        verify(consoleService, times(0)).writeLine("reverseWord");
        verify(ohce, times(0)).getReverseWordResponse("reverseWord");
    }

    @Test
    public void getReverseWordResponse_givenPalindrome_returnWordServiceReverseResponseAndBonitaPalabra() {
        doReturn("a").when(wordService).reverseWord(anyString());
        doReturn(true).when(wordService).isPalindrome(anyString());

        String reverseWord = ohce.getReverseWordResponse("a");

        verify(wordService, times(1)).reverseWord("a");
        verify(wordService, times(1)).isPalindrome("a");
        assertThat(reverseWord).isEqualTo("a\r\n¡Bonita palabra!");
    }

    @Test
    public void getReverseWordResponse_givenNonPalindrome_returnWordServiceReverse() {
        doReturn("a").when(wordService).reverseWord(anyString());
        doReturn(false).when(wordService).isPalindrome(anyString());

        String reverseWord = ohce.getReverseWordResponse("a");

        verify(wordService, times(1)).reverseWord("a");
        verify(wordService, times(1)).isPalindrome("a");
        assertThat(reverseWord).isEqualTo("a");
    }

    @Test
    public void getReverseWordResponse_givenNonWord_returnPleaseEnterWord() {
        doThrow(new IllegalArgumentException()).when(wordService).isPalindrome(anyString());

        String reverseWord = ohce.getReverseWordResponse("a");

        assertThat(reverseWord).isEqualTo("Please enter a word that is made up only of letters! " +
                "To stop enter the following command: Stop!");
    }
}
