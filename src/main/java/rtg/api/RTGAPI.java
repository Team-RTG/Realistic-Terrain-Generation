package rtg.api;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

import rtg.api.util.BlockUtil;
import rtg.api.util.Logger;
import rtg.api.util.UtilityClass;
import rtg.api.util.storage.BiomeMap;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.WorldTypeRTG;


@UtilityClass
public final class RTGAPI {

    public static final String   RTG_API_ID       = "rtgapi";
    public static final String   VERSION          = "@API_VERSION@";
    public static final String   RTG_MOD_ID       = "rtg";
    public static final String   RTG_WORLDTYPE_ID = "RTG";
    public static final BiomeMap RTG_BIOMES       = new BiomeMap();

    private static final Set<DimensionType> ALLOWED_DIMENSION_TYPES = new ObjectArraySet<>();

    private static Path            configPath;
    private static IRealisticBiome patchBiome;
    private static IBlockState     shadowStoneBlock  = null;
    private static IBlockState     shadowDesertBlock = null;

    private RTGAPI() {

    }

    public static Path getConfigPath() {
        return configPath;
    }

    public static void setConfigPath(Path path) {
        if (configPath == null) {
            configPath = path;
        }
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
        return patchBiome;
    }

    public static IRealisticBiome getRTGBiome(int biomeId) {
        IRealisticBiome rtgBiome = RTG_BIOMES.getValueAt(biomeId);
        if (rtgBiome != null) {
            return rtgBiome;
        }
        return patchBiome;
    }

    public static void initPatchBiome(Biome biome) {
        IRealisticBiome rtgBiome = RTG_BIOMES.get(biome);
        if (rtgBiome == null) {
            Logger.error("Erroneous patch biome set in config: {} (no RTG version), Using default.", biome.getRegistryName());
            rtgBiome = Objects.requireNonNull(RTG_BIOMES.get(Biomes.PLAINS), "Cannot find an RTG version of minecraft:plains. This should be impossible.");
        }
        Logger.debug("Setting patch biome to: {}", rtgBiome.baseBiomeResLoc());
        patchBiome = rtgBiome;
    }

    public static void setShadowBlocks(IBlockState stone, IBlockState desert) {
        if (shadowStoneBlock  == null) { shadowStoneBlock  = stone  != null ? stone  : BlockUtil.getStateClay(EnumDyeColor.CYAN); }
        if (shadowDesertBlock == null) { shadowDesertBlock = desert != null ? desert : BlockUtil.getStateClay(EnumDyeColor.GRAY); }
    }

    public static IBlockState getShadowStoneBlock() {
        return shadowStoneBlock;
    }

    public static IBlockState getShadowDesertBlock() {
        return shadowDesertBlock;
    }
}
