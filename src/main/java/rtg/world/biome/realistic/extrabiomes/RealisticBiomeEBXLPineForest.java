package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLPineForest;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLPineForest;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLPineForest extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.pineforest.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLPineForest(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.river,
			new TerrainEBXLPineForest(68f, 85f, 15),
			new SurfaceEBXLPineForest(config, topBlock, fillerBlock, false, null, 1.2f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
