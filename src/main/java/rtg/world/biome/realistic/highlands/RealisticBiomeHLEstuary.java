package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLEstuary;
import rtg.world.gen.terrain.highlands.TerrainHLEstuary;

public class RealisticBiomeHLEstuary extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.estuary;
    
    public RealisticBiomeHLEstuary(BiomeConfig config) {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLEstuary(),
            new SurfaceHLEstuary(config, hlBiome.topBlock, hlBiome.fillerBlock));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.maxY = 63;
		this.addDeco(decoBaseBiomeDecorations);
    }
}
