package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaBirchForestHills;
import rtg.surface.vanilla.SurfaceVanillaBirchForestHills;
import rtg.terrain.vanilla.TerrainVanillaBirchForestHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaBirchForestHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.birchForestHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.birchForestHills.fillerBlock;
	
	public RealisticBiomeVanillaBirchForestHills()
	{
		super(
			BiomeGenBase.birchForestHills,
			VanillaBiomes.vanillaRiverTemperate,
			new CoastVanillaBirchForestHills(),
			new TerrainVanillaBirchForestHills(70f, 180f, 7f, 100f, 38f, 260f, 68f),
			new SurfaceVanillaBirchForestHills(topBlock, fillerBlock, topBlock, topBlock)
		);
	}	
}
