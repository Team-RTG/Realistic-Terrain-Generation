package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biomes.extrabiomes.config.BiomeConfigEBXLGreenSwamp;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLGreenSwamp;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLGreenSwamp;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLGreenSwamp extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.greenswamp.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLGreenSwamp()
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainEBXLGreenSwamp(),
			new SurfaceEBXLGreenSwamp(topBlock, fillerBlock)
		);
		
		this.biomeConfig = new BiomeConfigEBXLGreenSwamp();
		this.biomeWeight = ConfigEBXL.weightEBXLGreenSwamp;
		this.generateVillages = ConfigEBXL.villageEBXLGreenSwamp;
	}
}
