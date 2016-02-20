package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLBaldHill;
import rtg.world.gen.terrain.highlands.TerrainHLBaldHill;

public class RealisticBiomeHLBaldHill extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.baldHill;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLBaldHill(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLBaldHill(90f, 180f, 13f, 100f, 38f, 260f, 110f),
            new SurfaceHLBaldHill(config, Blocks.stone, Blocks.stone)
        );
    }
}
