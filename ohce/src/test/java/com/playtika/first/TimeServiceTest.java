package com.playtika.first;

import com.playtika.first.TimeService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TimeServiceTest {

    TimeService timeService = new TimeService();

    @ParameterizedTest
    @ValueSource(ints = {20,21,22,23,24})
    public void isNight_givenBetween20And24_returnTrue(int hour) {
        assertThat(timeService.isNight(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    public void isNight_givenBetween0And6_returnTrue(int hour) {
        assertThat(timeService.isNight(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10,11,12,13,14,15,16,17,18,19})
    public void isNight_givenBetween7And19_returnFalse(int hour) {
        assertThat(timeService.isNight(hour)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10,11,12})
    public void isMorning_givenBetween7And12_returnTrue(int hour) {
        assertThat(timeService.isMorning(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {13,14,15,16,17,18,19,20,21,22,23,24,0,1,2,3,4,5,6})
    public void isMorning_givenBetween13And6_returnFalse(int hour) {
        assertThat(timeService.isMorning(hour)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {13,14,15,16,17,18,19})
    public void isAfternoon_givenBetween13And19_returnTrue(int hour) {
        assertThat(timeService.isAfternoon(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10,11,12,20,21,22,23,24,0,1,2,3,4,5,6})
    public void isAfternoon_givenBetween0And12And20And24_returnFalse(int hour) {
        assertThat(timeService.isAfternoon(hour)).isFalse();
    }
}
