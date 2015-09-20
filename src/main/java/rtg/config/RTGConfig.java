package rtg.config;

import java.io.File;

public class RTGConfig 
{
	public static File mainConfigFile;
	public static File BOPbiomeGenConfigFile;
	public static File engineConfigFile;

	public static void init(String configpath)
	{
		mainConfigFile = new File(configpath + "ConfigRTG.cfg");
		engineConfigFile = new File(configpath + "ConfigTest.cfg");
		BOPbiomeGenConfigFile = new File(configpath + "ConfigBOP.cfg");


		ConfigTest.init(engineConfigFile);
		ConfigRTG.init(mainConfigFile);
		ConfigBOP.init(BOPbiomeGenConfigFile);

	}
}
