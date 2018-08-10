package rtg.api.util;


import rtg.api.RTGAPI;


public class Logger {

    private static final org.apache.logging.log4j.Logger LOGGER;

    static {
        LOGGER = org.apache.logging.log4j.LogManager.getLogger(rtg.RTG.MOD_ID.toUpperCase());
    }

    private Logger() {

    }

    public static void debug(String format, Object... data) {

        if (RTGAPI.config().ENABLE_DEBUGGING.get()) {
            LOGGER.log(org.apache.logging.log4j.Level.WARN, format, data);
        }
    }

    public static void info(String format, Object... data) {

        LOGGER.log(org.apache.logging.log4j.Level.INFO, format, data);
    }

    public static void warn(String format, Object... data) {

        LOGGER.log(org.apache.logging.log4j.Level.WARN, format, data);
    }

    public static void error(String format, Object... data) {

        LOGGER.log(org.apache.logging.log4j.Level.ERROR, format, data);
    }

    public static void fatal(String format, Object... data) {

        LOGGER.log(org.apache.logging.log4j.Level.FATAL, format, data);
    }
}