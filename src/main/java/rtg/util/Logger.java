package rtg.util;

import net.minecraft.crash.CrashReport;

import net.minecraftforge.fml.common.FMLLog;

import org.apache.logging.log4j.Level;

import rtg.api.RTGAPI;


public class Logger {

    public static void debug(String format, Object... data) {

        if (RTGAPI.config().ENABLE_DEBUGGING.get()) {
            FMLLog.log(Level.INFO, "[RTG-DEBUG] " + format, data);
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

    public static void fatal(String message, Throwable throwable, Object... data) {

        CrashReport crashreport = CrashReport.makeCrashReport(throwable, message);
        FMLLog.bigWarning("[RTG-FATAL] " + crashreport.getCompleteReport(), data);
    }
}