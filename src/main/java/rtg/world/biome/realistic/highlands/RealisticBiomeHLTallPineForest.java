package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLTallPineForest;
import rtg.world.gen.terrain.highlands.TerrainHLTallPineForest;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLTallPineForest extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tallPineForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTallPineForest()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
            new TerrainHLTallPineForest(),
            new SurfaceHLTallPineForest(topBlock, fillerBlock, false, null, 1.2f));
        
        this.setRealisticBiomeName("HL Tall Pine Forest");
        this.biomeCategory = BiomeCategory.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_tallPineForest;
    }
}
