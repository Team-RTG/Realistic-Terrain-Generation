package rtg.config;

import java.io.File;

public class RTGConfig
{
	public static File rtgConfigFile;
	public static File bopConfigFile;
	public static File ebConfigFile;
	public static File tcConfigFile;

	public static void init(String configpath)
	{
		rtgConfigFile = new File(configpath + "ConfigRTG.cfg");
		bopConfigFile = new File(configpath + "ConfigBOP.cfg");
		ebConfigFile = new File(configpath + "ConfigEB.cfg");
		tcConfigFile = new File(configpath + "ConfigTC.cfg");

		ConfigRTG.init(rtgConfigFile);
		ConfigBOP.init(bopConfigFile);
		ConfigEB.init(ebConfigFile);
		ConfigTC.init(tcConfigFile);
	}
}
