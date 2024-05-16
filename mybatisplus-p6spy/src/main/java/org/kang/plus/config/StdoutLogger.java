package org.kang.plus.config;

/**
 * User:
 * Description:
 * Date: 2024-05-14
 * Time: 17:46
 */
public class StdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger {
    public StdoutLogger() {
    }

    public void logText(String text) {
        System.err.println(text);
    }
}
