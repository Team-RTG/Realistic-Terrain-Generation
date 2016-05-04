package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsHighland;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsHighland;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

public class RealisticBiomeACDarklandsHighland extends RealisticBiomeACBase
{

	public static BiomeGenBase acBiome = ACBiomes.darklands_hills;
	
    public RealisticBiomeACDarklandsHighland(BiomeConfig config)
    {
    
        super(config, 
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsHighland(10f, 120f, 10f, 200f),
            new SurfaceACDarklandsHighland(config, acBiome.topBlock, acBiome.fillerBlock, acBiome.topBlock, acBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
        );
        this.noLakes=true;
        this.noWaterFeatures=true;
        
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
