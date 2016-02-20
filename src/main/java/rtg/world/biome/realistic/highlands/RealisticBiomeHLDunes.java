package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLDunes;
import rtg.world.gen.terrain.highlands.TerrainHLDunes;

public class RealisticBiomeHLDunes extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.dunes;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLDunes(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLDunes(),
            new SurfaceHLDunes(config, topBlock, fillerBlock));
    }
}
