package rtg.world.biome.realistic;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainFake;

/**
 * @author topisani
 */
public class FakedRTGBiome extends RealisticBiomeBase {


    public FakedRTGBiome(Biome biome) {
        super(biome, biome.getTemperature() < 0.15f ? Biomes.FROZEN_RIVER : Biomes.RIVER);
    }


    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainFake();
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceGeneric(this.config, this.baseBiome.topBlock, this.baseBiome.fillerBlock);
    }

    @Override
    public void initDecos() {
        this.addDeco(new DecoBaseBiomeDecorations());
    }
}
