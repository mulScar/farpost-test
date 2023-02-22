package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestUtilsTest {

    @Test
    public void testCommon() {
        double responseTime = 50;
        String testRequest = "192.168.32.181 - - [14/06/2017:16:47:02 +1000]" +
                " \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\"" +
                " 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0";

        RequestInfo requestInfo = RequestUtils.parseInfo(testRequest);
        assertNotNull(requestInfo);
        assertEquals("16:47:02", requestInfo.getTimeStamp());
        assertFalse(requestInfo.isErrorCode());
        assertFalse(requestInfo.isTimeOut(responseTime));
    }

    @Test
    public void testBadRequest() {
        double responseTime = 10.0;
        String testRequest = "192.168.32.181 - - [14/06/2017:17:50:02 +1000]" +
                " \"PUT /rest/v1.4/documents?zone=default&_rid=e356713 HTTP/1.1\"" +
                " 501 2 30.164372 \"-\" \"@list-item-updater\" prio:0";

        RequestInfo requestInfo = RequestUtils.parseInfo(testRequest);
        assertNotNull(requestInfo);
        assertEquals("17:50:02", requestInfo.getTimeStamp());
        assertTrue(requestInfo.isErrorCode());
        assertTrue(requestInfo.isTimeOut(responseTime));
    }
}
