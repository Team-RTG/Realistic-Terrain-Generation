package rtg.config;

import java.io.File;

public class RTGConfig
{
	public static File rtgConfigFile;
	public static File bopConfigFile;
	public static File ebxlConfigFile;
	public static File ebConfigFile;
	public static File hlConfigFile;
	public static File tcConfigFile;
	public static File bcConfigFile;

	public static void init(String configpath)
	{
		rtgConfigFile = new File(configpath + "ConfigRTG.cfg");
		bopConfigFile = new File(configpath + "ConfigBOP.cfg");
		ebxlConfigFile = new File(configpath + "ConfigEBXL.cfg");
		ebConfigFile = new File(configpath + "ConfigEB.cfg");
		hlConfigFile = new File(configpath + "ConfigHL.cfg");
		tcConfigFile = new File(configpath + "ConfigTC.cfg");
		bcConfigFile = new File(configpath + "ConfigBC.cfg");

		ConfigRTG.init(rtgConfigFile);
		ConfigBOP.init(bopConfigFile);
		ConfigEBXL.init(ebxlConfigFile);
		ConfigEB.init(ebConfigFile);
		ConfigHL.init(hlConfigFile);
		ConfigTC.init(tcConfigFile);
		ConfigBC.init(bcConfigFile);
	}
}
