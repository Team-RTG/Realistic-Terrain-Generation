package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLCanyon;
import rtg.world.gen.terrain.highlands.TerrainHLCanyon;

public class RealisticBiomeHLCanyon extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.canyon;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLCanyon(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLCanyon(true, 35f, 160f, 60f, 40f, 69f),
            new SurfaceHLCanyon(config, topBlock, (byte)0, fillerBlock, (byte)0, 0));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
