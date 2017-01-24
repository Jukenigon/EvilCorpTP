package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Utils {
    private static final String FILE_NAME = "log.csv";

    public void writeLog(String s) {
        try {
            Files.write(Paths.get(FILE_NAME), s.getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(FILE_NAME), "\n".getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("An error occured while writing log.");
        }
    }
}
