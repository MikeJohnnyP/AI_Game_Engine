package chess_engine;

import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

class Statistic {
    private String method;
    private double time;
    private double memory;

    public Statistic(String method, double time, double memory) {
        this.method = method;
        this.time = time;
        this.memory = memory;
    }

    public String getMethod() {
        return method;
    }

    public double getTime() {
        return time;
    }

    public double getMemory() {
        return memory;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.#####");
        return "Statistic{" +
                "method='" + method + '\'' +
                ", time=" + df.format(time) +
                ", memory=" + df.format(memory) +
                '}';
    }
}

class PerformanceAnalyzer {
    private List<Statistic> statistics;
    private DecimalFormat df;

    public PerformanceAnalyzer() {
        this.statistics = new ArrayList<>();
        this.df = new DecimalFormat("#.######");
    }

    public void addStatistic(Statistic stat) {
        statistics.add(stat);
    }

    public String getMaxTime() {
        return df.format(statistics.stream()
                .mapToDouble(Statistic::getTime)
                .max()
                .orElse(0));
    }

    public String getMinTime() {
        return df.format(statistics.stream()
                .mapToDouble(Statistic::getTime)
                .min()
                .orElse(0));
    }

    public String getAverageTime() {
        return df.format(statistics.stream()
                .mapToDouble(Statistic::getTime)
                .average()
                .orElse(0));
    }

    public String getMaxMemory() {
        return df.format(statistics.stream()
                .mapToDouble(Statistic::getMemory)
                .max()
                .orElse(0));
    }

    public String getMinMemory() {
        return df.format(statistics.stream()
                .mapToDouble(Statistic::getMemory)
                .min()
                .orElse(0));
    }

    public String getAverageMemory() {
        return df.format(statistics.stream()
                .mapToDouble(Statistic::getMemory)
                .average()
                .orElse(0));
    }

    @Override
    public String toString() {
        return statistics.stream()
                .map(Statistic::toString)
                .collect(Collectors.joining("\n"));
    }
}

class LogParser {

    // Hàm đọc file từ local
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void main(String[] args) {
        String filePath = "D:\\AI_Game_Engine\\MinimaxBotDepth5.txt"; // Đường dẫn đến file log
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer();

        try {
            String log = readFile(filePath); // Đọc nội dung file log

            Pattern pattern = Pattern.compile("Method: (\\w+) \\| Time: ([0-9.]+) seconds \\| Memory: ([0-9.]+) MB");
            Matcher matcher = pattern.matcher(log);

            while (matcher.find()) {
                String method = matcher.group(1);
                double time = Double.parseDouble(matcher.group(2));
                double memory = Double.parseDouble(matcher.group(3));

                Statistic stat = new Statistic(method, time, memory);
                analyzer.addStatistic(stat);
            }

            System.out.println("All Statistics:");
//            System.out.println(analyzer);

            System.out.println("\nPerformance Summary:");
            System.out.println("Max Time: " + analyzer.getMaxTime() + " seconds");
            System.out.println("Min Time: " + analyzer.getMinTime() + " seconds");
            System.out.println("Average Time: " + analyzer.getAverageTime() + " seconds");
            System.out.println("Max Memory: " + analyzer.getMaxMemory() + " MB");
            System.out.println("Min Memory: " + analyzer.getMinMemory() + " MB");
            System.out.println("Average Memory: " + analyzer.getAverageMemory() + " MB");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
