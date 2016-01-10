package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.extrabiomes.config.BiomeConfigEBXLMiniJungle;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMiniJungle;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMiniJungle;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLMiniJungle extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.minijungle.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMiniJungle()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLMiniJungle(0f, 81f, 68f, 200f),
			new SurfaceEBXLMiniJungle(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigEBXLMiniJungle();
		this.biomeWeight = ConfigEBXL.weightEBXLMiniJungle;
		this.generateVillages = ConfigEBXL.villageEBXLMiniJungle;
	}
}
