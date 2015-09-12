package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaFrozenRiver;
import rtg.surface.vanilla.SurfaceVanillaFrozenRiver;
import rtg.terrain.vanilla.TerrainVanillaFrozenRiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaFrozenRiver extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.frozenRiver.topBlock;
	public static Block fillerBlock = BiomeGenBase.frozenRiver.fillerBlock;
	
	public RealisticBiomeVanillaFrozenRiver()
	{
		super(
			BiomeGenBase.frozenRiver,
			RTGBiomes.baseRiverIce,
			new CoastVanillaFrozenRiver(),
			new TerrainVanillaFrozenRiver(),
			new SurfaceVanillaFrozenRiver(Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, (byte)0, 0)
		);
	}	
}
