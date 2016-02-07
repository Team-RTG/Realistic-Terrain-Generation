package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.gen.surface.highlands.SurfaceHLTundra;
import rtg.world.gen.terrain.highlands.TerrainHLTundra;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLTundra extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tundra;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTundra(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLTundra(),
            new SurfaceHLTundra(topBlock, fillerBlock));
        
        this.config = config;
    }
}
