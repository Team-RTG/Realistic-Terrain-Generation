package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaiga;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.taiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.taiga.fillerBlock;
	
	public RealisticBiomeVanillaTaiga()
	{
		super(
			BiomeGenBase.taiga,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainVanillaTaiga(),
			new SurfaceVanillaTaiga(topBlock, fillerBlock)
		);
		
		this.setRealisticBiomeName("Vanilla Taiga");
		this.biomeCategory = BiomeCategory.COLD;
		this.biomeWeight = ConfigVanilla.weightVanillaTaiga;
	}	
}
