package org.example;

public class App {

    private static final String MIN_AVAILABILITY_LEVEL_KEY = "-u";
    private static final String MAX_PROCESSING_TIME_KEY = "-t";

    private static final int REQUEST_COUNT = 100;

    public static void main(String[] args) {
        Double availabilityLevel = null;
        Double processingTime = null;

        try {
            for (int i = 0; i < args.length; i++) {
                if (MIN_AVAILABILITY_LEVEL_KEY.equals(args[i])) {
                    availabilityLevel = Double.valueOf(args[++i]);
                } else if (MAX_PROCESSING_TIME_KEY.equals(args[i])) {
                    processingTime = Double.valueOf(args[++i]);
                }
            }
            if (availabilityLevel == null || processingTime == null) {
                throw new Exception("Keys are incorrect");
            }
        } catch (Exception ex) {
            if (ex instanceof NumberFormatException) {
                System.out.println("Invalid value");
            } else {
                System.out.println(ex.getMessage());
            }
            return;
        }

        Analyzer analyzer = new Analyzer(availabilityLevel, processingTime, REQUEST_COUNT, System.in);
        analyzer.analyze();
    }
}
