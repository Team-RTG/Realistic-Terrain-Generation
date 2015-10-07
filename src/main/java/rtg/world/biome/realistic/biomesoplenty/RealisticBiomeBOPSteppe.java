package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSteppe;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSteppe;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPSteppe extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.steppe;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPSteppe()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPSteppe(70f, 180f, 13f, 100f, 38f, 260f, 71f),
			new SurfaceBOPSteppe(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Steppe");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigBOP.weightBOPsteppe;
	}
}
