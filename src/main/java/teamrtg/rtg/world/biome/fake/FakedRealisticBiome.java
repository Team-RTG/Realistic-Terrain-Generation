package teamrtg.rtg.world.biome.fake;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

/**
 * @author topisani
 */
public class FakedRealisticBiome extends RealisticBiomeBase {

    public FakedRealisticBiome(RTGSupport mod, BiomeGenBase biome) {
        super(mod, biome);
    }


    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(ChunkProviderRTG provider, int x, int z, float border, float river) {
                return riverized(provider.biomeFaker.getHeightAt(x, z), river);
            }
        };
    }

    @Override
    protected void initDecos() {
        addDeco(new DecoBaseBiomeDecorations());
    }
}
