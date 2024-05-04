package data;

import java.io.*;
import java.nio.file.*;

public class Stream {
    private static final String PUZZLES_DIRECTORY = "resources";
    private static final String SOLUTIONS_DIRECTORY = "solutions";
    private static final String CLASS_NAME_REGEX = "Day(\\d+)";

    public static int getDayNumber() {
        String className = getCallingClassName();
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(CLASS_NAME_REGEX);
        java.util.regex.Matcher matcher = pattern.matcher(className);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            throw new IllegalStateException("Unable to parse day number from class name: " + className);
        }
    }

    private static String getCallingClassName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            if (!element.getClassName().equals(Stream.class.getName())) {
                return element.getClassName();
            }
        }
        throw new IllegalStateException("Unable to determine calling class.");
    }

    public static InputStream readPuzzleData() throws IOException {
        int day = getDayNumber();
        String puzzleFilePath = PUZZLES_DIRECTORY + "/day" + day + ".txt";
        return Files.newInputStream(Paths.get(puzzleFilePath));
    }

    public static void writeSolution(String solution) throws IOException {
        int day = getDayNumber();
        String solutionFilePath = SOLUTIONS_DIRECTORY + "/day" + day + "_solution.txt";
        Files.write(Paths.get(solutionFilePath), solution.getBytes());
    }
}
