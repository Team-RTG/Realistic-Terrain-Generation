package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.extrabiomes.config.BiomeConfigEBXLWasteland;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLWasteland;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLWasteland;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLWasteland extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.wasteland.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLWasteland()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
			new TerrainEBXLWasteland(),
			new SurfaceEBXLWasteland(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigEBXLWasteland();
		this.biomeWeight = ConfigEBXL.weightEBXLWasteland;
		this.generateVillages = ConfigEBXL.villageEBXLWasteland;
	}
}
