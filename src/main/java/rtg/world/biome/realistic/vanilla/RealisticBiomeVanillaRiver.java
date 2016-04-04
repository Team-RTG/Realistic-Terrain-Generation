package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRiver;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {
    public static BiomeGenBase vanillaBiome = Biomes.river;

    public RealisticBiomeVanillaRiver() {
        super(
                vanillaBiome,
                Biomes.river
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaRiver(this);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainFlatLakes(x, y, simplex, river, 3f, 60f);
            }
        };
    }

    @Override
    protected void initProperties()
    {

    }

    @Override
    protected void initDecos()
    {

    }
}
