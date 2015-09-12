package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaMegaTaigaHills;
import rtg.surface.vanilla.SurfaceVanillaMegaTaigaHills;
import rtg.terrain.vanilla.TerrainVanillaMegaTaigaHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMegaTaigaHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.megaTaigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.megaTaigaHills.fillerBlock;
	
	public RealisticBiomeVanillaMegaTaigaHills()
	{
		super(
			BiomeGenBase.megaTaigaHills,
			RTGBiomes.baseRiverCold,
			new CoastVanillaMegaTaigaHills(),
			new TerrainVanillaMegaTaigaHills(230f, 120f, 90f),
			new SurfaceVanillaMegaTaigaHills(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
	}	
}
