package rtg.api.world.biome;

import net.minecraft.world.biome.Biome;

import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

/**
 * Created by WhichOnesPink on 06/02/2017.
 */
public interface IRealisticBiome {

    Biome baseBiome();
    Biome riverBiome();
    Biome beachBiome();

    BiomeDecoratorRTG rDecorator();

    BiomeConfig getConfig();
    void initConfig();
    TerrainBase initTerrain();
    SurfaceBase initSurface();
    void initDecos();

    default boolean generatesEmeralds() {
        return false;
    }

    default boolean generatesSilverfish() {
        return false;
    }

    default int waterUndergroundLakeChance() {
        return 1; // Lower equals more frequent.
    }

    default int lavaUndergroundLakeChance() {
        return 1; // Lower equals more frequent.
    }

    default int waterSurfaceLakeChance() {
        return 10; // Lower equals more frequent.
    }

    default int lavaSurfaceLakeChance() {
        return 0; // Lower equals more frequent.
    }

    default float lakePressure(RTGWorld rtgWorld, int x, int z, float border) {
        return 1f;
    }

    /**
     * Returns the number of extra blocks of gold ore to generate in this biome.
     * Defaults to 0, but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     */
    default int getExtraGoldGenCount() {
        return 0;
    }

    /**
     * Returns the minimum Y value at which extra gold ore can generate.
     * Defaults to 32 (BiomeMesa), but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    default int getExtraGoldGenMinHeight() {
        return 32;
    }

    /**
     * Returns the maximum Y value at which extra gold ore can generate.
     * Defaults to 80 (BiomeMesa), but can be overridden by sub-classed biomes.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    default int getExtraGoldGenMaxHeight() {
        return 80;
    }

    String modSlug();

    default String biomeSlug() {
        return BiomeConfig.formatSlug(this.baseBiome().getBiomeName());
    }
}
