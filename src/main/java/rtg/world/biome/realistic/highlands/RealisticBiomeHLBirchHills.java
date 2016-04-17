package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.highlands.SurfaceHLBirchHills;
import rtg.world.gen.terrain.highlands.TerrainHLBirchHills;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBirchHills extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.birchHills;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLBirchHills(BiomeConfig config)
    {
    
        super(config, hlBiome, BiomeGenBase.river, new TerrainHLBirchHills(230f, 60f, 68f),
            new SurfaceHLBirchHills(config, topBlock, fillerBlock, false, null, 0.95f));
        noLakes = true;
    }
}
