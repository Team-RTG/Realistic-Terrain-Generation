package teamrtg.rtg.api.world;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.api.world.biome.RealisticBiomeBase;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;

/**
 * @author topisani
 */
public class FakedRealisticBiome extends RealisticBiomeBase {

    public FakedRealisticBiome(RTGSupport mod, BiomeGenBase biome) {
        super(mod, biome);
    }


    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int z, float border, float river) {
                return riverized(rtgWorld.biomeFaker.getHeightAt(x, z), river);
            }
        };
    }

    @Override
    public void initDecos() {
        addDeco(new DecoBaseBiomeDecorations());
    }
}
