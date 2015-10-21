package rtg.world.biome.realistic.vanilla;

import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForest;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBirchForest extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.birchForest.topBlock;
	public static Block fillerBlock = BiomeGenBase.birchForest.fillerBlock;
	
	public RealisticBiomeVanillaBirchForest()
	{
		super(
			BiomeGenBase.birchForest,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaBirchForest(),
			new SurfaceVanillaBirchForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Birch Forest");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigVanilla.weightVanillaBirchForest;
	}	
}
