package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.BiomeConfig;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMountainTaiga;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMountainTaiga;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLMountainTaiga extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.mountaintaiga.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMountainTaiga(BiomeConfig config)
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBXLMountainTaiga(),
			new SurfaceEBXLMountainTaiga(topBlock, fillerBlock, false, null, 1.2f)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigEBXL.weightEBXLMountainTaiga;
		this.generateVillages = ConfigEBXL.villageEBXLMountainTaiga;
	}
}
