package rtg.util;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import rtg.api.RTGAPI;


public class Logger {

    public static void debug(String format, Object... data) {

        if (RTGAPI.config().ENABLE_DEBUGGING.get()) {
            FMLLog.log(Level.WARN, "[RTG-DEBUG] " + format, data);
        }
    }

    public static void info(String format, Object... data) {

        FMLLog.log(Level.INFO, "[RTG-INFO] " + format, data);
    }

    public static void warn(String format, Object... data) {

        FMLLog.log(Level.WARN, "[RTG-WARN] " + format, data);
    }

    public static void error(String format, Object... data) {

        FMLLog.log(Level.ERROR, "[RTG-ERROR] " + format, data);
    }

    public static void fatal(String format, Object... data) {

        FMLLog.log(Level.FATAL, "[RTG-FATAL] " + format, data);
    }
}