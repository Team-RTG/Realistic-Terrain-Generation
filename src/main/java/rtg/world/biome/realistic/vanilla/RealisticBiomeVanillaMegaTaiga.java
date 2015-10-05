package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMegaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMegaTaiga;

public class RealisticBiomeVanillaMegaTaiga extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.megaTaiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.megaTaiga.fillerBlock;
	
	public RealisticBiomeVanillaMegaTaiga()
	{
		super(
			BiomeGenBase.megaTaiga,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaMegaTaiga(230f, 120f, 90f),
			new SurfaceVanillaMegaTaiga(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
		
		this.setRealisticBiomeName("Vanilla Mega Taiga");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigRTG.weightVanillaMegaTaiga;
	}	
}
