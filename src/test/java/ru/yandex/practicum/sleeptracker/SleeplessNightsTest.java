package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import sleeptrackerfunction.SleeplessNights;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsTest {
    @Test
    public void testEmptySessions() {
        SleeplessNights sleeplessNights = new SleeplessNights();
        SleepAnalysisResult result = sleeplessNights.apply(List.of());
        assertEquals("Количество бессонных ночей: 0", result.getDescription());
    }

    @Test
    public void testSingleSleeplessNight() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2024, 1, 1, 23, 0),
                LocalDateTime.of(2024, 1, 2, 5, 30), SleepQuality.BAD);
        SleeplessNights sleeplessNights = new SleeplessNights();
        SleepAnalysisResult result = sleeplessNights.apply(List.of(session));
        assertEquals("Количество бессонных ночей: 0", result.getDescription());
    }

    @Test
    public void testMultipleNights() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2024, 1, 1, 22, 0),
                LocalDateTime.of(2024, 1, 2, 8, 30), SleepQuality.BAD
        );
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2024, 1, 3, 23, 0),
                LocalDateTime.of(2024, 1, 4, 5, 30), SleepQuality.BAD
        );
        SleeplessNights sleeplessNights = new SleeplessNights();
        SleepAnalysisResult result = sleeplessNights.apply(List.of(session1, session2));
        assertEquals("Количество бессонных ночей: 1", result.getDescription());
    }

    @Test
    public void testComplexCase() {
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2024, 1, 1, 0, 30),
                LocalDateTime.of(2024, 1, 2, 6, 30), SleepQuality.BAD
        );
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2024, 1, 3, 21, 0),
                LocalDateTime.of(2024, 1, 4, 7, 30), SleepQuality.BAD
        );
        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2024, 1, 5, 0, 0),
                LocalDateTime.of(2024, 1, 5, 8, 0), SleepQuality.BAD
        );
        SleeplessNights sleeplessNights = new SleeplessNights();
        SleepAnalysisResult result = sleeplessNights.apply(List.of(session1, session2, session3));
        assertEquals("Количество бессонных ночей: 3", result.getDescription());
    }
}