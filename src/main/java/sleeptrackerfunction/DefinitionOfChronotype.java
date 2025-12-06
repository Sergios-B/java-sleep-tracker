package sleeptrackerfunction;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefinitionOfChronotype implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Скорее всего вы петух", Chronotype.PIGEON);
        }

        Map<Chronotype, Long> chronotypeCounts = sleepingSessions.stream()
                .map(this::definition)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (chronotypeCounts.containsKey(Chronotype.LARK) &&
                chronotypeCounts.get(Chronotype.LARK) > chronotypeCounts.getOrDefault(Chronotype.OWL, 0L) &&
                chronotypeCounts.get(Chronotype.LARK) > chronotypeCounts.getOrDefault(Chronotype.PIGEON, 0L)) {
            return new SleepAnalysisResult("Вы жаворонок!", Chronotype.LARK);
        } else if (chronotypeCounts.containsKey(Chronotype.OWL) &&
                chronotypeCounts.get(Chronotype.OWL) > chronotypeCounts.getOrDefault(Chronotype.LARK, 0L) &&
                chronotypeCounts.get(Chronotype.OWL) > chronotypeCounts.getOrDefault(Chronotype.PIGEON, 0L)) {
            return new SleepAnalysisResult("Вы та еще сова)", Chronotype.OWL);
        } else {
            return new SleepAnalysisResult("Странная птица, вроде голубь", Chronotype.PIGEON);
        }
    }

    public Chronotype definition(SleepingSession sleepingSession) {
        LocalTime sleepOwl = LocalTime.of(23, 0);
        LocalTime wakeUpOwl = LocalTime.of(9, 0);
        LocalTime sleepLark = LocalTime.of(22, 0);
        LocalTime wakeUpLark = LocalTime.of(7, 0);

        LocalTime fallingAsleepTime = LocalTime.from(sleepingSession.getFallingAsleep());
        LocalTime awakeningTime = LocalTime.from(sleepingSession.getAwakening());

        if ((fallingAsleepTime.isAfter(sleepOwl) || fallingAsleepTime.isBefore(wakeUpLark)) && awakeningTime.isAfter(wakeUpOwl)) {
            return Chronotype.OWL;
        } else if (fallingAsleepTime.isBefore(sleepLark) && awakeningTime.isBefore(wakeUpLark)) {
            return Chronotype.LARK;
        } else {
            return Chronotype.PIGEON;
        }
    }
}