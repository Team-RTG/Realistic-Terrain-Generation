package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsPlains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsPlains;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

public class RealisticBiomeACDarklandsPlains extends RealisticBiomeACBase
{

	public static BiomeGenBase acBiome = ACBiomes.darklands_plains;
	
    public RealisticBiomeACDarklandsPlains(BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsPlains(),
            new SurfaceACDarklandsPlains(config, acBiome.topBlock, acBiome.fillerBlock)
        );
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
