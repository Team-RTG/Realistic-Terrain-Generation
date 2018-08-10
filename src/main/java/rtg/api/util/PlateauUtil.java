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
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;


/**
 * A Utility for storing and retrieving plateau band configurations for specific biomes.
 *
 * @author WhichOnesPink 2017-01-07
 * @author srs-bsns 2018-03-21
 * @since 1.0.0
 */
@UtilityClass
public final class PlateauUtil {

    private static final HashMap<IRealisticBiome, List<IBlockState>> BIOME_PLATEAU_BANDS = Maps.newHashMap();
    private static final IBlockState DEFAULT_PLATEAU_BLOCK = Blocks.HARDENED_CLAY.getDefaultState();
    private static final BiomeMesa MESA = (BiomeMesa) Biomes.MESA;
    private static final Collection<String> MESA_PLATEAU_BLOCKS;
    private static final Collection<String> SAVANNA_PLATEAU_BLOCKS;

    static {
        MESA_PLATEAU_BLOCKS = Collections.unmodifiableCollection(Arrays.asList(
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

    private PlateauUtil() {
    }

    public static String[] getMesaPlateauBlocks() {
        return MESA_PLATEAU_BLOCKS.toArray(new String[0]);
    }

    public static String[] getSavannaPlateauBlocks() {
        return SAVANNA_PLATEAU_BLOCKS.toArray(new String[0]);
    }

    // TODO: [1.12] PlateauUtil should only use RTG layers. Use of vanilla layers (only an option that is unlikely to be used in most cases) should be removed.
//              The vanilla layers require a world seed in order to initialise, which can be problematic when dealing with multiple dims with different seeds.
    public static void init(long seed) {

        MESA.generateBands(seed);

        Biome.REGISTRY.forEach(biome -> {

            IRealisticBiome rBiome = RTGAPI.getRTGBiome(biome);
            if (rBiome != null) {

                BiomeConfig biomeConfig = rBiome.getConfig();
                if (biomeConfig.hasProperty(biomeConfig.ALLOW_PLATEAU_MODIFICATIONS)) {

                    String[] blocks;
                    if (biomeConfig.hasProperty(biomeConfig.PLATEAU_GRADIENT_BLOCK_LIST) && biomeConfig.PLATEAU_GRADIENT_BLOCK_LIST.getValues().length > 0) {
                        blocks = biomeConfig.PLATEAU_GRADIENT_BLOCK_LIST.getValues();
                    }
                    else {
                        blocks = BiomeDictionary.hasType(biome, Type.MESA) ? getMesaPlateauBlocks() : getSavannaPlateauBlocks();
                    }
                    List<IBlockState> bands = Arrays.stream(blocks).map(BlockUtil::getBlockStateFromCfgString).filter(Objects::nonNull).collect(Collectors.toList());
                    if (bands.isEmpty()) {
                        bands.add(DEFAULT_PLATEAU_BLOCK);
                    }
                    BIOME_PLATEAU_BANDS.put(rBiome, bands);
                }
            }
            else {
                Logger.debug("[PlateauUtil#init] Biome: {}, with ID: {}, has no realistic version... skipping", biome.getRegistryName(), Biome.getIdForBiome(biome));
            }
        });
    }

    public static IBlockState getPlateauBand(final RTGWorld rtgWorld, final IRealisticBiome rBiome, final int x, final int y, final int z) {
        return (rBiome.getConfig().ALLOW_PLATEAU_MODIFICATIONS.get() && BIOME_PLATEAU_BANDS.containsKey(rBiome)) ? getBand(rBiome, y) : MESA.getBand(x, y, z);
    }

    public static float stepIncrease(final float simplexVal, final float start, final float finish, final float height) {
        return (simplexVal <= start) ? 0 : (simplexVal >= finish) ? height : ((simplexVal - start) / (finish - start)) * height;
    }

    private static IBlockState getBand(IRealisticBiome rBiome, int index) {
        List<IBlockState> bands = BIOME_PLATEAU_BANDS.get(rBiome);
        return bands.get(index % bands.size());
    }
}
