package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaiga;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.coldTaiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.coldTaiga.fillerBlock;
	
	public RealisticBiomeVanillaColdTaiga()
	{
		super(
			BiomeGenBase.coldTaiga,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaColdTaiga(),
			new SurfaceVanillaColdTaiga(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Cold Taiga");
		this.biomeCategory = BiomeCategory.SNOW;
		this.biomeWeight = ConfigRTG.weightVanillaColdTaiga;
	}	
}
