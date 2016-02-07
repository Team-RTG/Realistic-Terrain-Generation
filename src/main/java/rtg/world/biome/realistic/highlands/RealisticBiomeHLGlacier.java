package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
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
    
        super(
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLGlacier(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLGlacier(Blocks.packed_ice,Blocks.ice,  false, null, 0.95f));
        
        this.config = config;
    }
}
