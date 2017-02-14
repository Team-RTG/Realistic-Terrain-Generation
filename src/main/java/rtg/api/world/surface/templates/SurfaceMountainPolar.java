package rtg.api.world.surface.templates;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;

public class SurfaceMountainPolar extends SurfaceBase {

    private float min;

    public SurfaceMountainPolar(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff) {

        super(config, top, fill);
        min = minCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

    }
}