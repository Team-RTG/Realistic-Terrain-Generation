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

    boolean generatesEmeralds = false;
    boolean generatesSilverfish = false;
    int waterUndergroundLakeChance = 0;
    int lavaUndergroundLakeChance = 0;
    int waterSurfaceLakeChance = 0;
    int lavaSurfaceLakeChance = 0;

    Biome baseBiome();
    Biome riverBiome();
    Biome beachBiome();

    BiomeDecoratorRTG rDecorator();

    BiomeConfig getConfig();
    void initConfig();
    TerrainBase initTerrain();
    SurfaceBase initSurface();
    void initDecos();

    float lakePressure(RTGWorld rtgWorld, int x, int z, float border);

    /**
     * Returns the number of extra blocks of gold ore to generate in this biome.
     * Defaults to 0, but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     */
    int getExtraGoldGenCount();

    /**
     * Returns the minimum Y value at which extra gold ore can generate.
     * Defaults to 32 (BiomeMesa), but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    int getExtraGoldGenMinHeight();

    /**
     * Returns the maximum Y value at which extra gold ore can generate.
     * Defaults to 80 (BiomeMesa), but can be overridden by sub-classed biomes.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    int getExtraGoldGenMaxHeight();
}
