package teamrtg.rtg.api.world;

import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.module.RTGModule;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;

/**
 * @author topisani
 */
public class FakedRTGBiome extends RTGBiome {

    public FakedRTGBiome(RTGModule mod, Biome biome) {
        super(mod, biome);
    }


    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int z, float biomeWeight, float border, float river) {
                return riverized(rtgWorld.biomeFaker.getHeightAt(x, z), river);
            }
        };
    }

    @Override
    public void initDecos() {
        addDeco(new DecoBaseBiomeDecorations());
    }
}
