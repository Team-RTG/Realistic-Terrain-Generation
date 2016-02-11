package rtg.world.biome.realistic.biomesoplenty;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWetland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWetland;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBOPWetland extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.wetland;
	
    public static Block topBlock = BOPCBlocks.newBopGrass;
    public static Block fillerBlock = BOPCBlocks.newBopDirt;
	
	public RealisticBiomeBOPWetland(BiomeConfig config)
	{
		super(
			bopBiome, BiomeGenBase.river,
			new TerrainBOPWetland(),
			new SurfaceBOPWetland(config, topBlock, fillerBlock)
		);
		
		this.config = config;
	}
}
