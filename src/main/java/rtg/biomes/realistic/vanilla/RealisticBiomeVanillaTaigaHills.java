package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaTaigaHills;
import rtg.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.terrain.vanilla.TerrainVanillaTaigaHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.taigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.taigaHills.fillerBlock;
	
	public RealisticBiomeVanillaTaigaHills()
	{
		super(
			BiomeGenBase.taigaHills,
			RTGBiomes.baseRiverCold,
			new CoastVanillaTaigaHills(),
			new TerrainVanillaTaigaHills(),
			new SurfaceVanillaTaigaHills(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
	}	
}
