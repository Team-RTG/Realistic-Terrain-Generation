package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLValley;
import rtg.world.gen.terrain.highlands.TerrainHLValley;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLValley extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.valley;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLValley()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLValley(),
            new SurfaceHLValley(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Valley");
        this.biomeCategory = BiomeCategory.WET;
        this.biomeWeight = ConfigHL.weightHL_valley;
    }
}
