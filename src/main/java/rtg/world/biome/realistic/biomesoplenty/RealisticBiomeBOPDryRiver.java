package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDryRiver;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDryRiver;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPDryRiver extends RealisticBiomeBOPBase
{
	public static BiomeGenBase bopBiome = BOPCBiomes.dryRiver;

	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;

	public RealisticBiomeBOPDryRiver(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPDryRiver(),
			new SurfaceBOPDryRiver(config)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
