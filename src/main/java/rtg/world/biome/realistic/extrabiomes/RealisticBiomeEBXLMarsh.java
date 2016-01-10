package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.extrabiomes.config.BiomeConfigEBXLMarsh;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMarsh;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMarsh;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLMarsh extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.marsh.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMarsh()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLMarsh(),
			new SurfaceEBXLMarsh(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigEBXLMarsh();
		this.biomeWeight = ConfigEBXL.weightEBXLMarsh;
		this.generateVillages = ConfigEBXL.villageEBXLMarsh;
	}
}
