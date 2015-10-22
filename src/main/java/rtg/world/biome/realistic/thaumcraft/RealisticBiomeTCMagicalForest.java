package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.thaumcraft.ConfigTC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeCategory;
import rtg.world.gen.surface.thaumcraft.SurfaceTCMagicalForest;
import rtg.world.gen.terrain.thaumcraft.TerrainTCMagicalForest;

public class RealisticBiomeTCMagicalForest extends RealisticBiomeTCBase
{	
	public RealisticBiomeTCMagicalForest(BiomeGenBase tcBiome)
	{
		super(
			tcBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainTCMagicalForest(),
			new SurfaceTCMagicalForest(tcBiome.topBlock, tcBiome.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
		
		this.setRealisticBiomeName("Thaumcraft Magical Forest");
		this.biomeCategory = BiomeCategory.SMALL;
		this.biomeWeight = ConfigTC.weightTCMagicalForest;
	}
}
