package sleeptrackerfunction;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleeplessNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей: " + 0, 0);
        }
        int countAllNights = Period.between(sleepingSessions.getFirst().getFallingAsleep().toLocalDate(), sleepingSessions.getLast().getAwakening().toLocalDate()).getDays();

        long countSleepNight = sleepingSessions.stream()
                .map(x -> {
                    LocalDateTime fallAsleepTime = x.getFallingAsleep();
                    LocalDateTime awakeningTime = x.getAwakening();
                    LocalDateTime normalEnd = LocalDateTime.of(awakeningTime.toLocalDate(), LocalTime.of(6, 0));
                    if (fallAsleepTime.getDayOfYear() != awakeningTime.getDayOfYear() || awakeningTime.isBefore(normalEnd)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .reduce(0, Integer::sum);

        if (sleepingSessions.getFirst().getFallingAsleep().toLocalTime().isBefore(LocalTime.of(12, 0))) {
            countAllNights++;
        }

        int sleeplessNights = countAllNights - (int) countSleepNight;
        return new SleepAnalysisResult("Количество бессонных ночей: " + sleeplessNights, sleeplessNights);
    }
}