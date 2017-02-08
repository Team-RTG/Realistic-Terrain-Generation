package rtg.world.biome.realistic;

import net.minecraft.world.biome.Biome;

import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

/**
 * Created by WhichOnesPink on 08/02/2017.
 */
public class RealisticBiomeCreator extends RealisticBiomeBase {

    private IRealisticBiome realisticBiome;

    private RealisticBiomeCreator(Biome biome, Biome river) {
        super(biome, river);
    }

    public RealisticBiomeCreator(IRealisticBiome realisticBiome) {

        this(realisticBiome.baseBiome(), realisticBiome.riverBiome());

        this.realisticBiome = realisticBiome;
    }

    @Override
    public Biome baseBiome() {
        return this.realisticBiome.baseBiome();
    }

    @Override
    public Biome riverBiome() {
        return this.realisticBiome.riverBiome();
    }

    @Override
    public void initConfig() {
        this.realisticBiome.initConfig();
    }

    @Override
    public TerrainBase initTerrain() {
        return this.realisticBiome.initTerrain();
    }

    @Override
    public SurfaceBase initSurface() {
        return this.realisticBiome.initSurface();
    }

    @Override
    public void initDecos() {
        this.realisticBiome.initDecos();
    }
}
