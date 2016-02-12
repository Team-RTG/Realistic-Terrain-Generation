package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLMesa;
import rtg.world.gen.terrain.highlands.TerrainHLMesa;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLMesa extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.mesa;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLMesa(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLMesa(),
            new SurfaceHLMesa(config, topBlock, fillerBlock, (byte) 1));
    }
}
