package rtg.api.util;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

import rtg.api.config.BiomeConfig;
import rtg.api.config.RTGConfig;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * Created by WhichOnesPink on 01/07/2017.
 */
public class PlateauBand {

    private static PlateauBand instance = new PlateauBand();

    private HashMap<IRealisticBiome, HashMap> biomeBands = new HashMap<IRealisticBiome, HashMap>();

    public static PlateauBand getInstance() {
        return instance;
    }

    private PlateauBand() {

    }

    public void init(IRTGWorld rtgWorld) {

        OpenSimplexNoise simplex = rtgWorld.simplex();

        for (Biome biome : Biome.REGISTRY) {

            if (biome.getBiomeName().isEmpty()) {
                Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                continue;
            }

            try {
                RealisticBiomeBase rbb = RealisticBiomeBase.getBiome(Biome.getIdForBiome(biome));
                BiomeConfig config = rbb.getConfig();

                if (config.hasProperty(config.ALLOW_PLATEAU_MODIFICATIONS)) {

                    String plateau_gradient_block_id = config.PLATEAU_GRADIENT_BLOCK_ID.get();
                    String plateau_gradient_metas = config.PLATEAU_GRADIENT_METAS.get();
                    String plateau_block_id = config.PLATEAU_BLOCK_ID.get();
                    int plateau_block_meta = config.PLATEAU_BLOCK_META.get();

                    Block plateauGradientBlock = Block.getBlockFromName(plateau_gradient_block_id);
                    byte[] plateauGradientMetas = RTGConfig.getPlateauGradientBlockMetasFromConfigString(plateau_gradient_metas);
                    IBlockState plateauBlock = Block.getBlockFromName(plateau_block_id).getStateFromMeta(plateau_block_meta);

                    HashMap<Integer, IBlockState> bands = new HashMap<Integer, IBlockState>();

                    for(int i = 0; i < 256; i++) {

                        byte b = plateauGradientMetas[i % plateauGradientMetas.length];
                        IBlockState bl = (b == -1) ? plateauBlock : plateauGradientBlock.getStateFromMeta(b);

                        bands.put(i, bl);
                    }

                    biomeBands.put(rbb, bands);
                }
            }
            catch (Exception e) {
                Logger.debug("Problem calculating plateau bands for biome (%s).", biome.getBiomeName());
            }
        }
    }

    public IBlockState getPlateauBand(IRTGWorld rtgWorld, IRealisticBiome biome, int x, int y, int z) {

        BiomeConfig config = biome.getConfig();
        boolean allow_plateau_modifications = config.ALLOW_PLATEAU_MODIFICATIONS.get();

        if (!allow_plateau_modifications) {
            return rtgWorld.mesaBiome().getBand(x, y, z);
        }
        else {

            if (biomeBands.containsKey(biome)) {

                HashMap<Integer, IBlockState> bands = biomeBands.get(biome);
                return bands.get(y);
            }
            else {
                return rtgWorld.mesaBiome().getBand(x, y, z);
            }
        }
    }
}
