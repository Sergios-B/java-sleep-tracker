package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sleeptrackerfunction.BadSleepSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BadSleepSessionTest {
    List<SleepingSession> testList = new ArrayList<>();
    SleepingSession sleepingSession1 = new SleepingSession(LocalDateTime.of(2024, 12, 31, 23, 0),
            LocalDateTime.of(2025, 1, 1, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession2 = new SleepingSession(LocalDateTime.of(2025, 1, 1, 23, 0),
            LocalDateTime.of(2025, 1, 2, 1, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession3 = new SleepingSession(LocalDateTime.of(2025, 2, 2, 0, 0),
            LocalDateTime.of(2025, 2, 2, 6, 0), SleepQuality.BAD);


    @Test
    void badSleepSessionTestWithTwoHours() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        BadSleepSession badSleepSession = new BadSleepSession();
        SleepAnalysisResult result = new SleepAnalysisResult("Количество сессий с плохим качеством сна составило " + 0 + ".", 0);
        Assertions.assertEquals(badSleepSession.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void badSleepSessionTestWithWithThreeNumbers() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        BadSleepSession badSleepSession = new BadSleepSession();
        SleepAnalysisResult result = new SleepAnalysisResult("Количество сессий с плохим качеством сна составило " + 1 + ".", 0);
        Assertions.assertEquals(badSleepSession.apply(testList).getDescription(), result.getDescription());
    }
}
