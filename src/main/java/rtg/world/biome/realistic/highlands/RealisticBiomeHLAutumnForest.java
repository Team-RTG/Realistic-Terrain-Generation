package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLAutumnForest;
import rtg.world.gen.terrain.highlands.TerrainHLAutumnForest;

public class RealisticBiomeHLAutumnForest extends RealisticBiomeHLBase {
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.autumnForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLAutumnForest(BiomeConfig config) {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLAutumnForest(0f, 50f, 68f, 200f),
            new SurfaceHLAutumnForest(config, topBlock, fillerBlock));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
