package com.playtika.second;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TimeServiceTest {

    private TimeService timeService = new TimeService();

    @ParameterizedTest
    @ValueSource(ints = {6,7,8,9,10,11,12})
    public void isMorning_givenHourBetween6and12_returnTrue(int hour) {
        assertThat(timeService.isMorning(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {13,14,15,16,17,18,19, 20, 21, 22, 23, 24, 0, 1, 2, 3, 4, 5})
    public void isMorning_givenHourBetween13and24OrBetween0and5_returnFalse(int hour) {
        assertThat(timeService.isMorning(hour)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {13,14,15,16,17,18,19})
    public void isAfternoon_givenHourBetween13and19_returnTrue(int hour) {
        assertThat(timeService.isAfternoon(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 21, 22, 23, 24, 0, 1, 2, 3, 4, 5, 6,7,8,9,10,11,12})
    public void isAfternoon_givenHourBetween20and24OrBetween0and12_returnFalse(int hour) {
        assertThat(timeService.isAfternoon(hour)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 21, 22, 23, 24, 0, 1, 2, 3, 4, 5, 6})
    public void isNight_givenHourBetween20and24OrBetween0And6_returnTrue(int hour) {
        assertThat(timeService.isNight(hour)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7,8,9,10,11,12,13,14,15,16,17,18,19})
    public void isNight_givenHourBetween7and19_returnFalse(int hour) {
        assertThat(timeService.isNight(hour)).isFalse();
    }
}
