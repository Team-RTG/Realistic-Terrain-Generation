package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaDesertHills;
import rtg.surface.vanilla.SurfaceVanillaDesertHills;
import rtg.terrain.vanilla.TerrainVanillaDesertHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDesertHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.desertHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.desertHills.fillerBlock;
	
	public RealisticBiomeVanillaDesertHills()
	{
		super(
			BiomeGenBase.desertHills,
			RTGBiomes.baseRiverOasis,
			new CoastVanillaDesertHills(),
			new TerrainVanillaDesertHills(230f, 120f, 0f),
			new SurfaceVanillaDesertHills(topBlock, fillerBlock, Blocks.sandstone, topBlock, fillerBlock)
		);
	}	
}
