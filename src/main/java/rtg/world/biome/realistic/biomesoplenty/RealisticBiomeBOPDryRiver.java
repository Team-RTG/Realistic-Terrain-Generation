package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.config.biomesoplenty.ConfigBOP;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDryRiver;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDryRiver;
import biomesoplenty.api.content.BOPCBiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPDryRiver extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.dryRiver;
	
	public static Block topBlock = bopBiome.topBlock.getBlock();
	public static Block fillerBlock = bopBiome.fillerBlock.getBlock();
	
	public RealisticBiomeBOPDryRiver(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPDryRiver(),
			new SurfaceBOPDryRiver()
		);
		
		this.config = config;
	}
}
