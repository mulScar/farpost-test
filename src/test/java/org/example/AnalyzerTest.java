package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnalyzerTest {

    private static final int REQUEST_COUNT = 10;

    @Test
    public void testCommon() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/resources/access.log");
        Analyzer analyzer = new Analyzer(60.0, 45.0, REQUEST_COUNT, file);
        String interval = analyzer.findNextInterval();
        assertNotNull(interval);
        assertEquals("16:47:02\t17:56:02\t30.0", interval);
    }

    @Test
    public void testOneFailRequest() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/resources/one_fail_request.log");
        Analyzer analyzer = new Analyzer(60.0, 45.0, REQUEST_COUNT, file);
        String interval = analyzer.findNextInterval();
        assertNotNull(interval);
        assertEquals("17:50:02\t17:50:02\t0.0", interval);
    }

    @Test
    public void testNotFullInterval() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/resources/not_full_interval.log");
        Analyzer analyzer = new Analyzer(60.0, 45.0, REQUEST_COUNT, file);
        String interval = analyzer.findNextInterval();
        assertNotNull(interval);
        assertEquals("17:47:02\t17:51:02\t0.0", interval);
    }

    @Test
    public void testFailChain() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/test/resources/fail_chain.log");
        Analyzer analyzer = new Analyzer(60.0, 12.0, REQUEST_COUNT, file);
        String interval = analyzer.findNextInterval();
        assertNotNull(interval);
        assertEquals("16:47:02\t19:57:02\t0.0", interval);
    }
}
