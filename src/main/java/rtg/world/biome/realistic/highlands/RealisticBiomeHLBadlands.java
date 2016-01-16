package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLBadlands;
import rtg.world.gen.terrain.highlands.TerrainHLBadlands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBadlands extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.badlands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLBadlands(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLBadlands(),
            new SurfaceHLBadlands(topBlock, fillerBlock));
        
        this.config = config;
        this.generateVillages = ConfigHL.villageHLBadlands;
    }
}
