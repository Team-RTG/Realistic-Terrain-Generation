package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSludgepit;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSludgepit;

public class RealisticBiomeBOPSludgepit extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.alps.get();
	
    public static Block topBlock = BOPBlocks.grass;
    public static Block fillerBlock = BOPBlocks.dirt;
	
	public RealisticBiomeBOPSludgepit(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPSludgepit(),
			new SurfaceBOPSludgepit(config, topBlock, fillerBlock)
		);
	}
}
