package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCoralReef;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCoralReef;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCoralReef extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.coralReef;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCoralReef()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainBOPCoralReef(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceBOPCoralReef(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Coral Reef");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigBOP.weightBOPcoralReef;
	}
}
