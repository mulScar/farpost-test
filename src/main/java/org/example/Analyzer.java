package org.example;

import java.io.InputStream;
import java.util.Scanner;

public class Analyzer {

    private final double availabilityLevel;
    private final double processingTime;
    private final int requestCount;
    private final Scanner scanner;

    public Analyzer(double availabilityLevel, double processingTime, int requestCount, InputStream inputStream) {
        this.availabilityLevel = availabilityLevel;
        this.processingTime = processingTime;
        this.requestCount = requestCount;
        scanner = new Scanner(inputStream);
    }

    /**
     * Анализирует весь поток ввода и выводит интервалы
     */
    public void analyze() {
        while (true) {
            String nextInterval = findNextInterval();
            if (nextInterval == null) {
                break;
            }
            System.out.println(nextInterval);
        }
        scanner.close();
    }

    /**
     * Поиск следующего интервала
     * @return Интервал с уровнем доступности, null если ничего считать не получилось
     */
    public String findNextInterval() {
        String startTimeStamp = null;
        String finishTimeStamp = null;
        int countFail = 0;
        int count = 0;
        while (scanner.hasNext()) {
            RequestInfo info = readNextLine();
            if (info == null) {
                continue;
            }

            if (count == 0) {
                startTimeStamp = info.getTimeStamp();
            }
            if (info.isFail(processingTime)) {
                countFail++;
            }

            count++;
            finishTimeStamp = info.getTimeStamp();
            if (count == requestCount) {
                if (countFail == count) {
                    finishTimeStamp = findFailChain(finishTimeStamp);
                    return startTimeStamp + '\t' + finishTimeStamp + '\t' + 0.0;
                } else {
                    String interval = checkInterval(countFail, count, startTimeStamp, finishTimeStamp);
                    if (interval != null) {
                        return interval;
                    } else {
                        count = 0;
                        countFail = 0;
                    }
                }
            }
        }

        if (finishTimeStamp != null) {
            return checkInterval(countFail, count, startTimeStamp, finishTimeStamp);
        }
        return null;
    }

    private String checkInterval(int countFail, int count, String startTimeStamp, String finishTimeStamp) {
        double currentAvailabilityLevel = 100 - (double) countFail * 100 / count;
        if (currentAvailabilityLevel < this.availabilityLevel) {
            return startTimeStamp + '\t' + finishTimeStamp + '\t' + currentAvailabilityLevel;
        }
        return null;
    }

    private String findFailChain(String finishFailChain) {
        while (scanner.hasNext()) {
            RequestInfo info = readNextLine();
            if (info == null) {
                continue;
            }

            if (!info.isFail(processingTime)) {
                return finishFailChain;
            }
            finishFailChain = info.getTimeStamp();
        }
        return finishFailChain;
    }

    private RequestInfo readNextLine() {
        String str = scanner.nextLine();
        RequestInfo info = RequestUtils.parseInfo(str);
        if (info == null) {
            System.out.println("Invalid data str " + str);
            return null;
        }
        return info;
    }
}
