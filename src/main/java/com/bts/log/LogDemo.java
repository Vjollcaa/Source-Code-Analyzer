package com.bts.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to setup logs
 * Different methods print different messages
 */

public class LogDemo {
    private static final Logger Log = LoggerFactory.getLogger(LogDemo.class.getName());

    public static void info(String message) {
        Log.info("--------------------------          " + message);
    }

    public static void info(int message) {
        Log.info("--------------------------          " + message);
    }

    public static void warn(String message) {
        Log.warn("**************************          " + message.toUpperCase());
    }

    public static void error(String message) {
        Log.error("//////////////////////////          " + message + "          /////////////////////////////////");
    }

    public static void debug(String message) {
        Log.debug("          " + message);
    }
}
