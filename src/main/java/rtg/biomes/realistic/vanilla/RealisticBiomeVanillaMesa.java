package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaMesa;
import rtg.surface.vanilla.SurfaceVanillaMesa;
import rtg.terrain.vanilla.TerrainVanillaMesa;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMesa extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.mesa.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesa.fillerBlock;
	
	public RealisticBiomeVanillaMesa()
	{
		super(
			BiomeGenBase.mesa,
			RTGBiomes.baseRiverOasis,
			new CoastVanillaMesa(),
			new TerrainVanillaMesa(),
			//new SurfaceVanillaMesa(Blocks.sand, Blocks.sand, (byte)1)
			new SurfaceVanillaMesa(topBlock, fillerBlock, (byte)1)
		);
	}	
}
