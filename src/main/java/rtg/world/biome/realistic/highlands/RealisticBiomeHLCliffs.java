package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLCliffs;
import rtg.world.gen.terrain.highlands.TerrainHLCliffs;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLCliffs extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.cliffs;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLCliffs(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLCliffs(75f, 70f, 78f),
            new SurfaceHLCliffs(config, topBlock, fillerBlock, false, null, 0.95f));
    }
}
