package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSludgepit;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSludgepit;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPSludgepit extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.sludgepit;
	
    public static Block topBlock = BOPCBlocks.newBopGrass;
    public static Block fillerBlock = BOPCBlocks.newBopDirt;
	
	public RealisticBiomeBOPSludgepit(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSludgepit(),
			new SurfaceBOPSludgepit(config, topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
