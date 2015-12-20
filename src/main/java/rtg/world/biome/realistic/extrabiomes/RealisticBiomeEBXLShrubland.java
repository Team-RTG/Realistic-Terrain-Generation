package rtg.world.biome.realistic.extrabiomes;

import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLShrubland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLShrubland;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLShrubland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.shrubland.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLShrubland()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBXLShrubland(),
			new SurfaceEBXLShrubland(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("EBXL Shrubland");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEBXL.weightEBXLShrubland;
		this.generateVillages = ConfigEBXL.villageEBXLShrubland;
	}
}
