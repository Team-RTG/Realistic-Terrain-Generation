package teamrtg.rtg.world.biome.fake;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

/**
 * @author topisani
 */
public class FakedRealisticBiome extends RealisticBiomeBase {

    public FakedRealisticBiome(RTGSupport mod, BiomeGenBase biome, ChunkProviderRTG chunkProvider) {
        super(mod, biome, chunkProvider);
    }


    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int z, float border, float river, ChunkProviderRTG chunkProvider) {
                return chunkProvider.biomeFaker.getHeightAt(x, z);
            }

            @Override
            protected float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int z, float border, float river) {
                return 0;
            }
        };
    }

    @Override
    protected void initDecos() {
        addDeco(new DecoBaseBiomeDecorations());
    }
}
