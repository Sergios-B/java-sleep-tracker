package sleeptrackerfunction;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class MaxSleepSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int maxSleep = sleepingSessions.stream()
                .mapToInt(session -> (int) Duration.between(session.getFallingAsleep(), session.getAwakening()).toMinutes())
                .max()
                .orElse(0);
        int hours = maxSleep / 60;
        int minutes = maxSleep % 60;
        LocalTime localTime = LocalTime.of(hours, minutes);
        return new SleepAnalysisResult("Максимальное время сна составило " + localTime.getHour() + " часов " +
                localTime.getMinute() + " минут.", localTime);
    }
}
