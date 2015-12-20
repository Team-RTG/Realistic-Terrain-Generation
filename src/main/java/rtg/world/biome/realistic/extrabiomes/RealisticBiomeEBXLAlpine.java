package rtg.world.biome.realistic.extrabiomes;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLAlpine;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLAlpine;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLAlpine extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.alpine.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLAlpine()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBXLAlpine(),
			new SurfaceEBXLAlpine(topBlock, fillerBlock, false, null, 0.45f)
		);
		
		this.setRealisticBiomeName("EBXL Alpine");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXLAlpine;
		this.generateVillages = ConfigEBXL.villageEBXLAlpine;
	}
}
