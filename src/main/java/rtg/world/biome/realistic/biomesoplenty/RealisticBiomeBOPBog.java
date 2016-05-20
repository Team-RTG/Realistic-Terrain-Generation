package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBog;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBog;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPBog extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.bog;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPBog(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPBog(),
			new SurfaceBOPBog(config, topBlock, fillerBlock)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
