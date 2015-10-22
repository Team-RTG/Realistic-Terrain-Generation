package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLRedwoodLush;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLRedwoodLush;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLRedwoodLush extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.redwoodlush.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLRedwoodLush()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLRedwoodLush(230f, 120f, 0f),
			new SurfaceEBXLRedwoodLush(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
		
		this.setRealisticBiomeName("EBXL Redwood Lush");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigEBXL.weightEBXL_redwoodlush;
	}
}
