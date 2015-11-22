package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCherryBlossomGrove;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCherryBlossomGrove;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCherryBlossomGrove extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.cherryBlossomGrove;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCherryBlossomGrove()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainBOPCherryBlossomGrove(6f, 120f, 65f, 200f),
			new SurfaceBOPCherryBlossomGrove(topBlock, fillerBlock, true, Blocks.sand, 0.45f, 1.5f, 60f, 65f, 1.5f)
		);
		
		this.setRealisticBiomeName("BOP Cherry Blossom Grove");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPcherryBlossomGrove;
		this.generateVillages = ConfigBOP.villageBOPcherryBlossomGrove;
	}
}
