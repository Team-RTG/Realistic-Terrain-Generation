package rtg.world.biome.realistic.extrabiomes;

import extrabiomes.api.BiomeManager;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMountainTaiga;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMountainTaiga;

public class RealisticBiomeEBXLMountainTaiga extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.mountaintaiga.get();
	
	public static Block topBlock = ebxlBiome.topBlock.getBlock();
	public static Block fillerBlock = ebxlBiome.fillerBlock.getBlock();
	
	public RealisticBiomeEBXLMountainTaiga(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLMountainTaiga(),
			new SurfaceEBXLMountainTaiga(config, topBlock, fillerBlock, false, null, 1.2f)
		);
	}
}
