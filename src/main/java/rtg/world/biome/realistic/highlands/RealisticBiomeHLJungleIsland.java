package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLJungleIsland;
import rtg.world.gen.terrain.highlands.TerrainHLJungleIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

public class RealisticBiomeHLJungleIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.jungleIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLJungleIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLJungleIsland(0f, 40f, 68f, 200f),
            new SurfaceHLJungleIsland(config, topBlock, fillerBlock));
    }

    @Override
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // no rivers or lakes
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }

}
