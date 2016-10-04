package rtg.reference;


public class ModInfo
{
    public static final String MOD_ID           = "@MOD_ID@";
    public static final String MOD_NAME         = "@MOD_NAME@";
    public static final String MOD_VERSION      = "@MOD_VERSION@";
    public static final String MCF_MINVER       = "0.0-MCF+MINVER";
    public static final String MCF_MAXVER       = "9001.0-MCF+MAXVER";
    public static final String MOD_DEPS         = ";after:MODDEPS";
    public static final String WORLD_TYPE       = MOD_ID.toUpperCase(); // Let's keep this uppercase. - Pink
    public static final String CONFIG_DIRECTORY = MOD_ID.toUpperCase(); // Let's keep this uppercase too. - Pink
}
