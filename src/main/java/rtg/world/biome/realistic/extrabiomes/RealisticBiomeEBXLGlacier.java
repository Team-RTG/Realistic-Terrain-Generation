package rtg.world.biome.realistic.extrabiomes;

import rtg.api.biome.BiomeConfig;
import rtg.config.extrabiomes.ConfigEBXL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLGlacier;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLGlacier;
import extrabiomes.api.BiomeManager;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBXLGlacier extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.glacier.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLGlacier(BiomeConfig config)
	{
		super(
			ebxlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
			new TerrainEBXLGlacier(90f, 180f, 13f, 100f, 38f, 260f, 71f),
			new SurfaceEBXLGlacier(topBlock, fillerBlock, false, null, 0.95f)
		);
		
		this.biomeConfig = config;
		this.biomeWeight = ConfigEBXL.weightEBXLGlacier;
		this.generateVillages = ConfigEBXL.villageEBXLGlacier;
	}
}
