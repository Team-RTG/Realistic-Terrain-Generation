package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLTundra;
import rtg.world.gen.terrain.highlands.TerrainHLTundra;

public class RealisticBiomeHLTundra extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tundra;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLTundra(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLTundra(),
            new SurfaceHLTundra(config, topBlock, fillerBlock));
    }
}
