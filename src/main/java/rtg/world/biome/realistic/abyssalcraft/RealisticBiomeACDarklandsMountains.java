package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsMountains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsMountains;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase
{

    public RealisticBiomeACDarklandsMountains(BiomeGenBase acBiome, BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsMountains(120f, 100f),
            new SurfaceACDarklandsMountains(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0.2f)
        );
        this.noLakes=true;
        this.noWaterFeatures=true;
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
