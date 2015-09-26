package rtg.config;

import java.io.File;

public class RTGConfig
{
	public static File rtgConfigFile;
	public static File bopConfigFile;

	public static void init(String configpath)
	{
		rtgConfigFile = new File(configpath + "ConfigRTG.cfg");
		bopConfigFile = new File(configpath + "ConfigBOP.cfg");

		ConfigRTG.init(rtgConfigFile);
		ConfigBOP.init(bopConfigFile);

	}
}
