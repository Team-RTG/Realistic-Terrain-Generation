package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biomes.biomesoplenty.config.BiomeConfigBOPPrairie;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPPrairie;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPPrairie;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPPrairie extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.prairie;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPPrairie()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPPrairie(63f, 80f, 25f),
			new SurfaceBOPPrairie(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigBOPPrairie();
		this.biomeWeight = ConfigBOP.weightBOPPrairie;
		this.generateVillages = ConfigBOP.villageBOPPrairie;
	}
}
