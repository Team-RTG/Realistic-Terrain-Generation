package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLOutback;
import rtg.world.gen.terrain.highlands.TerrainHLOutback;

public class RealisticBiomeHLOutback extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.outback;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLOutback(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLOutback(300f),
            new SurfaceHLOutback(config, topBlock, fillerBlock, (byte) 1, 1));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
