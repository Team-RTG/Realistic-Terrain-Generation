package rtg.util;

import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.reference.ModInfo;

public class Logger {
	public static void log(Level level, String format, Object... data) {
		FMLLog.log(ModInfo.MOD_ID, level, format, data);
	}

	public static void log(Level level, Throwable throwable, String format, Object... data) {
		FMLLog.log(ModInfo.MOD_ID, level, throwable, format, data);
	}


	public static void fatal(String format, Object... data) {
		log(Level.FATAL, format, data);
	}

	public static void fatal(Throwable throwable, String format,
							 Object... data) {
		log(Level.FATAL, throwable, format, data);
	}

	public static void error(String format, Object... data) {
		log(Level.ERROR, format, data);
	}

	public static void error(Throwable throwable, String format,
							 Object... data) {
		log(Level.ERROR, throwable, format, data);
	}

	public static void warn(String format, Object... data) {
		log(Level.WARN, format, data);
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	public static void debug(String format, Object... data) {
		log(Level.DEBUG, format, data);
	}
}