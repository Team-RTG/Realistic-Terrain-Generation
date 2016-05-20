package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBambooForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBambooForest;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPBambooForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.bambooForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBambooForest(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBambooForest(),
			new SurfaceBOPBambooForest(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
