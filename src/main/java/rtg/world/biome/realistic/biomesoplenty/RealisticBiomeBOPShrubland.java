package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPShrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPShrubland;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPShrubland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.shrubland;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPShrubland()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPShrubland(70f, 150f, 13f, 90f, 38f, 200f, 71f),
			new SurfaceBOPShrubland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("BOP Shrubland");
		this.biomeCategory = BiomeCategory.HOT;
		this.biomeWeight = ConfigBOP.weightBOPshrubland;
	}
}
