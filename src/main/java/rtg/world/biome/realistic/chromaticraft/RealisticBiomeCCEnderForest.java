package rtg.world.biome.realistic.chromaticraft;

import java.util.Random;

import rtg.config.chromaticraft.ConfigCC;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreePineBig;
import rtg.world.gen.feature.tree.WorldGenTreePineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.chromaticraft.SurfaceCCEnderForest;
import rtg.world.gen.terrain.chromaticraft.TerrainCCEnderForest;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeCCEnderForest extends RealisticBiomeCCBase
{	
	public RealisticBiomeCCEnderForest(BiomeGenBase ccBiome)
	{
		super(
			ccBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainCCEnderForest(),
			new SurfaceCCEnderForest(ccBiome.topBlock, ccBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, ccBiome.topBlock, 0.05f)
		);
		
		this.setRealisticBiomeName("CC Ender Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigCC.weightCCEnderForest;
	}
}
