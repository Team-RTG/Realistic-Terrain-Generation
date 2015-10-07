package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.forest.topBlock;
	public static Block fillerBlock = BiomeGenBase.forest.fillerBlock;
	
	public RealisticBiomeVanillaForest()
	{
		super(
			BiomeGenBase.forest,
			BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaForest(),
			new SurfaceVanillaForest(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Vanilla Forest");
		this.biomeCategory = BiomeCategory.WET;
		this.biomeWeight = ConfigRTG.weightVanillaForest;
	}	
}
