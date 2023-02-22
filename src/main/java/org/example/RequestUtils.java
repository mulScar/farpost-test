package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUtils {

    private static final Pattern PATTERN = Pattern.compile("(\\d+:\\d+:\\d+) .+ (\\d{3}) .+ (\\d+.\\d+)");

    /**
     * @param str Строка записи о запросе из лога
     * @return Объект RequestInfo с данными из строки, null если в строке не были найдены необходимые данные
     */
    public static RequestInfo parseInfo(String str) {
        Matcher matcher = PATTERN.matcher(str);
        if (matcher.find()) {
            String time = matcher.group(1);
            Integer code = Integer.valueOf(matcher.group(2));
            Double processTime = Double.valueOf(matcher.group(3));
            return new RequestInfo(time, code, processTime);
        }
        return null;
    }
}
