package rtg.api.util;


import rtg.RTGConfig;
import rtg.api.RTGAPI;

@UtilityClass
public final class Logger {

    private static final org.apache.logging.log4j.Logger LOGGER;

    static {
        LOGGER = org.apache.logging.log4j.LogManager.getLogger(RTGAPI.RTG_MOD_ID.toUpperCase());
    }

    private Logger() {

    }

    /**
     *  This should be used during high intensity situations, such as debugging during chunk
     *  generation when there could be thousands of debugging message per sec.
     */
    public static void rtgDebug(String format, Object... data) {

        if (RTGConfig.enableDebugging()) {
            LOGGER.log(org.apache.logging.log4j.Level.DEBUG, format, data);
        }
    }

    public static void debug(String format, Object... data) {

        LOGGER.log(org.apache.logging.log4j.Level.DEBUG, format, data);
    }

    public static void trace(String format, Object... data) {

        LOGGER.log(org.apache.logging.log4j.Level.TRACE, format, data);
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
