package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sleeptrackerfunction.AverageSessionDuration;
import sleeptrackerfunction.Chronotype;
import sleeptrackerfunction.DefinitionOfChronotype;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DefinitionOfChronotypeTest {
    List<SleepingSession> testList = new ArrayList<>();
    SleepingSession sleepingSession1 = new SleepingSession(LocalDateTime.of(2024, 12, 31, 21, 0),
            LocalDateTime.of(2025, 1, 1, 5, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession2 = new SleepingSession(LocalDateTime.of(2025, 1, 1, 21, 0),
            LocalDateTime.of(2025, 1, 2, 5, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession3 = new SleepingSession(LocalDateTime.of(2025, 2, 2, 0, 0),
            LocalDateTime.of(2025, 2, 2, 8, 0), SleepQuality.GOOD);
    SleepingSession sleepingSession4 = new SleepingSession(LocalDateTime.of(2025, 2, 4, 1, 0),
            LocalDateTime.of(2025, 2, 4, 12, 0), SleepQuality.GOOD);

    @Test
    void definitionOfChronotypeTestWithTwoNumber() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        DefinitionOfChronotype definitionOfChronotype = new DefinitionOfChronotype();
        SleepAnalysisResult result = new SleepAnalysisResult("Вы жаворонок!", Chronotype.LARK);
        Assertions.assertEquals(definitionOfChronotype.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void definitionOfChronotypeTestWithThreeNumbers() {
        testList.add(sleepingSession1);
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        DefinitionOfChronotype definitionOfChronotype = new DefinitionOfChronotype();
        SleepAnalysisResult result = new SleepAnalysisResult("Вы жаворонок!", Chronotype.LARK);
        Assertions.assertEquals(definitionOfChronotype.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void definitionOfChronotypeTestWithThreeNumbers1() {
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        testList.add(sleepingSession4);
        DefinitionOfChronotype definitionOfChronotype = new DefinitionOfChronotype();
        SleepAnalysisResult result = new SleepAnalysisResult("Странная птица, вроде голубь", Chronotype.PIGEON);
        Assertions.assertEquals(definitionOfChronotype.apply(testList).getDescription(), result.getDescription());
    }

    @Test
    void definitionOfChronotypeTestWithTwoNumber1() {
        testList.add(sleepingSession2);
        testList.add(sleepingSession3);
        DefinitionOfChronotype definitionOfChronotype = new DefinitionOfChronotype();
        SleepAnalysisResult result = new SleepAnalysisResult("Странная птица, вроде голубь", Chronotype.PIGEON);
        Assertions.assertEquals(definitionOfChronotype.apply(testList).getDescription(), result.getDescription());
    }
}
