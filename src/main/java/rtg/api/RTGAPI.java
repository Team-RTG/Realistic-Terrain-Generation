package rtg.api;

import java.nio.file.Path;
import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;

import net.minecraftforge.common.DimensionManager;

import rtg.api.config.RTGConfig;
import rtg.api.util.UtilityClass;
import rtg.api.util.storage.BiomeMap;

@UtilityClass
public final class RTGAPI
{
    private RTGAPI() {}

    public static final String RTG_API_ID       = "rtgapi";
    public static final String VERSION          = "@API_VERSION@";
    public static final String RTG_MOD_ID       = "rtg";
    public static final String RTG_WORLDTYPE_ID = "RTG";

    private static final Set<DimensionType> ALLOWED_DIMENSION_TYPES = new ObjectArraySet<>();
    public  static final BiomeMap RTG_BIOMES                        = new BiomeMap();

    private static Path      configPath;
    private static RTGConfig rtgConfig;
    private static WorldType rtgWorldType;

    public static void setConfigPath(Path path)     { if (configPath   == null) configPath   = path; }
    public static void setConfig(RTGConfig config)  { if (rtgConfig    == null) rtgConfig    = config; }
    public static void setWorldType(WorldType type) { if (rtgWorldType == null) rtgWorldType = type; }

    public static Path      getConfigPath() { return configPath; }
    public static RTGConfig config()        { return rtgConfig; }
    public static WorldType getWorldType()  { return rtgWorldType; }

    public static boolean checkWorldType(WorldType worldType) { return rtgWorldType.getClass().isInstance(worldType); }

    public static void    addAllowedDimensionType(DimensionType dimType)    { ALLOWED_DIMENSION_TYPES.add(dimType); }
    public static void    removeAllowedDimensionType(DimensionType dimType) { ALLOWED_DIMENSION_TYPES.remove(dimType); }
    public static boolean isAllowedDimensionType(DimensionType dimType)     { return ALLOWED_DIMENSION_TYPES.contains(dimType); }
    public static boolean isAllowedDimensionType(int dimId) {
        DimensionType type = (DimensionManager.isDimensionRegistered(dimId)) ? DimensionManager.getProviderType(dimId) : null;
        return type != null && ALLOWED_DIMENSION_TYPES.contains(type);
    }
}
