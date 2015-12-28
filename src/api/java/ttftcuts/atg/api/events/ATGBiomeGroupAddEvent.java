package ttftcuts.atg.api.events;

import ttftcuts.atg.api.ATGBiomes.BiomeType;
import cpw.mods.fml.common.eventhandler.Event;

public class ATGBiomeGroupAddEvent extends Event {

	public static enum ResponseType { NONE, OK, FAILED };
	
	public BiomeType type;
	public ResponseType response;
	public String name;
	public double temp;
	public double moisture;
	public double height;
	public double minHeight;
	public double maxHeight;
	public long salt;
	public boolean generate;
	public double suitability;

	public ATGBiomeGroupAddEvent( BiomeType type, String name, double temp, double moisture, double height, double minHeight, double maxHeight, long salt, boolean generate, double suitability) {
		this.type = type;
		this.name = name;
		this.temp = temp;
		this.moisture = moisture;
		this.height = height;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.salt = salt;
		this.generate = generate;
		this.suitability = suitability;
		this.response = ResponseType.NONE;
	}
	
	@Deprecated
	public ATGBiomeGroupAddEvent( BiomeType type, String name, double temp, double moisture, double height, double minHeight, double maxHeight, long salt, boolean generate) {
		this( type, name, temp, moisture, height, minHeight, maxHeight, salt, generate, 1.0);
	}
}
