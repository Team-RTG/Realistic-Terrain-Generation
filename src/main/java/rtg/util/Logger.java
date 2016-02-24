package rtg.util;

import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import rtg.config.rtg.ConfigRTG;

public class Logger {

	public static void debug(String format, Object... data) {
		if (ConfigRTG.enableDebugging) FMLLog.log(Level.DEBUG, "[RTG-DEBUG] " + format, data);
	}
}