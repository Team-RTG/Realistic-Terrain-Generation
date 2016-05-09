package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLSnowIsland;
import rtg.world.gen.terrain.highlands.TerrainHLSnowIsland;

public class RealisticBiomeHLSnowIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.snowIsland;
    
    public RealisticBiomeHLSnowIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.frozenRiver,
            new TerrainHLSnowIsland(40f, 180f, 13f, 100f, 1f, 260f, 70f),
            new SurfaceHLSnowIsland(config, hlBiome.topBlock, hlBiome.fillerBlock));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
        noLakes = true;
        noWaterFeatures = true;
    }

}
