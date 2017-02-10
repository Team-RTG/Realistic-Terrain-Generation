package rtg.world.biome.realistic;

import net.minecraft.world.biome.Biome;

import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

/**
 * Created by WhichOnesPink on 08/02/2017.
 */
public class RealisticBiomeCreator extends RealisticBiomeBase {

    private IRealisticBiome iRealisticBiome;

    private RealisticBiomeCreator(Biome biome, Biome river) {
        super(biome, river);
    }

    public RealisticBiomeCreator(IRealisticBiome iRealisticBiome) {

        this(iRealisticBiome.baseBiome(), iRealisticBiome.riverBiome());

        this.iRealisticBiome = iRealisticBiome;

        super.init();
    }

    @Override
    protected void init() {
        ; // Do nothing here so that we can super.init() after the iRealisticBiome variable has been initialized.
    }

    @Override
    public Biome baseBiome() {
        return this.iRealisticBiome.baseBiome();
    }

    @Override
    public Biome riverBiome() {
        return this.iRealisticBiome.riverBiome();
    }

    @Override
    public void initConfig() {
        this.iRealisticBiome.initConfig();
    }

    @Override
    public TerrainBase initTerrain() {
        return this.iRealisticBiome.initTerrain();
    }

    @Override
    public SurfaceBase initSurface() {
        return this.iRealisticBiome.initSurface();
    }

    @Override
    public void initDecos() {
        this.iRealisticBiome.initDecos();
    }

    @Override
    public String modSlug() {
        return this.iRealisticBiome.modSlug();
    }
}
