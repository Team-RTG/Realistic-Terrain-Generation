package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLGlacier;
import rtg.world.gen.terrain.highlands.TerrainHLGlacier;

public class RealisticBiomeHLGlacier extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.glacier;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLGlacier(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLGlacier(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLGlacier(config, Blocks.packed_ice,Blocks.ice,  false, null, 0.95f));
    }
}
