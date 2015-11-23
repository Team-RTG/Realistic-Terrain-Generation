package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMountain;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMountain;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPMountain extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.mountain;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMountain()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPMountain(),
			new SurfaceBOPMountain(topBlock, fillerBlock, true, Blocks.sand, 0.75f)
		);
		
		this.setRealisticBiomeName("BOP Mountain");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPmountain;
		this.generateVillages = ConfigBOP.villageBOPmountain;
	}
}
