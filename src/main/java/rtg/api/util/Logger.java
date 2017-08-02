package rtg.api.util;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import rtg.api.RTGAPI;


// TODO: [Clean-up] Class should be final to prevent extension, Class should have a private no-params contructor to prevent instantiation.
// TODO: [Clean-up] Create a new Logger instance for RTG instead of parasitically logging to the FML log
public class Logger {

//  private Logger() {}

//  private static final org.apache.logging.log4j.Logger LOGGER;
//  static {
//      LOGGER = org.apache.logging.log4j.LogManager.getLogger(rtg.reference.ModInfo.MOD_ID.toUpperCase());
//  }

    public static void debug(String format, Object... data) {

        if (RTGAPI.config().ENABLE_DEBUGGING.get()) {
//          LOGGER.log(Level.WARN, "[RTG-DEBUG] " + format, data);
            FMLLog.log(Level.WARN, "[RTG-DEBUG] " + format, data);
        }
    }

    public static void info(String format, Object... data) {

//      LOGGER.log(Level.INFO, "[RTG-INFO] " + format, data);
        FMLLog.log(Level.INFO, "[RTG-INFO] " + format, data);
    }

    public static void warn(String format, Object... data) {

//      LOGGER.log(Level.WARN, "[RTG-WARN] " + format, data);
        FMLLog.log(Level.WARN, "[RTG-WARN] " + format, data);
    }

    public static void error(String format, Object... data) {

//      LOGGER.log(Level.ERROR, "[RTG-ERROR] " + format, data);
        FMLLog.log(Level.ERROR, "[RTG-ERROR] " + format, data);
    }

    public static void fatal(String format, Object... data) {

//      LOGGER.log(Level.FATAL, "[RTG-FATAL] " + format, data);
        FMLLog.log(Level.FATAL, "[RTG-FATAL] " + format, data);
    }
}