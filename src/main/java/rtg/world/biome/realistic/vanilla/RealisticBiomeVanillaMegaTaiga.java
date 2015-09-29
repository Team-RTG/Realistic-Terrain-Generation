package rtg.world.biome.realistic.vanilla;

import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMegaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMegaTaiga;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

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
	}	
}
