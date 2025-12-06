package ru.yandex.practicum.sleeptracker;

import sleeptrackerfunction.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SleepTrackerApp {

    static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Пожалуйста, укажите путь к файлу с логом сна.");
            return;
        }

        String filePath = args[0];

        List<SleepingSession> sleepingSessions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            sleepingSessions = br.lines()
                    .map(line -> {
                        String[] parts = line.split(";");
                        LocalDateTime fallingAsleep = LocalDateTime.parse(parts[0], DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
                        LocalDateTime awakening = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
                        SleepQuality sleepQuality = SleepQuality.valueOf(parts[2]);
                        return new SleepingSession(fallingAsleep, awakening, sleepQuality);
                    })
                    .toList();
        } catch (IOException e) {
            System.out.println("Ошибка в чтении файла: " + e.getMessage());
        }

        NumberOfSleepSession numberOfSleepSession = new NumberOfSleepSession();
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        MaxSleepSession maxSleepSession = new MaxSleepSession();
        MinSleepSession minSleepSession = new MinSleepSession();
        BadSleepSession badSleepSession = new BadSleepSession();
        SleeplessNights sleeplessNights = new SleeplessNights();
        DefinitionOfChronotype definitionOfChronotype = new DefinitionOfChronotype();

        List<SleepAnalysisResult> sleepAnalysisResults = new ArrayList<>();
        sleepAnalysisResults.add(numberOfSleepSession.apply(sleepingSessions));
        sleepAnalysisResults.add(averageSessionDuration.apply(sleepingSessions));
        sleepAnalysisResults.add(maxSleepSession.apply(sleepingSessions));
        sleepAnalysisResults.add(minSleepSession.apply(sleepingSessions));
        sleepAnalysisResults.add(badSleepSession.apply(sleepingSessions));
        sleepAnalysisResults.add(sleeplessNights.apply(sleepingSessions));
        sleepAnalysisResults.add(definitionOfChronotype.apply(sleepingSessions));

        String resultString = sleepAnalysisResults.stream()
                .map(SleepAnalysisResult::getDescription)
                .collect(Collectors.joining("\n"));

        System.out.println(resultString);
    }
}