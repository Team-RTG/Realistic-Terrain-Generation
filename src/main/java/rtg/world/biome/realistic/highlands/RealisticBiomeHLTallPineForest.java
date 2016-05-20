package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLTallPineForest;
import rtg.world.gen.terrain.highlands.TerrainHLTallPineForest;

public class RealisticBiomeHLTallPineForest extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tallPineForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTallPineForest(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLTallPineForest(),
            new SurfaceHLTallPineForest(config, topBlock, fillerBlock, false, null, 1.2f));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
		this.addDeco(decoBaseBiomeDecorations);
    }
}
