package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMoor;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMoor;

public class RealisticBiomeBOPMoor extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.moor;
	
    public static Block topBlock = BOPCBlocks.newBopGrass;
    public static Block fillerBlock = BOPCBlocks.newBopDirt;
	
	public RealisticBiomeBOPMoor(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMoor(63f, 69f, 32f),
			new SurfaceBOPMoor(config, topBlock, fillerBlock)
		);
	}
}
