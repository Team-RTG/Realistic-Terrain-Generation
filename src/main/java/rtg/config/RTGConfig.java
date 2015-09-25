package rtg.config;

import java.io.File;

public class RTGConfig
{
	public static File rtgConfigFile;
	public static File bopConfigFile;
	public static File blaConfigFile;
	public static File bleConfigFile;

	public static void init(String configpath)
	{
		rtgConfigFile = new File(configpath + "ConfigRTG.cfg");
		bopConfigFile = new File(configpath + "ConfigBOP.cfg");
		blaConfigFile = new File(configpath + "BiomeGenRTG.cfg");
		bleConfigFile = new File(configpath + "BiomeWeightsRTG.cfg");
		

		ConfigRTG.init(rtgConfigFile);
		ConfigBOP.init(bopConfigFile);
		BiomeGenRTG.init(blaConfigFile);
		BiomeWeightsRTG.init(bleConfigFile);

	}
}
