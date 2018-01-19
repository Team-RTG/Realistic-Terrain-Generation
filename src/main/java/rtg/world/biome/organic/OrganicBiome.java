package rtg.world.biome.organic;

import javax.annotation.Nullable;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.biome.OrganicBiomeGenerator;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceOrganic;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.TerrainOrganic;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * @author topisani
 */
public final class OrganicBiome extends RealisticBiomeBase {

    private static final Map<Biome, IRealisticBiome> ORGANIC_BIOMES = Maps.newHashMap();

    private OrganicBiome(Biome biome) {
        super(biome, biome.getTemperature() < 0.15f ? Biomes.FROZEN_RIVER : Biomes.RIVER);
    }

    @Override
    public Biome baseBiome() {
        return this.baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
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

    public static void newOrganicBiome(Biome biome) {
        ORGANIC_BIOMES.put(biome, new OrganicBiome(biome));
        OrganicBiomeGenerator.organicBiomes[Biome.getIdForBiome(biome)] = true;
    }

    @Nullable
    public static IRealisticBiome getOrganicBiome(Biome biome) {
        return ORGANIC_BIOMES.getOrDefault(biome, null);
    }
}
