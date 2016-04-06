package teamrtg.rtg.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.RTGSupport;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

/**
 * @author topisani
 */
public class RealisticFakeBiome extends RealisticBiomeBase {

    public RealisticFakeBiome(RTGSupport mod, BiomeGenBase biome) {
        super(mod, biome);
    }

    @Override
    protected SurfaceBase initSurface() {
        return null;
    }

    @Override
    protected TerrainBase initTerrain() {
        return null;
    }

    @Override
    protected void initDecos() {
        addDeco(new DecoBaseBiomeDecorations());
    }
}
