package rtg.world.biome.realistic.biomesoplenty;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGlacier;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGlacier;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPGlacier extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.glacier;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPGlacier()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainBOPGlacier(230f, 100f, 0f),
			new SurfaceBOPGlacier(topBlock, fillerBlock, topBlock, fillerBlock, Blocks.packed_ice, Blocks.ice, 60f,
                -0.14f, 14f, 0.25f)
		);
		
		this.setRealisticBiomeName("BOP Glacier");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPGlacier;
		this.generateVillages = ConfigBOP.villageBOPGlacier;
	}
}
