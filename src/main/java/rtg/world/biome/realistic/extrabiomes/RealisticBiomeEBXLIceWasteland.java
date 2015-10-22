package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLIceWasteland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLIceWasteland;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLIceWasteland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.icewasteland.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLIceWasteland()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
			new TerrainEBXLIceWasteland(),
			new SurfaceEBXLIceWasteland(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("EBXL Ice Wasteland");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigEBXL.weightEBXL_icewasteland;
	}
}
