package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sleeptrackerfunction.AverageSessionDuration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AverageSessionDurationTest {
    List<SleepingSession> testList = new ArrayList<>();
    SleepingSession sleepingSession1 = new SleepingSession(LocalDateTime.of(2024, 12, 31, 23, 0),
            LocalDateTime.of(2025, 1, 1, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession2 = new SleepingSession(LocalDateTime.of(2025, 1, 1, 23, 0),
            LocalDateTime.of(2025, 1, 2, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession3 = new SleepingSession(LocalDateTime.of(2025, 2, 2, 0, 0),
            LocalDateTime.of(2025, 2, 2, 6, 0), SleepQuality.GOOD);


    @Test
    void averageSessionDurationTestWithTwoHours() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        AverageSessionDuration averageSession = new AverageSessionDuration();
        SleepAnalysisResult result = new SleepAnalysisResult("Средняя продолжительность сна составила " + 2 + " часов " + 0 + " минут", LocalTime.of(2, 0));
        Assertions.assertEquals(averageSession.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void averageSessionDurationTestWithThreeNumbers() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        AverageSessionDuration averageSession = new AverageSessionDuration();
        SleepAnalysisResult result = new SleepAnalysisResult("Средняя продолжительность сна составила " + 3 + " часов " + 20 + " минут", LocalTime.of(3, 20));
        Assertions.assertEquals(averageSession.apply(testList).getDescription(), result.getDescription());
    }
}
