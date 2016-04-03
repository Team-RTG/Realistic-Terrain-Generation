package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsM;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaExtremeHillsM extends RealisticBiomeVanillaBase {

    public static BiomeGenBase standardBiome = Biomes.extremeHills;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaExtremeHillsM() {

        super(
                mutationBiome,
                Biomes.river
        );
        this.generatesEmeralds = true;
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaExtremeHillsM(config, biome.config.TOP_BLOCK.get(), biome.config.FILL_BLOCK.get(), Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), 60f, -0.14f, 14f, 0.25f);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainHighland(x, y, simplex, cell, river, 10f, 200f, 140f, 10f);
            }
        };
    }
}
