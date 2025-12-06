package sleeptrackerfunction;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class MinSleepSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int min = sleepingSessions.stream()
                .mapToInt(session -> (int) Duration.between(session.getFallingAsleep(), session.getAwakening()).toMinutes())
                .min()
                .orElse(0);
        int hours = min / 60;
        int minutes = min % 60;
        LocalTime localTime = LocalTime.of(hours, minutes);
        return new SleepAnalysisResult("Минимальное время сна составило " + localTime.getHour() + " часов " + localTime.getMinute() + " минут.", localTime);
    }
}