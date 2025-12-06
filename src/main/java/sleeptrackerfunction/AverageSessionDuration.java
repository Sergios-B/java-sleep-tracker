package sleeptrackerfunction;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int average = (int) sleepingSessions.stream()
                .mapToInt(session -> (int) Duration.between(session.getFallingAsleep(), session.getAwakening()).toMinutes())
                .average()
                .orElse(0);
        int hours = average / 60;
        int minutes = average % 60;
        LocalTime localTime = LocalTime.of(hours, minutes);
        return new SleepAnalysisResult("Средняя продолжительность сна составила " + localTime.getHour() + " часов " + localTime.getMinute() + " минут", localTime);
    }
}