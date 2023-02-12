package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseInfo {

    private static final Pattern PATTERN = Pattern.compile(" \\[(\\d+\\/\\d+\\/\\d+:\\d+:\\d+:\\d+) .+\" (\\d{3}) \\d (\\d+.\\d+) \"");

    private String time;
    private int code;
    private double processTime;

    private ResponseInfo(String time, Integer code, Double processTime) {
        this.time = time;
        this.code = code;
        this.processTime = processTime;
    }

    public static ResponseInfo parse(String str) {
        Matcher matcher = PATTERN.matcher(str);
        if (matcher.find()) {
            String time = matcher.group(1);
            Integer code = Integer.valueOf(matcher.group(2));
            Double processTime = Double.valueOf(matcher.group(3));
            return new ResponseInfo(time, code, processTime);
        }
       return null;
    }

    public String getTime() {
        return time;
    }

    public int getCode() {
        return code;
    }

    public double getProcessTime() {
        return processTime;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "time='" + time + '\'' +
                ", code=" + code +
                ", processTime=" + processTime +
                '}';
    }
}
