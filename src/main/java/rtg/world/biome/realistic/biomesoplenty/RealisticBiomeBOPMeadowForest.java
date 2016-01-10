package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.biomesoplenty.config.BiomeConfigBOPMeadowForest;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMeadowForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMeadowForest;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPMeadowForest extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.meadowForest;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMeadowForest()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPMeadowForest(),
			new SurfaceBOPMeadowForest(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigBOPMeadowForest();
		this.biomeWeight = ConfigBOP.weightBOPMeadowForest;
		this.generateVillages = ConfigBOP.villageBOPMeadowForest;
	}
}
