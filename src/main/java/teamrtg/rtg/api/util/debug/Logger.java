package teamrtg.rtg.api.util.debug;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import teamrtg.rtg.api.mods.Mods;

public class Logger {

    public static void debug(String format, Object... data) {
        if (Mods.RTG.config.DEBUG_LOGGING.get()) FMLLog.log(Level.INFO, "[RTG-DEBUG] " + format, data);
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
        FMLLog.log(Level.FATAL, "[RTG-FATAL] " + message, data);
        Minecraft.getMinecraft().crashed(new CrashReport(message, throwable));
    }
}