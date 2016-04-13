package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaExtremeHillsPlusM;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class RealisticBiomeVanillaExtremeHillsPlusM extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.EXTREME_HILLS_WITH_TREES;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(BiomeUtils.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaExtremeHillsPlusM(ChunkProviderRTG chunkProvider) {

        super(
                mutationBiome,
                Biomes.RIVER,
                chunkProvider
        );
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainLonelyMountain(x, y, simplex, cell, river, 120f, 230f, 68f);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaExtremeHillsPlusM(this, false, 0f, 1.5f, 60f, 65f, 1.5f, 0.08f);
    }

    @Override
    protected void initDecos() {

    }

    @Override
    protected void initProperties() {
        config.addBlock(config.MIX_BLOCK).setDefault(Blocks.GRAVEL.getDefaultState());
        config.addBlock(config.BEACH_BLOCK).setDefault(Blocks.GRAVEL.getDefaultState());
        config.GENERATE_EMERALDS.setDefault(true);
    }
}
