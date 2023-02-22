package org.example;

/**
 * Информация о запросе
 */
public class RequestInfo {

    private final String timeStamp;
    private final int responseCode;
    private final double processingTime;

    public RequestInfo(String timeStamp, Integer responseCode, Double processingTime) {
        this.timeStamp = timeStamp;
        this.responseCode = responseCode;
        this.processingTime = processingTime;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean isErrorCode() {
        return responseCode >= 500 && responseCode <= 600;
    }

    public boolean isTimeOut(double time) {
        return processingTime >= time;
    }

    public boolean isFail(double time) {
        return isErrorCode() || isTimeOut(time);
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "timeStamp='" + timeStamp + '\'' +
                ", responseCode=" + responseCode +
                ", processingTime=" + processingTime +
                '}';
    }
}
