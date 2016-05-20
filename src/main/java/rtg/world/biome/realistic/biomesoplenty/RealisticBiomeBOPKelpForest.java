package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPKelpForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPKelpForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPKelpForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.kelpForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPKelpForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPKelpForest(false, -10f, 0f, 0f, 0f, 30f),
			new SurfaceBOPKelpForest(config, topBlock, fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
