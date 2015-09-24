package rtg.world.biome.realistic.vanilla;

import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBirchForestHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.birchForestHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.birchForestHills.fillerBlock;
	
	public RealisticBiomeVanillaBirchForestHills()
	{
		super(
			BiomeGenBase.birchForestHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaBirchForestHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceVanillaBirchForestHills(topBlock, fillerBlock, topBlock, topBlock)
		);
	}	
}
