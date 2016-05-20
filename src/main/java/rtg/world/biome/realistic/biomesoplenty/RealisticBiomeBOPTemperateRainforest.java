package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTemperateRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTemperateRainforest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPTemperateRainforest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.temperateRainforest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPTemperateRainforest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPTemperateRainforest(),
			new SurfaceBOPTemperateRainforest(config, topBlock, fillerBlock, false, null, 0.45f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
