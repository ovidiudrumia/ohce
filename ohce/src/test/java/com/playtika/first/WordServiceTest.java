package com.playtika.first;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WordServiceTest {

    @Spy
    WordService wordService;
    
    @ParameterizedTest
    @ValueSource(strings = {"otto", "radar", "level", "rotor"})
    public void isPalindrome_givenPalindrome_returnTrue(String word) {
        assertThat(wordService.isPalindrome(word)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"clear", "neat", "heart"})
    public void isPalindrome_givenNotPalindrome_returnFalse(String word) {
        assertThat(wordService.isPalindrome(word)).isFalse();
    }

    @Test
    public void isPalindrome_givenWord_checkWordIsValidIsCalled() {
        doNothing().when(wordService).checkWordIsValid(anyString());
        doReturn("name").when(wordService).reverseWord(anyString());

        wordService.isPalindrome("a");

        verify(wordService, times(1)).checkWordIsValid("a");
    }

    @Test
    public void isPalindrome_givenWord_reverseWordIsCalled() {
        doNothing().when(wordService).checkWordIsValid(anyString());

        wordService.isPalindrome("a");

        verify(wordService, times(1)).reverseWord("a");
    }

    @Test
    public void checkWordIsValid_givenEmpty_throwException() {
        assertThrows(IllegalArgumentException.class, () -> {
            wordService.checkWordIsValid("");
        });
    }

    @ParameterizedTest
    @CsvSource({"1", "a1", "1a", "1a4", "11aradar", "223level333", "12r32o3t13o1r"})
    public void checkWordIsValid_givenWordWithLetter_throwException(String notWord) {
        assertThrows(IllegalArgumentException.class, () -> {
            wordService.checkWordIsValid(notWord);
        });
    }

    @ParameterizedTest
    @CsvSource({"clear,raelc", "neat,taen", "heart,traeh", "otto,otto", "radar,radar", "level,level", "rotor,rotor"})
    public void reverseWord_givenWord_returnReverse(String input, String expected) {
        assertThat(wordService.reverseWord(input)).isEqualTo(expected);
    }

    @Test
    public void reverseWord_word_checkWordIsValidIsCalled() {
        doNothing().when(wordService).checkWordIsValid(anyString());

        wordService.reverseWord("a");

        verify(wordService, times(1)).checkWordIsValid("a");
    }
}
