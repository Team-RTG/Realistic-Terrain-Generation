package rtg.api.util;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.config.BiomeConfig;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * Created by WhichOnesPink on 01/07/2017.
 * Modified by srs-bsns 11/08/2017
 */
// TODO: [Clean-up] Rewrite this class (and it's corresponding config setting) to use a list of block states instead of a list of meta values for a single block
//                  This will make plateau gradients much more configurable, and will also comply with the removal of meta values in MC 1.13
public final class PlateauUtil {

    private static final HashMap<IRealisticBiome, List<IBlockState>> BIOME_PLATEAU_BANDS = new HashMap<>();

    private PlateauUtil() {}

    @SuppressWarnings("deprecation")
    public static void init() {

        Biome.REGISTRY.forEach(biome -> {
            IRealisticBiome rBiome = RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome));
            if (rBiome == null) {
                Logger.debug("[PlateauUtil#init] Biome: {}, with ID: {}, has no realistic version... skipping", biome.getRegistryName(), Biome.getIdForBiome(biome));
                return;
            }

// TODO: [Clean-up] Should we be skipping here just because the biome has no name. Is this even possible (anymore)?
            if (biome.getBiomeName().isEmpty()) {
                Logger.error("PlateauUtil#init: Biome: {}, with ID: {}, has no name... skipping", biome.getRegistryName(), Biome.getIdForBiome(biome));
                return;
            }

            BiomeConfig biomeConfig = rBiome.getConfig();
            if (biomeConfig.hasProperty(biomeConfig.ALLOW_PLATEAU_MODIFICATIONS)) {

                Block gradientBlock = BlockUtil.getBlock(biomeConfig.PLATEAU_GRADIENT_BLOCK_ID.get(), Blocks.STAINED_HARDENED_CLAY);

                List<Integer> gradientMetas = Lists.newLinkedList();
                Lists.newArrayList(biomeConfig.PLATEAU_GRADIENT_METAS.get().split(",")).forEach(sval -> {
                    Integer val = null;
                    try { val = Integer.parseInt(sval.trim()); }
                    catch(NumberFormatException ignore) {}
                    if (val!=null) gradientMetas.add(val);
                });

                // the Block to be used with a meta value < 0
                IBlockState defaultBlock = BlockUtil.getBlockStateFromString(
// TODO: [Clean-up] Update this to use a single "<ResourceLocation>/<meta value>" string when the biome configs are updated
                    biomeConfig.PLATEAU_BLOCK_ID.get()+"/"+biomeConfig.PLATEAU_BLOCK_META.get(),
                    Blocks.HARDENED_CLAY.getDefaultState()
                );

                List<IBlockState> bands = Lists.newLinkedList();
                for (int i = 0; i < 256; i++) {
                    int meta = gradientMetas.get(i % gradientMetas.size());
                    bands.add(i, (meta < 0) ? defaultBlock : gradientBlock.getStateFromMeta(meta));
                }

                BIOME_PLATEAU_BANDS.put(rBiome, bands);
            }
        });
    }

    public static IBlockState getPlateauBand(IRTGWorld rtgWorld, IRealisticBiome rBiome, int x, int y, int z) {

        return (!rBiome.getConfig().ALLOW_PLATEAU_MODIFICATIONS.get())
            ? rtgWorld.mesaBiome().getBand(x, y, z)
            : (BIOME_PLATEAU_BANDS.containsKey(rBiome)) ? BIOME_PLATEAU_BANDS.get(rBiome).get(y) : rtgWorld.mesaBiome().getBand(x, y, z);
    }
}
