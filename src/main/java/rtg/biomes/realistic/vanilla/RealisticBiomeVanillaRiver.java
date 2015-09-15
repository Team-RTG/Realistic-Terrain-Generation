package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaRiver;
import rtg.surface.SurfaceBase;
import rtg.surface.vanilla.SurfaceVanillaRiver;
import rtg.terrain.TerrainBase;
import rtg.terrain.vanilla.TerrainVanillaRiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanilla
{	
	public static BiomeGenBase vanillaBiome = BiomeGenBase.river;
	public static Block topBlock = vanillaBiome.topBlock;
	public static Block fillerBlock = vanillaBiome.fillerBlock;
	
	public RealisticBiomeVanillaRiver()
	{
		super(
			vanillaBiome,
			vanillaBiome,
			new CoastVanillaRiver(),
			new TerrainVanillaRiver(),
			new SurfaceVanillaRiver(Blocks.sand, Blocks.sand, Blocks.sand, Blocks.sand, (byte)0, 0)
		);
	}	
}
