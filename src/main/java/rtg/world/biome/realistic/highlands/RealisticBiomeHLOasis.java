package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLOasis;
import rtg.world.gen.terrain.highlands.TerrainHLOasis;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLOasis extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.oasis;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLOasis(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLOasis(),
            new SurfaceHLOasis(config, Blocks.sand, fillerBlock));
    }
}
