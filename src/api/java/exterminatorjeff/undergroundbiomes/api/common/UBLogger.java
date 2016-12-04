package exterminatorjeff.undergroundbiomes.api.common;

import static org.apache.logging.log4j.Level.FATAL;
import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.WARN;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.TRACE;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exterminatorjeff.undergroundbiomes.api.API;
import exterminatorjeff.undergroundbiomes.api.ModInfo;

/**
 * Logger for UB, useful for debugging.<br>
 * <br>
 * For this to work properly, you must edit the Forge Log4j configuration file:
 * 
 * <pre>
 * {@code
 * <Logger level="all" name="Underground Biomes" additivity="false">
 * 	<AppenderRef ref="FmlSysOut" />
 * </Logger>
 * }
 * </pre>
 * 
 * And put it in the working directory.<br>
 * <br>
 * Also you must add this VM argument :
 * 
 * <pre>
 * -Dlog4j.configurationFile=path/to/log4j2.xml
 * </pre>
 * 
 * @author LouisDB
 *
 */
public final class UBLogger {

	private static final Logger LOGGER = LogManager.getLogger(ModInfo.NAME);

	private final String name;
	private final Level maxLevel;

	public UBLogger(Level maxLevel) {
		this((String) null, maxLevel);
	}

	public UBLogger(Class<?> clazz, Level maxLevel) {
		this(clazz.getSimpleName(), maxLevel);
	}

	public UBLogger(String name, Level maxLevel) {
		this.name = name;
		this.maxLevel = maxLevel;
	}

	private void log(Level level, String message) {
		if (level.isAtLeastAsSpecificAs(maxLevel)) {
			String prefix = ModInfo.MODID + " " + name == null ? "" : "[" + name + "] ";
			if ((level == ERROR || level == FATAL) && API.SETTINGS.crashOnProblems())
				throw new RuntimeException(prefix + message);
			else
				LOGGER.log(level, prefix + message);

		}
	}

	public void fatal(String message) {
		log(FATAL, message);
	}

	public void error(String message) {
		log(ERROR, message);
	}

	public void warn(String message) {
		log(WARN, message);
	}

	public void info(String message) {
		log(INFO, message);
	}

	public void debug(String message) {
		log(DEBUG, message);
	}

	public void trace(String message) {
		log(TRACE, message);
	}

}
