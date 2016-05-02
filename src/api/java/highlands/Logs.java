package highlands;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import cpw.mods.fml.common.FMLCommonHandler;



public class Logs {
	static Logger logger = (Logger)FMLCommonHandler.instance().getFMLLogger();
	public static void log (Level level, String msg) {
		logger.log(level, msg);
	}
}
