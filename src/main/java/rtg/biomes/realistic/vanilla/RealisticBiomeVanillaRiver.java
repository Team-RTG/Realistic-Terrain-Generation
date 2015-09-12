package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaRiver;
import rtg.surface.vanilla.SurfaceVanillaRiver;
import rtg.terrain.vanilla.TerrainVanillaRiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.river.topBlock;
	public static Block fillerBlock = BiomeGenBase.river.fillerBlock;
	
	public RealisticBiomeVanillaRiver()
	{
		super(
			BiomeGenBase.river,
			RTGBiomes.baseRiverWet,
			new CoastVanillaRiver(),
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver(Blocks.sand, Blocks.sand, Blocks.sand, Blocks.sand, (byte)0, 0)
		);
	}	
}
