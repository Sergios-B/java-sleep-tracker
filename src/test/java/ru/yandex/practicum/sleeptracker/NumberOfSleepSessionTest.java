package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sleeptrackerfunction.NumberOfSleepSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NumberOfSleepSessionTest {
    List<SleepingSession> testList = new ArrayList<>();
    SleepingSession sleepingSession1 = new SleepingSession(LocalDateTime.of(2024, 12, 31, 23, 0),
            LocalDateTime.of(2025, 1, 1, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession2 = new SleepingSession(LocalDateTime.of(2025, 1, 1, 23, 0),
            LocalDateTime.of(2025, 1, 2, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession3 = new SleepingSession(LocalDateTime.of(2025, 2, 2, 0, 0),
            LocalDateTime.of(2025, 2, 2, 6, 0), SleepQuality.BAD);


    @Test
    void numberOfSleepSessionTestWithTwoHours() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        NumberOfSleepSession numberOfSleepSession = new NumberOfSleepSession();
        SleepAnalysisResult result = new SleepAnalysisResult("Зафиксировано " + 2 + " сессий сна.", 2);
        Assertions.assertEquals(numberOfSleepSession.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void numberOfSleepSessionTestWithWithThreeNumbers() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        NumberOfSleepSession numberOfSleepSession = new NumberOfSleepSession();
        SleepAnalysisResult result = new SleepAnalysisResult("Зафиксировано " + 3 + " сессий сна.", 3);
        Assertions.assertEquals(numberOfSleepSession.apply(testList).getDescription(), result.getDescription());
    }
}
