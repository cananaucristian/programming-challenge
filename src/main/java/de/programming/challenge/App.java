package de.programming.challenge;

import java.io.IOException;

/**
 * The entry class that the app runs and outputs data to the console.
 *
 * @author cananaucristian
 */
public final class App {

    /**
     * This is the main entry method of program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws IOException {
        FileController fileController = new FileController();

        String dayWithSmallestTempSpread = fileController.
                getSmallestColumnValueFromFileData(
                        "src/main/resources/de/programming/challenge/weather.csv",
                        "MxT",
                        "MnT",
                        "Day",
                        false
                );

        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = fileController.
                getSmallestColumnValueFromFileData(
                        "src/main/resources/de/programming/challenge/football.csv",
                "Goals",
                        "Goals Allowed",
                        "Team",
                        true
                );

        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
