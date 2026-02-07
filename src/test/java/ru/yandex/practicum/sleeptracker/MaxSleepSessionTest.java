package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sleeptrackerfunction.MaxSleepSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MaxSleepSessionTest {
    List<SleepingSession> testList = new ArrayList<>();
    SleepingSession sleepingSession1 = new SleepingSession(LocalDateTime.of(2024, 12, 31, 23, 0),
            LocalDateTime.of(2025, 1, 1, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession2 = new SleepingSession(LocalDateTime.of(2025, 1, 1, 23, 0),
            LocalDateTime.of(2025, 1, 2, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession3 = new SleepingSession(LocalDateTime.of(2025, 2, 2, 0, 0),
            LocalDateTime.of(2025, 2, 2, 6, 0), SleepQuality.GOOD);


    @Test
    void maxSleepSessionTestTwoNumbers() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        MaxSleepSession maxSleepSession = new MaxSleepSession();
        SleepAnalysisResult result = new SleepAnalysisResult("Максимальное время сна составило " + 2 + " часов " +
                0 + " минут.", LocalTime.of(2, 0));
        Assertions.assertEquals(maxSleepSession.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void maxSleepSessionTestWithThreeNumbers() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        MaxSleepSession maxSleepSession = new MaxSleepSession();
        SleepAnalysisResult result = new SleepAnalysisResult("Максимальное время сна составило " + 6 + " часов " +
                0 + " минут.", LocalTime.of(6, 0));
        Assertions.assertEquals(maxSleepSession.apply(testList).getDescription(), result.getDescription());
    }
}
