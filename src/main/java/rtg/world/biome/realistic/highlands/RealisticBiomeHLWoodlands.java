package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLWoodlands;
import rtg.world.gen.terrain.highlands.TerrainHLWoodlands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLWoodlands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.woodlands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLWoodlands()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLWoodlands(230f, 120f, 0f),
            new SurfaceHLWoodlands(topBlock, fillerBlock, false, null, 0.95f));
        
        this.setRealisticBiomeName("HL Woodlands");
        this.biomeCategory = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_woodlands;
    }
}
