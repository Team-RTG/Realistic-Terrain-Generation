package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLAlps;
import rtg.world.gen.terrain.highlands.TerrainHLAlps;

public class RealisticBiomeHLAutumnForest extends RealisticBiomeHLBase
{	
	public static BiomeGenBase hlBiome = HighlandsBiomes.alps;
	
	public static Block topBlock = hlBiome.topBlock;
	public static Block fillerBlock = hlBiome.fillerBlock;
	
	public RealisticBiomeHLAutumnForest()
	{
		super(
			hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.ICE),
			new TerrainHLAlps(),
			new SurfaceHLAlps(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("HL Alps");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigHL.weightHL_alps;
	}
}
