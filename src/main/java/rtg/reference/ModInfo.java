package rtg.reference;

import net.minecraftforge.common.ForgeVersion;

public class ModInfo {
    public static final String MOD_ID = "@MOD_ID@";
    public static final String MOD_NAME = "@MOD_NAME@";
    public static final String MOD_VERSION = "@MOD_VERSION@";
    public static final String FORGE_DEP = "" + ForgeVersion.majorVersion + '.'
        + ForgeVersion.minorVersion + '.'
        + ForgeVersion.revisionVersion + '.'
        + ForgeVersion.buildVersion;
    public static final String MOD_DEPS = "";
    public static final String PROXY_COMMON = "rtg.proxy.CommonProxy";
    public static final String PROXY_CLIENT = "rtg.proxy.ClientProxy";
}
