package rtg.api;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import rtg.api.config.RTGConfig;
import rtg.api.util.Logger;
import rtg.api.util.UtilityClass;
import rtg.api.util.WorldUtil;
import rtg.api.util.storage.BiomeMap;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.WorldTypeRTG;


@UtilityClass
public final class RTGAPI {

    public static final String RTG_API_ID = "rtgapi";
    public static final String VERSION = "@API_VERSION@";
    public static final String RTG_MOD_ID = "rtg";
    public static final String RTG_WORLDTYPE_ID = "RTG";
    public static final BiomeMap RTG_BIOMES = new BiomeMap();
    private static final Set<DimensionType> ALLOWED_DIMENSION_TYPES = new ObjectArraySet<>();
    private static Path configPath;
    private static RTGConfig rtgConfig;
    private static IRealisticBiome patchBiome;

    private RTGAPI() {

    }

    public static void setConfig(RTGConfig config) {
        if (rtgConfig == null) {
            rtgConfig = config;
        }
    }

    public static Path getConfigPath() {
        return configPath;
    }

    public static void setConfigPath(Path path) {
        if (configPath == null) {
            configPath = path;
        }
    }

    public static RTGConfig config() {
        return rtgConfig;
    }

    public static boolean checkWorldType(WorldType worldType) {
        return WorldTypeRTG.getInstance().equals(worldType);
    }

    public static void addAllowedDimensionType(DimensionType dimType) {
        ALLOWED_DIMENSION_TYPES.add(dimType);
    }

    public static void removeAllowedDimensionType(DimensionType dimType) {
        ALLOWED_DIMENSION_TYPES.remove(dimType);
    }

    public static boolean isAllowedDimensionType(DimensionType dimType) {
        return ALLOWED_DIMENSION_TYPES.contains(dimType);
    }

    public static boolean isAllowedDimensionType(int dimId) {
        DimensionType type = (DimensionManager.isDimensionRegistered(dimId)) ? DimensionManager.getProviderType(dimId) : null;
        return type != null && ALLOWED_DIMENSION_TYPES.contains(type);
    }

    public static IRealisticBiome getRTGBiome(@Nonnull Biome biome) {
        IRealisticBiome rtgBiome = RTG_BIOMES.get(biome);
        if (rtgBiome != null) {
            return rtgBiome;
        }
        Logger.error("A realistic version of {} not found, using patch biome: {}", biome.getRegistryName(), patchBiome.baseBiomeResLoc());
        return patchBiome;
    }

    public static IRealisticBiome getRTGBiome(int biomeId) {
        IRealisticBiome rtgBiome = RTG_BIOMES.getValueAt(biomeId);
        if (rtgBiome != null) {
            return rtgBiome;
        }
        Logger.error("A realistic version of biome with ID {} not found, using patch biome: {}", biomeId, patchBiome.baseBiomeResLoc());
        return patchBiome;
    }

    public static void initPatchBiome() {

        final String cfgBiome = rtgConfig.PATCH_BIOME.get().trim();
        Biome biome;
        if ((biome = WorldUtil.Biomes.getBiomeFromCfgString(cfgBiome)) == null) {
            Logger.error("Erroneous patch biome set in config: {} (non-existant). Using default.", cfgBiome);
            biome = Biomes.PLAINS;
        }

        IRealisticBiome rtgBiome = RTG_BIOMES.get(biome);
        if (rtgBiome == null) {
            Logger.error("Erroneous patch biome set in config: {} (no RTG version), Using default.", biome.getRegistryName());
            rtgBiome = Objects.requireNonNull(RTG_BIOMES.get(Biomes.PLAINS), "Cannot find an RTG version of minecraft:plains. This should be impossible.");
        }
        Logger.debug("Setting patch biome to: {}", rtgBiome.baseBiomeResLoc());
        patchBiome = rtgBiome;
    }
}
