package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLGlacier;
import rtg.world.gen.terrain.highlands.TerrainHLGlacier;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLGlacier extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.glacier;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLGlacier(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLGlacier(90f, 180f, 13f, 100f, 71f),
            new SurfaceHLGlacier(config, Blocks.packed_ice,Blocks.packed_ice,  false, null, 0.95f));
        noWaterFeatures = true;
    }
}
