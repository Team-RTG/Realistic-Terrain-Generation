package rtg.world.biome.organic;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceOrganic;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainOrganic;

/**
 * @author topisani
 */
public class OrganicBiome extends RealisticBiomeBase {


    public OrganicBiome(Biome biome) {
        super(biome, biome.getTemperature() < 0.15f ? Biomes.FROZEN_RIVER : Biomes.RIVER);
    }


    @Override
    public void initConfig() {

    }

    @Override
    public boolean hasConfig() {
        return false;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainOrganic();
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceOrganic(this.config, this.baseBiome.topBlock, this.baseBiome.fillerBlock);
    }

    @Override
    public void initDecos() {
        this.addDeco(new DecoBaseBiomeDecorations());
    }
}
