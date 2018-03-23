package rtg.api.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import rtg.api.config.BiomeConfig;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * A Utility for storing and retrieving plateau band configurations for specific biomes.
 *
 * @author WhichOnesPink 2017-01-07
 * @author srs-bsns 2018-03-21
 *
 * @since 1.0.0
 */
@UtilityClass
public final class PlateauUtil
{
    private PlateauUtil() {}

    private static final HashMap<IRealisticBiome, List<IBlockState>> BIOME_PLATEAU_BANDS;
    private static final IBlockState        DEFAULT_PLATEAU_BLOCK;
    private static final Collection<String> MESA_PLATEAU_BLOCKS;
    private static final Collection<String> SAVANNA_PLATEAU_BLOCKS;

    public static String[] getMesaPlateauBlocks() {
        return MESA_PLATEAU_BLOCKS.toArray(new String[MESA_PLATEAU_BLOCKS.size()]);
    }

    public static String[] getSavannaPlateauBlocks() {
        return SAVANNA_PLATEAU_BLOCKS.toArray(new String[SAVANNA_PLATEAU_BLOCKS.size()]);
    }

    static {
        BIOME_PLATEAU_BANDS   = Maps.newHashMap();
        DEFAULT_PLATEAU_BLOCK = Blocks.HARDENED_CLAY.getDefaultState();
        MESA_PLATEAU_BLOCKS   = Collections.unmodifiableCollection(Arrays.asList(
            "minecraft:stained_hardened_clay[color=yellow]",
            "minecraft:stained_hardened_clay[color=yellow]",
            "minecraft:stained_hardened_clay[color=yellow]",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=orange]",
            "minecraft:stained_hardened_clay[color=red]",
            "minecraft:stained_hardened_clay[color=red]",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=orange]",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=orange]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=orange]",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=orange]",
            "minecraft:hardened_clay",
            "minecraft:stained_hardened_clay[color=orange]",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay",
            "minecraft:hardened_clay"
        ));
        SAVANNA_PLATEAU_BLOCKS = Collections.unmodifiableCollection(Arrays.asList(
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=white]",
            "minecraft:stained_hardened_clay[color=silver]",
            "minecraft:stained_hardened_clay[color=brown]",
            "minecraft:stained_hardened_clay[color=brown]"
        ));
    }

    public static void init() {

        Biome.REGISTRY.forEach(biome -> {

            IRealisticBiome rBiome = RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome));
            if (rBiome != null) {

                BiomeConfig biomeConfig = rBiome.getConfig();
                if (biomeConfig.hasProperty(biomeConfig.ALLOW_PLATEAU_MODIFICATIONS)) {

                    String[] blocks;
                    if (biomeConfig.hasProperty(biomeConfig.PLATEAU_GRADIENT_BLOCK_LIST) && biomeConfig.PLATEAU_GRADIENT_BLOCK_LIST.getValues().length > 0) {
                        blocks = biomeConfig.PLATEAU_GRADIENT_BLOCK_LIST.getValues();
                    }
                    else blocks = BiomeDictionary.hasType(biome, Type.MESA) ? getMesaPlateauBlocks() : getSavannaPlateauBlocks();
                    List<IBlockState> bands = Arrays.stream(blocks).map(BlockUtil::getBlockStateFromCfgString).filter(Objects::nonNull).collect(Collectors.toList());
                    if (bands.isEmpty()) { bands.add(DEFAULT_PLATEAU_BLOCK); }
                    BIOME_PLATEAU_BANDS.put(rBiome, bands);
                }
            }
            else Logger.debug("[PlateauUtil#init] Biome: {}, with ID: {}, has no realistic version... skipping", biome.getRegistryName(), Biome.getIdForBiome(biome));
        });
    }

    public static IBlockState getPlateauBand(final IRTGWorld rtgWorld, final IRealisticBiome rBiome, final int x, final int y, final int z) {

        return (rBiome.getConfig().ALLOW_PLATEAU_MODIFICATIONS.get() && BIOME_PLATEAU_BANDS.containsKey(rBiome))
            ? BIOME_PLATEAU_BANDS.get(rBiome).get(y)
            : rtgWorld.mesaBiome().getBand(x, y, z);
    }

    public static float stepIncrease(final float simplexVal, final float start, final float finish, final float height) {
        return (simplexVal <= start) ? 0 : (simplexVal >= finish) ? height : ((simplexVal-start) / (finish-start)) * height;
    }
}
