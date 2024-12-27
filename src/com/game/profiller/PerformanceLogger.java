package com.game.profiller;

import java.util.logging.*;
import java.util.function.Supplier;

public class PerformanceLogger {

    private Logger logger;
    private Runtime runtime = null;
    private long memoryBefore = 0;
    private long startTime = 0;
    private long endTime= 0;
    private long memoryAfter = 0;

    public PerformanceLogger(String name) {
        try {
            FileHandler fileHandler = new FileHandler(name, false);
            fileHandler.setFormatter(new SimpleFormatter());
            logger = Logger.getLogger(name);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initTime() {
        System.gc();
        runtime = Runtime.getRuntime();

        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        startTime = System.nanoTime();
    }

    public void endTime() {
        endTime = System.nanoTime();
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
    }

    public void logPerformance(String methodName) {
        long memoryUsed = memoryAfter - memoryBefore;
        long duration = endTime - startTime;

        double durationInSeconds = duration / 1_000_000_000.0;
        double memoryInKilobytes = memoryUsed / (1024*1024.0);


        logger.info(String.format("Method: %s | Time: %.6f seconds | Memory: %.2f MB", methodName, durationInSeconds, memoryInKilobytes));
        System.out.printf("Method: %s | Time: %.6f seconds | Memory: %.2f MB%n", methodName, durationInSeconds, memoryInKilobytes);

        Runtime runtime = null;
        long memoryBefore = 0;
        long startTime = 0;
        long endTime= 0;
        long memoryAfter = 0;
    }
}
